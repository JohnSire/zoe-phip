package com.zoe.phip.register.service.impl.external;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.parser.Parser;
import com.zoe.phip.infrastructure.util.DateUtil;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.infrastructure.util.XmlBeanUtil;
import com.zoe.phip.register.dao.IMedicalStaffInfoMapper;
import com.zoe.phip.register.model.MedicalStaffInfo;
import com.zoe.phip.register.model.base.Acknowledgement;
import com.zoe.phip.register.service.external.IMedicalStaffRegister;
import com.zoe.phip.register.util.ProcessXmlUtil;
import com.zoe.phip.register.util.RegisterType;
import com.zoe.phip.register.util.RegisterUtil;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * Created by zengjiyang on 2016/4/11.
 */
@Repository("MedicalStaffRegister")
@Service(interfaceClass = IMedicalStaffRegister.class, proxy = "sdpf", dynamic = true)
public class MedicalStaffRegisterImpl implements IMedicalStaffRegister {

    private static final String adapter = "/template/staff/input/Adapter/MedicalStaffRegisterAdapter.xml";
    private static final Logger logger = LoggerFactory.getLogger(MedicalStaffRegisterImpl.class);

    @Autowired
    private IMedicalStaffInfoMapper staffInfoMapper;

    /**
     * 新增个人信息注册
     * 1.验证参数合法
     * 2.通过适配xml转成指定实体
     * 3.补全数据库实体
     * 4.执行数据库
     * 5.操作实体 AE AA
     *
     * @param message
     * @return
     */
    @Override
    public String addProvider(String message) {

        String strResult = ProcessXmlUtil.verifyMessage(message);
        //Acknowledgement
        Acknowledgement acknowledgement = new Acknowledgement();
        //xml格式错误
        if (strResult.contains("error:传入的参数不符合xml格式")) {
            // TODO: 2016/4/14
            acknowledgement.setTypeCode("AE");
            acknowledgement.setText(strResult);
            return RegisterUtil.registerMessage(RegisterType.MESSAGE, acknowledgement);
        }
        Document document = ProcessXmlUtil.load(message);
        MedicalStaffInfo staffInfo = null;
        try {
            SAXReader reader = new SAXReader();

            Document parserDoc = reader.read(this.getClass().getResourceAsStream(adapter));
            staffInfo = XmlBeanUtil.toBean(document, MedicalStaffInfo.class, parserDoc);
            staffInfo.setId(StringUtil.getUUID());
            //ReceiverSender
//            String rsXmlPath = "/template/base/ReceiverSenderAdapter.xml";
//            Document rsParDoc = reader.read(this.getClass().getResourceAsStream(rsXmlPath));
//            ReceiverSender sr = XmlBeanUtil.toBean(document, ReceiverSender.class, rsParDoc);
//            staffInfo.setReceiverSender(sr);
            //数据是否存在判断
            if (ifStaffIdExist(staffInfo.getStaffId())) {
                return RegisterUtil.responseFailed(staffInfo, "由于内容重复注册，注册失败", RegisterType.DOCTOR_ADD_ERROR);
            }
            staffInfoMapper.defaultAdd(staffInfo);
            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("注册成功");
            staffInfo.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.DOCTOR_ADD_SUCUESS, staffInfo);
        } catch (Exception ex) {
            logger.error("error:", ex);
            return RegisterUtil.responseFailed(staffInfo, ex.getMessage(), RegisterType.DOCTOR_ADD_ERROR);
        }
    }

    @Override
    public String updateProvider(String message) {
        String result = ProcessXmlUtil.verifyMessage(message);
        Acknowledgement acknowledgement = new Acknowledgement();
        //xml格式错误
        if (result.contains("error:传入的参数不符合xml格式")) {
            // TODO: 2016/4/14
            acknowledgement.setTypeCode("AE");
            acknowledgement.setText(result);
            return RegisterUtil.registerMessage(RegisterType.MESSAGE, acknowledgement);
        }
        Document document = ProcessXmlUtil.load(message);
        MedicalStaffInfo staffInfo = null;
        try {
            SAXReader reader = new SAXReader();
            //MedicalStaffInfo
            Document parserDoc = reader.read(this.getClass().getResourceAsStream(adapter));
            staffInfo = XmlBeanUtil.toBean(document, MedicalStaffInfo.class, parserDoc);
            if (!ifStaffIdExist(staffInfo.getStaffId())) {
                return RegisterUtil.responseFailed(staffInfo, "由于更新内容不存在，更新失败", RegisterType.DOCTOR_UPDATE_ERROR);
            }
            staffInfoMapper.defaultUpdate(staffInfo);
            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("更新成功");
            staffInfo.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.DOCTOR_UPDATE_SUCUESS, staffInfo);
        } catch (Exception ex) {

        }
        return null;
    }

    @Override
    public String providerDetailsQuery(String message) {
        String result = ProcessXmlUtil.verifyMessage(message);
        Acknowledgement acknowledgement = new Acknowledgement();
        if (result.contains("error:传入的参数不符合xml格式")) {
            // TODO: 2016/4/14
            acknowledgement.setTypeCode("AE");
            acknowledgement.setText(result);
            return RegisterUtil.registerMessage(RegisterType.MESSAGE, acknowledgement);
        }
        Document document;
        MedicalStaffInfo staffInfo = null;
        try {
            document = ProcessXmlUtil.load(message);
            String rootModeCode = document.getRootElement().getName();
            String msgId = document.selectSingleNode("//id/@extension").getText();
            String idRoot = document.selectSingleNode("//id/@root").getText(); //消息IDroot属性
            Date creationTime = DateUtil.stringToDateTime(document.selectSingleNode("//creationTime/@value").getText());
            String staffId = document.selectSingleNode("//controlActProcess/queryByParameterPayload/providerID/value/@extension").getText();

            String genderCode = document.selectSingleNode("//controlActProcess/queryByParameterPayload/administrativeGender/value/@code").getText();
            String staffName = document.selectSingleNode("//controlActProcess/queryByParameterPayload/providerName/value").getText();
            String birthDate = document.selectSingleNode("//controlActProcess/queryByParameterPayload/dOB/value/@value").getText();

            Map<String, Object> map = new TreeMap<>();
            if (!StringUtil.isNullOrWhiteSpace(staffId)) map.put("staffId", staffId);
            if (!StringUtil.isNullOrWhiteSpace(staffName)) map.put("staffName", staffName);
            if (!StringUtil.isNullOrWhiteSpace(genderCode)) map.put("genderCode", genderCode);
            if (!StringUtil.isNullOrWhiteSpace(birthDate)) map.put("birthDate", birthDate);
            staffInfo = staffInfoMapper.getStaff(map);
            map.clear();
            map = null;
            if (staffInfo == null || StringUtil.isNullOrWhiteSpace(staffInfo.getStaffId())) {
                staffInfo = new MedicalStaffInfo();
                staffInfo.setMsgId(msgId);
                staffInfo.setId(idRoot);
                staffInfo.setCreationTime(creationTime);
                staffInfo.setStaffId(staffId);
                return RegisterUtil.responseFailed(staffInfo, "由于查询内容不存在，查询失败", RegisterType.DOCTOR_QUERY_ERROR);
            }
            return RegisterUtil.registerMessage(RegisterType.DOCTOR_QUERY_SUCUESS, staffInfo);

        } catch (Exception ex) {
            return RegisterUtil.responseFailed(staffInfo,ex.getMessage(),RegisterType.DOCTOR_QUERY_ERROR);
        }
    }

    public boolean ifStaffIdExist(String staffId) {
        Example example = new Example(MedicalStaffInfo.class);
        example.createCriteria().andEqualTo("staffId", staffId);
        int count = staffInfoMapper.selectCountByExample(example);
        return count > 0;
    }

}
