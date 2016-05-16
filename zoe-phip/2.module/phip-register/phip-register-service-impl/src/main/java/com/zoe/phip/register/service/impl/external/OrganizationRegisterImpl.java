package com.zoe.phip.register.service.impl.external;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.DateUtil;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.module.service.util.XmlBeanUtil;
import com.zoe.phip.register.model.OrgDeptInfo;
import com.zoe.phip.module.service.entity.base.Acknowledgement;
import com.zoe.phip.register.service.external.IOrganizationRegister;
import com.zoe.phip.register.service.impl.internal.OrganizationRegisterInImpl;
import com.zoe.phip.module.service.util.ProcessXmlUtil;
import com.zoe.phip.register.util.RegisterType;
import com.zoe.phip.module.service.util.RegisterUtil;
import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by zengjiyang on 2016/4/11.
 */
@Repository("OrganizationRegister")
@Service(interfaceClass = IOrganizationRegister.class, proxy = "sdpf", protocol = {"webservice"}, dynamic = true)
public class OrganizationRegisterImpl implements IOrganizationRegister {

    private static final Logger logger = LoggerFactory.getLogger(OrganizationRegisterImpl.class);
    static final String adapter = "/template/org/input/adapter/OrganizationRegisterAdapter.xml";
    @Autowired
    private OrganizationRegisterInImpl organizationRegisterIn;

    /**
     * 新增医疗卫生机构注册
     *
     * @param message
     * @return
     */
    @Override
    public String addOrganization(String message) {
        String strResult = ProcessXmlUtil.verifyMessage(message);
        Acknowledgement acknowledgement = new Acknowledgement();
        //xml格式错误
        if (strResult.contains("error:传入的参数不符合xml格式")) {
            // TODO: 2016/4/14
            acknowledgement.setTypeCode("AE");
            acknowledgement.setText(strResult);
            return RegisterUtil.registerMessage(RegisterType.MESSAGE, acknowledgement);
        }
        Document document = ProcessXmlUtil.load(message);
        OrgDeptInfo baseInfo = null;
        try {
            baseInfo = XmlBeanUtil.toBean(document, OrgDeptInfo.class, ProcessXmlUtil.getAdapterDom(adapter));
            if(null!=baseInfo && null !=baseInfo.getValidateMessage()){
                throw  new Exception(baseInfo.getValidateMessage());
            }
            //xml 验证错误  todo 打开验证功能
            if (strResult.contains("error:数据集内容验证错误")) {
                return registerFailed(baseInfo, strResult);
            }
            OrgDeptInfo result = organizationRegisterIn.addOrganization(baseInfo);

            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("注册成功");
            baseInfo.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.ORG_ADD_SUCCESS, result);
        } catch (BusinessException e) {
            String msg = SafeExecuteUtil.getBusinessExceptionMsg(e, organizationRegisterIn.getClass());
            return registerFailed(baseInfo, msg);

        } catch (Exception ex) {
            logger.error("error:", ex);
            return registerFailed(baseInfo, ex.getMessage());
        }
    }

    @Override
    public String updateOrganization(String message) {
        String strResult = ProcessXmlUtil.verifyMessage(message);
        Acknowledgement acknowledgement = new Acknowledgement();
        //xml格式错误
        if (strResult.contains("error:传入的参数不符合xml格式")) {
            // TODO: 2016/4/14
            acknowledgement.setTypeCode("AE");
            acknowledgement.setText(strResult);
            return RegisterUtil.registerMessage(RegisterType.MESSAGE, acknowledgement);
        }
        Document document = ProcessXmlUtil.load(message);
        OrgDeptInfo baseInfo = null;
        try {
            baseInfo = XmlBeanUtil.toBean(document, OrgDeptInfo.class, ProcessXmlUtil.getAdapterDom(adapter));
            if(null!=baseInfo && null !=baseInfo.getValidateMessage()){
                throw  new Exception(baseInfo.getValidateMessage());
            }

            //xml 验证错误  todo 打开验证功能
            if (strResult.contains("error:数据集内容验证错误")) {
                return registerFailed(baseInfo, strResult);
            }
            OrgDeptInfo result = organizationRegisterIn.updateOrganization(baseInfo);

            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("更新成功");
            baseInfo.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.ORG_UPDATE_SUCCESS, result);
        } catch (BusinessException e) {
            String msg = SafeExecuteUtil.getBusinessExceptionMsg(e, organizationRegisterIn.getClass());
            return updateFailed(baseInfo, msg);

        } catch (Exception ex) {
            logger.error("error:", ex);
            return updateFailed(baseInfo, ex.getMessage());
        }
    }

    @Override
    public String organizationDetailQuery(String message) {
        String strResult = ProcessXmlUtil.verifyMessage(message);
        Acknowledgement acknowledgement = new Acknowledgement();
        //xml格式错误
        if (strResult.contains("error:传入的参数不符合xml格式")) {
            // TODO: 2016/4/14
            acknowledgement.setTypeCode("AE");
            acknowledgement.setText(strResult);
            return RegisterUtil.registerMessage(RegisterType.MESSAGE, acknowledgement);
        }
        Document document;
        OrgDeptInfo deptInfo = null;
        String strDeptId="";
        String strDeptName="";
        String strMsgId="";
        String strIdRoot="";
        String strCreateTime="";
        String errorMsg = "";
       String strDivisionRoot="";
        try {
            document = ProcessXmlUtil.load(message);
             strDeptId = document.selectSingleNode("//controlActProcess/queryByParameterPayload/organizationID/value/@extension").getText();
            strDivisionRoot = document.selectSingleNode("//controlActProcess/queryByParameterPayload/organizationID/value/@root").getText();
             strDeptName = document.selectSingleNode("//controlActProcess/queryByParameterPayload/organizationName/value").getText();
             strMsgId = document.selectSingleNode("//id/@extension").getText();
             strIdRoot = document.selectSingleNode("//id/@root").getText();
             strCreateTime = document.selectSingleNode("//creationTime/@value").getText();
            Map<String, Object> map = new TreeMap<>();
            map.clear();
            map.put("deptCode", strDeptId);
            map.put("deptName", strDeptName);
            OrgDeptInfo result = organizationRegisterIn.organizationDetailQuery(map);
       /*     if (deptInfo == null || StringUtil.isNullOrWhiteSpace(deptInfo.getCode())) {
                deptInfo = new OrgDeptInfo();
                deptInfo.setCreationTime(DateUtil.stringToDateTime(strCreateTime));
                deptInfo.setId(strIdRoot);
                deptInfo.setMsgId(strMsgId);
                deptInfo.setCode(strDeptId);
                deptInfo.setDeptName(strDeptName);
                return RegisterUtil.responseFailed(deptInfo, "由于查询内容不存在，查询失败", RegisterType.ORG_QUERY_ERROR);
            } else {
                return RegisterUtil.registerMessage(RegisterType.ORG_QUERY_SUCCESS, deptInfo);
            }*/

            return RegisterUtil.registerMessage(RegisterType.ORG_QUERY_SUCCESS, result);
        } catch (BusinessException e) {
            errorMsg = SafeExecuteUtil.getBusinessExceptionMsg(e, organizationRegisterIn.getClass());
           // acknowledgement.setText(msg);
           // return RegisterUtil.registerMessage(RegisterType.ORG_QUERY_ERROR, acknowledgement);

        } catch (Exception ex) {
           // return RegisterUtil.responseFailed(deptInfo, ex.getMessage(), RegisterType.ORG_QUERY_ERROR);
            errorMsg = ex.getMessage();
            logger.error("error:", ex);
        }
        OrgDeptInfo orgDeptInfo= new OrgDeptInfo();
        orgDeptInfo.setCreationTime(DateUtil.stringToDateTime(strCreateTime));
        orgDeptInfo.setDivisionRoot(strDivisionRoot);
        orgDeptInfo.setId(strIdRoot);
        orgDeptInfo.setMsgId(strMsgId);
        orgDeptInfo.setDeptCode(strDeptId);
        orgDeptInfo.setDeptName(strDeptName);
        return RegisterUtil.registerMessage(RegisterType.ORG_QUERY_ERROR, orgDeptInfo);
    }


    /**
     * 注册失败返回值
     *
     * @param baseInfo
     * @param errorMsg
     * @return
     */
    private String registerFailed(OrgDeptInfo baseInfo, String errorMsg) {
        return RegisterUtil.responseFailed(baseInfo, errorMsg, RegisterType.ORG_ADD_ERROR);
    }

    /**
     * 更新失败返回值
     *
     * @param baseInfo
     * @param errorMsg
     * @return
     */
    private String updateFailed(OrgDeptInfo baseInfo, String errorMsg) {
        return RegisterUtil.responseFailed(baseInfo, errorMsg, RegisterType.ORG_UPDATE_ERROR);
    }


}
