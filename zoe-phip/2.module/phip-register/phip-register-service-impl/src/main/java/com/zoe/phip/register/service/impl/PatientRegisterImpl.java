package com.zoe.phip.register.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.infrastructure.util.XmlBeanUtil;
import com.zoe.phip.register.dao.IXmanBaseInfoMapper;
import com.zoe.phip.register.dao.IXmanCardMapper;
import com.zoe.phip.register.model.EhrDataInfo;
import com.zoe.phip.register.model.XmanBaseInfo;
import com.zoe.phip.register.service.IPatientRegister;
import com.zoe.phip.register.util.ProcessXmlUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by zengjiyang on 2016/4/11.
 */
@Repository("PatientRegister")
@Service(interfaceClass = IPatientRegister.class, proxy = "sdpf", dynamic = true)
public class PatientRegisterImpl implements IPatientRegister {

    @Autowired
    private IXmanBaseInfoMapper baseInfoMapper;

    @Autowired
    private IXmanCardMapper cardMapper;

    /**
     * 新增个人信息注册
     *
     * @param message
     * @return
     */
    @Override
    public String addPatientRegistry(String message) {
        String strResult = ProcessXmlUtil.verifyMessage(message);
        if (strResult.contains("error:传入的参数不符合xml格式")) {
            // TODO: 2016/4/14
            String output = ProcessXmlUtil.responseMsgXml("AE", strResult, "", "").getTextTrim();
            return output;
        }
        Document document = ProcessXmlUtil.load(message);
        String root = document.getRootElement().getName();
        String idRoot = document.selectSingleNode("/" + root + "/id/@root").getText();
        String receiverId = document.selectSingleNode("/" + root + "/receiver/receiver/id/@root").getText();
        String senderId = document.selectSingleNode("/" + root + "/sender/device/id/@root").getText();
        String receiverExtension = document.selectSingleNode("/" + root + "/receiver/device/id/@extension").getText();
        String senderExtension = document.selectSingleNode("/" + root + "/sender/device/id/@extension").getText();

        XmanBaseInfo baseInfo = new XmanBaseInfo();
        try {
            baseInfo = XmlBeanUtil.toBean(document, XmanBaseInfo.class,null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        baseInfo.setId(UUID.randomUUID().toString());
        String strExists = "yes";
        if (!strResult.equals("success:数据集内容验证正确!") || strExists.equals("yes")) {
            document.getRootElement().element("/acceptAckCode").attribute("code").setValue("NE");
            String result;
            if (!strResult.equals("success:数据集内容验证正确!")) {
                result = ProcessXmlUtil.mixResponseXml(document, root, "PRPA_IN201313UV02", "AE", strResult + "，注册失败!", baseInfo.getMsgId(), idRoot);
            } else {
                result = ProcessXmlUtil.mixResponseXml(document, root, "PRPA_IN201313UV02", "AE", "由于内容重复注册,注册失败!", baseInfo.getMsgId(), idRoot);
            }
        }
        // TODO: 2016/4/14 新增个人基本信息到数据库
        String strAddPatientDataSet = baseInfoMapper.insertSelective(baseInfo) > 0 ? "" : baseInfo.getPatientId();
        String outputStr;
        if (StringUtil.isNullOrWhiteSpace(strAddPatientDataSet)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
            Document xmlResponse = ProcessXmlUtil.loadXmlFile("个人信息注册更新服务响应成功");
            ((Element) xmlResponse.selectSingleNode("/PRPA_IN201312UV02/id")).attribute("extension").setValue(baseInfo.getMsgId());
            ((Element) xmlResponse.selectSingleNode("/PRPA_IN201312UV02/creationTime")).attribute("value").setValue(sdf.format(new Date()));
            ((Element) xmlResponse.selectSingleNode("/PRPA_IN201312UV02/controlActProcess/subject/registrationEvent/subject1/patient/id")).attribute("extension").setValue(baseInfo.getPatientId());
            xmlResponse.selectSingleNode("/PRPA_IN201312UV02/controlActProcess/subject/registrationEvent/subject1/patient/patientPerson/name").setText(baseInfo.getName());
            ((Element) xmlResponse.selectSingleNode("/PRPA_IN201312UV02/controlActProcess/subject/registrationEvent/custodian/assignedEntity/id")).attribute("extension").setValue(baseInfo.getAssignedPersonCode());
            xmlResponse.selectSingleNode("/PRPA_IN201312UV02/controlActProcess/subject/registrationEvent/custodian/assignedEntity/assignedPerson/name").setText(baseInfo.getAssignedPersonName());
            ((Element) xmlResponse.selectSingleNode("/PRPA_IN201312UV02/receiver/device/id")).attribute("root").setValue(senderId);
            ((Element) xmlResponse.selectSingleNode("/PRPA_IN201312UV02/sender/device/id")).attribute("root").setValue(receiverId);
            ((Element) xmlResponse.selectSingleNode("/PRPA_IN201312UV02/receiver/device/id")).attribute("extension").setValue(senderExtension);
            ((Element) xmlResponse.selectSingleNode("/PRPA_IN201312UV02/sender/device/id")).attribute("extension").setValue(receiverExtension);
            outputStr = ProcessXmlUtil.mixResponseXml(xmlResponse, "PRPA_IN201312UV02", "PRPA_IN201312UV02", "AA", "注册成功", baseInfo.getMsgId(), idRoot);
            // 如果个人注册成功,不仅要添加BASEINFO�而且在EHR_DATA_INFO中也需添加一条个人信息的索引记录用以调阅档案

          /*  EhrDataInfo ehrDataInfo = new EhrDataInfo();
            ehrDataInfo.setMsgId(baseInfo.getMsgId());
            ehrDataInfo.setPatientId(baseInfo.getHealthRecordNo());
            ehrDataInfo.setPatientName(baseInfo.getName());
            ehrDataInfo.setHealthCardId(baseInfo.getCardNo());
            ehrDataInfo.setIdNo(baseInfo.getIdNo());
            ehrDataInfo.setOrgCode(baseInfo.getOrgCode());
            ehrDataInfo.setOrgName(baseInfo.getOrgName());
            ehrDataInfo.setAreaCode(baseInfo.getAreaCode());
            ehrDataInfo.setTelNo(baseInfo.getTelNo());
            ehrDataInfo.setAddressStreet(baseInfo.getAddress());
            ehrDataInfo.setOutTime(new Date());
            ehrDataInfo.setTitle("个人基本健康信息");
            ehrDataInfo.setDocumentUniqueId("HSDA00.01");
            ehrDataInfo.setRepositoryUniqueId(UUID.randomUUID().toString());*/

        } else {
            ((Element) (document.selectSingleNode("/" + root + "/receiver/device/id"))).attribute("root").setValue(senderId);
            ((Element) (document.selectSingleNode("/" + root + "/sender/device/id"))).attribute("root").setValue(receiverId);
            ((Element) (document.selectSingleNode("/" + root + "/receiver/device/id"))).attribute("extension").setValue(senderExtension);
            ((Element) (document.selectSingleNode("/" + root + "/sender/device/id"))).attribute("extension").setValue(receiverExtension);
            ((Element) (document.selectSingleNode("/" + root + "/acceptAckCode"))).attribute("code").setValue("NE");
            outputStr = ProcessXmlUtil.mixResponseXml(document, root, "PRPA_IN201313UV02", "AE", "由于" + strAddPatientDataSet + "，注册失败!", baseInfo.getMsgId(), idRoot);
        }
        return outputStr;
    }

    @Override
    public String updatePatientRegistry(String message) {
        return null;
    }

    @Override
    public String mergePatientRegistry(String message) {
        return null;
    }

    @Override
    public String patientRegistryQuery(String message) {
        return null;
    }
}
