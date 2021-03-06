package com.zoe.phip.register.service.impl.external;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.config.PropertyPlaceholder;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.DateUtil;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.infrastructure.util.StringUtil;
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

import java.util.Date;
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
        String strResult = ProcessXmlUtil.verifyMessage(message, PropertyPlaceholder.getProperty("org.register"));
        Acknowledgement acknowledgement = new Acknowledgement();
        //xml格式错误
        if (strResult.contains("error:传入的参数不符合xml格式")) {
            OrgDeptInfo orgDeptInfo = new OrgDeptInfo();
            orgDeptInfo.setCreationTime(new Date());
            orgDeptInfo.setMsgId(StringUtil.getUUID());
            orgDeptInfo.setId(StringUtil.getUUID());
            return registerFailed(orgDeptInfo, strResult);

        }
        Document document = ProcessXmlUtil.load(message);
        OrgDeptInfo baseInfo = null;
        try {
            baseInfo = XmlBeanUtil.toBean(document, OrgDeptInfo.class, ProcessXmlUtil.getAdapterDom(adapter));
            if (null != baseInfo && null != baseInfo.getValidateMessage()) {
                throw new Exception(baseInfo.getValidateMessage());
            }
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
        String strResult = ProcessXmlUtil.verifyMessage(message, PropertyPlaceholder.getProperty("org.update"));
        Acknowledgement acknowledgement = new Acknowledgement();
        //xml格式错误
        if (strResult.contains("error:传入的参数不符合xml格式")) {
            OrgDeptInfo orgDeptInfo = new OrgDeptInfo();
            orgDeptInfo.setCreationTime(new Date());
            orgDeptInfo.setMsgId(StringUtil.getUUID());
            orgDeptInfo.setId(StringUtil.getUUID());
            return updateFailed(orgDeptInfo, strResult);
        }
        Document document = ProcessXmlUtil.load(message);
        OrgDeptInfo baseInfo = null;
        try {
            baseInfo = XmlBeanUtil.toBean(document, OrgDeptInfo.class, ProcessXmlUtil.getAdapterDom(adapter));
            if (null != baseInfo && null != baseInfo.getValidateMessage()) {
                throw new Exception(baseInfo.getValidateMessage());
            }

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
        String strResult = ProcessXmlUtil.verifyMessage(message, PropertyPlaceholder.getProperty("org.query"));
        Acknowledgement acknowledgement = new Acknowledgement();
        //xml格式错误
        if (strResult.contains("error:传入的参数不符合xml格式")) {
            OrgDeptInfo orgDeptInfo = new OrgDeptInfo();
            orgDeptInfo.setCreationTime(new Date());
            orgDeptInfo.setMsgId(StringUtil.getUUID());
            orgDeptInfo.setId(StringUtil.getUUID());

            acknowledgement.setText(strResult);
            orgDeptInfo.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.ORG_QUERY_ERROR, orgDeptInfo);
        }
        Document document;
        OrgDeptInfo deptInfo = null;
        String strDeptId = "";
        String strDeptName = "";
        String strMsgId = "";
        String strIdRoot = "";
        String strCreateTime = "";
        String errorMsg = "";
        String strDivisionRoot = "";
        try {
            document = ProcessXmlUtil.load(message);
            strDeptId = document.selectSingleNode(PropertyPlaceholder.getProperty("queryOrg.strDeptId")).getText();
            strDivisionRoot = document.selectSingleNode(PropertyPlaceholder.getProperty("queryOrg.strDivisionRoot")).getText();
            strDeptName = document.selectSingleNode(PropertyPlaceholder.getProperty("queryOrg.strDeptName")).getText();
            strMsgId = document.selectSingleNode(PropertyPlaceholder.getProperty("queryOrg.strMsgId")).getText();
            strIdRoot = document.selectSingleNode(PropertyPlaceholder.getProperty("queryOrg.strIdRoot")).getText();
            strCreateTime = document.selectSingleNode(PropertyPlaceholder.getProperty("queryOrg.strCreateTime")).getText();
            acknowledgement.setMsgId(strMsgId);
            acknowledgement.setCreateTime(strCreateTime);
            if (strResult.contains("error:数据集内容验证错误")) {
                acknowledgement.setText(strResult + ",查询失败");
                OrgDeptInfo orgDeptInfo = new OrgDeptInfo();
                orgDeptInfo.setCreationTime(DateUtil.stringToDateTime(strCreateTime));
                orgDeptInfo.setDivisionRoot(strDivisionRoot);
                orgDeptInfo.setMsgId(strMsgId);
                orgDeptInfo.setDeptCode(strDeptId);
                orgDeptInfo.setDeptName(strDeptName);
                orgDeptInfo.setAcknowledgement(acknowledgement);
                return RegisterUtil.registerMessage(RegisterType.ORG_QUERY_ERROR, orgDeptInfo);
            }
            Map<String, Object> map = new TreeMap<>();
            map.clear();
            if(!StringUtil.isNullOrWhiteSpace(strDeptId))
                map.put("deptCode", strDeptId);
            if(!StringUtil.isNullOrWhiteSpace(strDeptName))
                map.put("deptName", strDeptName);
            if(StringUtil.isNullOrWhiteSpace(strDeptId)&&StringUtil.isNullOrWhiteSpace(strDeptName)){
                acknowledgement.setText("机构名称和机构代码为空！");
                OrgDeptInfo orgDeptInfo = new OrgDeptInfo();
                orgDeptInfo.setCreationTime(DateUtil.stringToDateTime(strCreateTime));
                orgDeptInfo.setDivisionRoot(strDivisionRoot);
                orgDeptInfo.setMsgId(strMsgId);
                orgDeptInfo.setDeptCode(strDeptId);
                orgDeptInfo.setDeptName(strDeptName);
                orgDeptInfo.setAcknowledgement(acknowledgement);
                return RegisterUtil.registerMessage(RegisterType.ORG_QUERY_ERROR, orgDeptInfo);
            }

            OrgDeptInfo result = organizationRegisterIn.organizationDetailQuery(map);

            return RegisterUtil.registerMessage(RegisterType.ORG_QUERY_SUCCESS, result);
        } catch (BusinessException e) {
            errorMsg = SafeExecuteUtil.getBusinessExceptionMsg(e, organizationRegisterIn.getClass());

        } catch (Exception ex) {
            errorMsg = ex.getMessage();
            logger.error("error:", ex);
        }
        OrgDeptInfo orgDeptInfo = new OrgDeptInfo();
        orgDeptInfo.setCreationTime(DateUtil.stringToDateTime(strCreateTime));
        orgDeptInfo.setDivisionRoot(strDivisionRoot);
        orgDeptInfo.setMsgId(strMsgId);
        orgDeptInfo.setDeptCode(strDeptId);
        orgDeptInfo.setDeptName(strDeptName);
        acknowledgement.setText(errorMsg);
        orgDeptInfo.setAcknowledgement(acknowledgement);
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
