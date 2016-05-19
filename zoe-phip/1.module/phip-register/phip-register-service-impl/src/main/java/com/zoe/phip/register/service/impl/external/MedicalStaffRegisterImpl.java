package com.zoe.phip.register.service.impl.external;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.config.PropertyPlaceholder;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.DateUtil;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.module.service.util.XmlBeanUtil;
import com.zoe.phip.register.model.MedicalStaffInfo;
import com.zoe.phip.module.service.entity.base.Acknowledgement;
import com.zoe.phip.register.service.external.IMedicalStaffRegister;
import com.zoe.phip.register.service.impl.internal.MedicalStaffRegisterInImpl;
import com.zoe.phip.module.service.util.ProcessXmlUtil;
import com.zoe.phip.register.util.RegisterType;
import com.zoe.phip.module.service.util.RegisterUtil;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by zengjiyang on 2016/4/11.
 */
@Repository("MedicalStaffRegister")
@Service(interfaceClass = IMedicalStaffRegister.class, proxy = "sdpf", protocol = {"webservice"}, dynamic = true)
public class MedicalStaffRegisterImpl implements IMedicalStaffRegister {

    //    private static final String adapter = "/template/staff/input/Adapter/MedicalStaffRegisterAdapter.xml";
    private static final Logger logger = LoggerFactory.getLogger(MedicalStaffRegisterImpl.class);


    @Autowired
    private MedicalStaffRegisterInImpl staffRegisterIn;

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

        String strResult = ProcessXmlUtil.verifyMessage(message, PropertyPlaceholder.getProperty("staff.register"));
        //Acknowledgement
        Acknowledgement acknowledgement = new Acknowledgement();
        //xml格式错误
        if (strResult.contains("error:传入的参数不符合xml格式")) {
            // TODO: 2016/4/14
            MedicalStaffInfo medicalStaffInfo = new MedicalStaffInfo();
            medicalStaffInfo.setCreationTime(new Date());
            medicalStaffInfo.setId(StringUtil.getUUID());
            medicalStaffInfo.setMsgId(StringUtil.getUUID());
            return RegisterUtil.responseFailed(medicalStaffInfo, strResult, RegisterType.DOCTOR_ADD_ERROR);

        }
        Document document = ProcessXmlUtil.load(message);
        String errorMsg = "";
        MedicalStaffInfo staffInfo = null;
        try {
            SAXReader reader = new SAXReader();
            String filePath = "/template/staff/input/Adapter/MedicalStaffRegisterAdapter.xml";
            Document parserDoc = reader.read(this.getClass().getResourceAsStream(filePath));
            staffInfo = XmlBeanUtil.toBean(document, MedicalStaffInfo.class, ProcessXmlUtil.getAdapterDom(filePath));

            if (null != staffInfo && null != staffInfo.getValidateMessage()) {
                throw new Exception(staffInfo.getValidateMessage());
            }
            //xml 验证错误
            if (strResult.contains("error:数据集内容验证错误")) {
                return RegisterUtil.responseFailed(staffInfo, strResult, RegisterType.DOCTOR_ADD_ERROR);
            }
            MedicalStaffInfo result = staffRegisterIn.addProvider(staffInfo);

            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("注册成功");
            staffInfo.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.DOCTOR_ADD_SUCCESS, result);

        } catch (BusinessException e) {
            errorMsg = SafeExecuteUtil.getBusinessExceptionMsg(e, staffRegisterIn.getClass());
        } catch (Exception ex) {
            logger.error("error:", ex);
            errorMsg = ex.getMessage();
        }
        acknowledgement.setText(errorMsg);
        return RegisterUtil.responseFailed(staffInfo, errorMsg, RegisterType.DOCTOR_ADD_ERROR);
    }

    @Override
    public String updateProvider(String message) {
        String strResult = ProcessXmlUtil.verifyMessage(message, PropertyPlaceholder.getProperty("staff.update"));
        Acknowledgement acknowledgement = new Acknowledgement();
        //xml格式错误
        if (strResult.contains("error:传入的参数不符合xml格式")) {
            MedicalStaffInfo medicalStaffInfo = new MedicalStaffInfo();
            medicalStaffInfo.setCreationTime(new Date());
            medicalStaffInfo.setMsgId(StringUtil.getUUID());
            medicalStaffInfo.setId(StringUtil.getUUID());
            return RegisterUtil.responseFailed(medicalStaffInfo, strResult, RegisterType.DOCTOR_UPDATE_ERROR);

        }
        Document document = ProcessXmlUtil.load(message);
        MedicalStaffInfo staffInfo = null;
        String errorMsg = "";
        try {
            String filePath = "/template/staff/input/Adapter/MedicalStaffRegisterAdapter.xml";
            staffInfo = XmlBeanUtil.toBean(document, MedicalStaffInfo.class, ProcessXmlUtil.getAdapterDom(filePath));

            if (null != staffInfo && null != staffInfo.getValidateMessage()) {
                throw new Exception(staffInfo.getValidateMessage());
            }
            //数据是否存在判断
//            if (!ifStaffIdExist(staffInfo.getStaffId())) {
//                return RegisterUtil.responseFailed(staffInfo, "由于更新内容不存在，更新失败", RegisterType.DOCTOR_UPDATE_ERROR);
//            }
            //xml 验证错误
            if (strResult.contains("error:数据集内容验证错误")) {
                return RegisterUtil.responseFailed(staffInfo, strResult, RegisterType.DOCTOR_UPDATE_ERROR);
            }
            MedicalStaffInfo result = staffRegisterIn.updateProvider(staffInfo);

            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("注册成功");
            staffInfo.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.DOCTOR_UPDATE_SUCCESS, result);

        } catch (BusinessException e) {
            errorMsg = SafeExecuteUtil.getBusinessExceptionMsg(e, staffRegisterIn.getClass());
        } catch (Exception ex) {
            errorMsg = ex.getMessage();
            logger.error("error:", ex);
        }
        return RegisterUtil.responseFailed(staffInfo, errorMsg, RegisterType.DOCTOR_UPDATE_ERROR);
    }

    @Override
    public String providerDetailsQuery(String message) {
        String strResult = ProcessXmlUtil.verifyMessage(message, PropertyPlaceholder.getProperty("staff.query"));
        Acknowledgement acknowledgement = new Acknowledgement();
        if (strResult.contains("error:传入的参数不符合xml格式")) {
            MedicalStaffInfo medicalStaffInfo=new MedicalStaffInfo();
            medicalStaffInfo.setMsgId(StringUtil.getUUID());
            medicalStaffInfo.setId(StringUtil.getUUID());
            medicalStaffInfo.setCreationTime(new Date());
            acknowledgement.setTypeCode("AE");
            acknowledgement.setText(strResult);
            medicalStaffInfo.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.DOCTOR_QUERY_ERROR, medicalStaffInfo);

        }
        Document document;
        String errorMsg = "";
        String msgId = "";
        String creationTime = "";
        String extensionId = "";
        String name = "";
        String birthDate = "";
        document = ProcessXmlUtil.load(message);
        try {
            msgId = document.selectSingleNode(PropertyPlaceholder.getProperty("queryStaff.msgId")).getText();
            creationTime = document.selectSingleNode(PropertyPlaceholder.getProperty("queryStaff.creationTime")).getText();
            extensionId = document.selectSingleNode(PropertyPlaceholder.getProperty("queryStaff.extensionId")).getText();
            name = document.selectSingleNode(PropertyPlaceholder.getProperty("queryStaff.name")).getText();
            birthDate = document.selectSingleNode(PropertyPlaceholder.getProperty("queryStaff.birthDate")).getText();
            Map<String, Object> map = new TreeMap<>();
            if (!StringUtil.isNullOrWhiteSpace(extensionId)) map.put("extensionId", extensionId);
            if (!StringUtil.isNullOrWhiteSpace(name)) map.put("name", name);
            if (!StringUtil.isNullOrWhiteSpace(birthDate)) map.put("birthDate", birthDate);
            MedicalStaffInfo result = staffRegisterIn.providerDetailsQuery(map);

            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("查询成功");
            result.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.DOCTOR_QUERY_SUCCESS, result);

        } catch (BusinessException e) {
            errorMsg = SafeExecuteUtil.getBusinessExceptionMsg(e, staffRegisterIn.getClass());
        } catch (Exception ex) {
            errorMsg = ex.getMessage();
            logger.error("error:", ex);
        }

        MedicalStaffInfo info = new MedicalStaffInfo();
        info.setName(name);
        info.setMsgId(msgId);
        info.setCreationTime(DateUtil.stringToDateTime(creationTime));
        info.setExtensionId(extensionId);
        info.setBirthTime(DateUtil.stringToDateTime(birthDate));
        acknowledgement.setText(errorMsg);
        return RegisterUtil.registerMessage(RegisterType.DOCTOR_QUERY_ERROR, info);
    }


}
