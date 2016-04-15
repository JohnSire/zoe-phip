package com.zoe.phip.register.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.infrastructure.util.XmlBeanUtil;
import com.zoe.phip.register.model.MedicalStaffInfo;
import com.zoe.phip.register.service.IMedicalStaffRegister;
import com.zoe.phip.register.util.ProcessXmlUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by zengjiyang on 2016/4/11.
 */
@Repository("MedicalStaffRegister")
@Service(interfaceClass = IMedicalStaffRegister.class, proxy = "sdpf", dynamic = true)
public class MedicalStaffRegisterImpl implements IMedicalStaffRegister {
    @Override
    public String addProvider(String message) {

//        String strResult = ProcessXmlUtil.verifyMessage(message);
//        if (strResult.contains("error:传入的参数不符合xml格式")) {
//            String output = ProcessXmlUtil.responseMsgXml("AE", strResult, "", "").getTextTrim();
//            return output;
//        }
//        Document document = ProcessXmlUtil.load(message);
//        String root = document.getRootElement().getName();
//        String idRoot = document.selectSingleNode("/" + root + "/id/@root").getText();
//        String receiverId = document.selectSingleNode("/" + root + "/receiver/device/id/@root").getText();
//        String senderId = document.selectSingleNode("/" + root + "/sender/device/id/@root").getText();
//
//        MedicalStaffInfo info = null;
//        try {
//            info = XmlBeanUtil.toBean(document, new MedicalStaffInfo());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        String strExists = "yes";
//        if (!strResult.equals("success:数据集内容验证正确") || strExists.equals("yes")) {
//            document.getRootElement().element("/acceptAckCode").attribute("code").setValue("NE");
//            String result;
//            if (!strResult.equals("success:数据集内容验证正确")) {
//                result = ProcessXmlUtil.mixResponseXml(document, root, "PRPM_IN301011UV01", "AE", strResult + "，注册失败", info.getMessageId(), idRoot);
//            } else {
//                result = ProcessXmlUtil.mixResponseXml(document, root, "PRPM_IN301011UV01", "AE", "由于内容重复注册，注册失败", info.getMessageId(), idRoot);
//            }
//        }
//
//        String strAddMedicalDataSet = "";
//        String outputStr;
//
//        if (StringUtil.isNullOrWhiteSpace(strAddMedicalDataSet)) {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
//            Document xmlResponse = ProcessXmlUtil.loadXmlFile("医护人员注册更新服务响应成功");
//            //消息ID root属性
//            ((Element) xmlResponse.selectSingleNode("/PRPM_IN301011UV01/id")).attribute("extension").setValue(info.getMessageId());
//            //创建时间
//            ((Element) xmlResponse.selectSingleNode("/PRPM_IN301011UV01/creationTime")).attribute("value").setValue(sdf.format(new Date()));
//            //医务人员ID
//            ((Element) xmlResponse.selectSingleNode("/PRPM_IN301011UV01/controlActProcess/subject/registrationRequest/subject1/healthCareProvider/id")).attribute("extension").setValue(info.getStaffId());
//
//            xmlResponse.selectSingleNode("/PRPM_IN301011UV01/controlActProcess/subject/registrationRequest/subject1/healthCareProvider/healthCarePrincipalPerson/name").setText(info.getName());
//            ((Element) xmlResponse.selectSingleNode("/PRPM_IN301011UV01/controlActProcess/subject/registrationRequest/subject1/healthCareProvider/healthCarePrincipalPerson/administrativeGenderCode")).attribute("code").setValue(info.getGenderCode());
//
//
//        }
//

        return "abc";
    }

    @Override
    public String updateProvider(String message) {
        return null;
    }

    @Override
    public String providerDetailsQuery(String message) {
        return null;
    }


}
