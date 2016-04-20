package com.zoe.phip.register.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.parser.Parser;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.infrastructure.util.XmlBeanUtil;
import com.zoe.phip.register.dao.IMedicalStaffInfoMapper;
import com.zoe.phip.register.model.MedicalStaffInfo;
import com.zoe.phip.register.model.base.Acknowledgement;
import com.zoe.phip.register.model.base.ReceiverSender;
import com.zoe.phip.register.service.IMedicalStaffRegister;
import com.zoe.phip.register.util.ProcessXmlUtil;
import com.zoe.phip.register.util.RegisterType;
import com.zoe.phip.register.util.RegisterUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by zengjiyang on 2016/4/11.
 */
@Repository("MedicalStaffRegister")
@Service(interfaceClass = IMedicalStaffRegister.class, proxy = "sdpf", dynamic = true)
public class MedicalStaffRegisterImpl implements IMedicalStaffRegister {


    private static final Logger logger = LoggerFactory.getLogger(MedicalStaffRegisterImpl.class);


    @Autowired
    private Parser parser;

    @Autowired
    private IMedicalStaffInfoMapper medicalStaffmapper;

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
            //MedicalStaffInfo
            String filePath = "/template/staff/input/Adapter/MedicalStaffRegisterAdapter.xml";
            Document parserDoc = reader.read(this.getClass().getResourceAsStream(filePath));
            staffInfo = XmlBeanUtil.toBean(document, MedicalStaffInfo.class, parserDoc);
            staffInfo.setId(StringUtil.getUUID());
            //ReceiverSender
//            String rsXmlPath = "/template/base/ReceiverSenderAdapter.xml";
//            Document rsParDoc = reader.read(this.getClass().getResourceAsStream(rsXmlPath));
//            ReceiverSender sr = XmlBeanUtil.toBean(document, ReceiverSender.class, rsParDoc);
//            staffInfo.setReceiverSender(sr);
            //数据是否存在判断
            Example example = new Example(MedicalStaffInfo.class);
            example.createCriteria().andEqualTo("staffId", staffInfo.getStaffId());
            int count = medicalStaffmapper.selectCountByExample(example);
            if (count > 0) {
                return registerFailed(staffInfo, "由于内容重复注册，注册失败");
            }

            medicalStaffmapper.defaultAdd(staffInfo);
            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("注册成功");
            staffInfo.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.DOCTOR_SUCUESS, staffInfo);
        } catch (Exception ex) {
            logger.error("error:", ex);
            return registerFailed(staffInfo, ex.getMessage());
        }


    }

    @Override
    public String updateProvider(String message) {


        return null;
    }

    @Override
    public String providerDetailsQuery(String message) {
        return null;
    }


    /**
     * 注册失败返回值
     *
     * @param staffInfo
     * @param errorMsg
     * @return
     */
    private String registerFailed(MedicalStaffInfo staffInfo, String errorMsg) {

        return RegisterUtil.responseFailed(staffInfo, errorMsg, RegisterType.DOCTOR_SUCUESS);
    }


//    private String responseFailed(MedicalStaffInfo staffInfo, String errorMsg, String path) {
//        Acknowledgement acknowledgement = new Acknowledgement();
//        acknowledgement.setTypeCode("AE");
//        acknowledgement.setText(errorMsg);
//        staffInfo.setAcknowledgement(acknowledgement);
//        return RegisterUtil.registerMessage(path, staffInfo);
//    }
}
