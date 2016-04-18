package com.zoe.phip.register.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.util.XmlBeanUtil;
import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.register.dao.IOrgDeptInfoMapper;
import com.zoe.phip.register.model.OrgDeptInfo;
import com.zoe.phip.register.service.IOrganizationRegister;
import com.zoe.phip.register.util.ProcessXmlUtil;
import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.UUID;

/**
 * Created by zengjiyang on 2016/4/11.
 */
@Repository("OrganizationRegister")
@Service(interfaceClass = IOrganizationRegister.class, proxy = "sdpf", dynamic = true)
public class OrganizationRegisterImpl implements IOrganizationRegister {

    @Autowired
    private IOrgDeptInfoMapper baseInfoMapper;

    @Autowired
    private BaseInServiceImpl baseInServiceImpl;

    @Override
    /**
     * 新增医疗卫生机构注册
     */
    public String addOrganization(String message) {


        String strResult = ProcessXmlUtil.verifyMessage(message);


        if (strResult.contains("error:传入的参数不符合xml格式"))
        {
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
        OrgDeptInfo baseInfo = null;
        try {
            baseInfo = XmlBeanUtil.toBean(document, new OrgDeptInfo());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        String strExists = "yes";
        if (!strResult.equals("success:数据集内容验证正确") || strExists.equals("yes")) {
            document.getRootElement().element("/acceptAckCode").attribute("code").setValue("NE");
            String result;
            if (!strResult.equals("success:数据集内容验证正确")) {
                result = ProcessXmlUtil.mixResponseXml(document, root, "PRPM_IN401031UV01", "AE", strResult + "，注册失败", baseInfo.getMsgId(), idRoot);
            } else {
                result = ProcessXmlUtil.mixResponseXml(document, root, "PRPM_IN401031UV01", "AE", "由于内容重复注册，注册失败", baseInfo.getMsgId(), idRoot);
            }
        }
        String strAddPatientDataSet = baseInfoMapper.insertSelective(baseInfo) > 0 ? "" : "false";
        String outputStr=null;
/*        if (StringUtil.isNullOrWhiteSpace(strAddPatientDataSet)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
            Document xmlResponse = ProcessXmlUtil.loadXmlFile("医疗卫生机构注册更新服务响应成功");
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
            outputStr = ProcessXmlUtil.mixResponseXml(xmlResponse, "PRPM_IN401031UV01", "PRPM_IN401031UV01", "AA", "注册成功", baseInfo.getMessageId(), idRoot);



        } else {
            ((Element) (document.selectSingleNode("/" + root + "/receiver/device/id"))).attribute("root").setValue(senderId);
            ((Element) (document.selectSingleNode("/" + root + "/sender/device/id"))).attribute("root").setValue(receiverId);
            ((Element) (document.selectSingleNode("/" + root + "/receiver/device/id"))).attribute("extension").setValue(senderExtension);
            ((Element) (document.selectSingleNode("/" + root + "/sender/device/id"))).attribute("extension").setValue(receiverExtension);
            ((Element) (document.selectSingleNode("/" + root + "/acceptAckCode"))).attribute("code").setValue("NE");
            outputStr = ProcessXmlUtil.mixResponseXml(document, root, "PRPM_IN401031UV02", "AE", "由于" + strAddPatientDataSet + "，注册失败", baseInfo.getMsgId(), idRoot);
        }*/
        return outputStr;


    }

    @Override
    public String updateOrganization(String message) {
        String strResult = ProcessXmlUtil.verifyMessage(message);
        if (strResult.contains("error:传入的参数不符合xml格式"))
        {
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
        OrgDeptInfo baseInfo = null;
        try {
            baseInfo = XmlBeanUtil.toBean(document, new OrgDeptInfo());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String strExists = "yes";
        if (!strResult.equals("success:数据集内容验证正确") || strExists.equals("yes")) {
            document.getRootElement().element("/acceptAckCode").attribute("code").setValue("NE");
            String result;
            if (!strResult.equals("success:数据集内容验证正确")) {
                result = ProcessXmlUtil.mixResponseXml(document, root, "PRPM_IN401031UV01", "AE", strResult + "，注册失败", baseInfo.getMsgId(), idRoot);
            } else {
                result = ProcessXmlUtil.mixResponseXml(document, root, "PRPM_IN401031UV01", "AE", "由于更新内容不存在，注册失败", baseInfo.getMsgId(), idRoot);
            }
        }
        String strAddPatientDataSet = baseInfoMapper.updateByPrimaryKeySelective(baseInfo) > 0 ? "" : "false";
        String outputStr=null;


        return null;
    }

    @Override
    public String organizationDetailQuery(String message) {
        String strResult = ProcessXmlUtil.verifyMessage(message);
        if (strResult.contains("error:传入的参数不符合xml格式"))
        {
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
        String strMsgId = document.selectSingleNode("/" + root + "/id/@extension").getText();
        String strDeptId = document.selectSingleNode("/" + root + "/controlActProcess/queryByParameterPayload/organizationID/value/@extension").getText();
        String strDeptName = "";
        strDeptName = document.selectSingleNode("/" + root + "/controlActProcess/queryByParameterPayload/organizationName/value").getText();
        
        //// TODO: 2016/4/15  查询数据库
        OrgDeptInfo baseInfo = getOrgDeptInfo(strDeptId, strDeptName);
        baseInfo.setMsgId ( strMsgId.equals("") ? UUID.randomUUID().toString() : strMsgId);
        if (strResult != "success:数据集内容验证正确" || baseInfo.getDeptCode().equals("")) {
            document.getRootElement().element("/acceptAckCode").attribute("code").setValue("NE");
            String result;
            if (!strResult.equals("success:数据集内容验证正确")) {
                result = ProcessXmlUtil.mixResponseXml(document, root, "PRPM_IN401031UV01", "AE", strResult + "，查询失败", baseInfo.getMsgId(), idRoot);
            } else {
                result = ProcessXmlUtil.mixResponseXml(document, root, "PRPM_IN401031UV01", "AE", "由于查询内容不存在，查询失败", baseInfo.getMsgId(), idRoot);
            }
        }

        return null;
    }


    public OrgDeptInfo getOrgDeptInfo(String deptCode,String deptName){
        Example example = new Example(OrgDeptInfo.class);
        example.createCriteria().andEqualTo("deptCode", deptCode).andEqualTo("deptName",deptName);
        return (OrgDeptInfo)baseInServiceImpl.getMapper().selectByExample(example).get(0);
    }
}
