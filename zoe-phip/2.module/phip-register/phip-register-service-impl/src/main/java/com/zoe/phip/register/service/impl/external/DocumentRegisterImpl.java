package com.zoe.phip.register.service.impl.external;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.DateUtil;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.infrastructure.util.XmlBeanUtil;
import com.zoe.phip.register.model.XmanEhr;
import com.zoe.phip.register.model.XmanEhrContent;
import com.zoe.phip.register.model.XmanIndex;
import com.zoe.phip.register.model.base.Acknowledgement;
import com.zoe.phip.register.service.external.IDocumentRegister;
import com.zoe.phip.register.service.impl.internal.DocumentRegisterInImpl;
import com.zoe.phip.register.util.ProcessXmlUtil;
import com.zoe.phip.register.util.RegisterType;
import com.zoe.phip.register.util.RegisterUtil;
import org.dom4j.Document;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zengjiyang on 2016/5/5.
 */
@Repository("DocumentRegister")
@Service(interfaceClass = IDocumentRegister.class, proxy = "sdpf", protocol = {"webservice"}, dynamic = true)
public class DocumentRegisterImpl implements IDocumentRegister {

    private static final Logger logger = LoggerFactory.getLogger(DocumentRegisterImpl.class);
    private final String xmanIndexAdapter = "/template/document/input/adapter/XmanIndexRegisterAdapter.xml";
    private final String xmanEhrAdapter = "/template/document/input/adapter/XmanEhrRegisterAdapter.xml";
    private final String xmanEhrContentAdapter = "/template/document/input/adapter/XmanEhrContentRegisterAdapter.xml";
    @Autowired
    private DocumentRegisterInImpl documentRegisterIn;

    @Override
    public String provideAndRegisterDocumentSet(String message) {

        String strResult = ProcessXmlUtil.verifyMessage(message);
        Acknowledgement acknowledgement = new Acknowledgement();
        //xml格式错误
        if (strResult.contains("error:传入的参数不符合xml格式")) {
            acknowledgement.setTypeCode("AE");
            acknowledgement.setText(strResult);
            return RegisterUtil.registerMessage(RegisterType.MESSAGE, acknowledgement);
        }

        Document document = ProcessXmlUtil.load(message);
        if (document.selectNodes("//RegistryPackage/SubmissionSet").size() > 1) {
            return registerBatch(message);
        }
        String errorMsg = "";
        XmanIndex xmanIndex = null;
        XmanEhr xmanEhr = null;
        XmanEhrContent xmanEhrContent = null;
        try {
            //XmanIndex/XmamEhr/XmanEhrContent
            xmanIndex = XmlBeanUtil.toBean(document, XmanIndex.class, ProcessXmlUtil.getAdapterDom(xmanIndexAdapter));
            xmanEhr = XmlBeanUtil.toBean(document, XmanEhr.class, ProcessXmlUtil.getAdapterDom(xmanEhrAdapter));
            xmanEhrContent = XmlBeanUtil.toBean(document, XmanEhrContent.class, ProcessXmlUtil.getAdapterDom(xmanEhrContentAdapter));

            //xml 验证错误
            if (strResult.contains("error:数据集内容验证错误")) {
                return registerFailed(xmanIndex, strResult);
            }

            XmanIndex result = documentRegisterIn.addDocumentRegistry(xmanIndex, xmanEhr, xmanEhrContent);
            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("注册成功");
            acknowledgement.setMsgId(StringUtil.getUUID());
            result.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.EHR_ADD_SUCCESS, result);
        } catch (BusinessException e) {
            errorMsg = SafeExecuteUtil.getBusinessExceptionMsg(e, documentRegisterIn.getClass());
        } catch (Exception ex) {
            logger.error("error:", ex);
            errorMsg = ex.getMessage();
        }
        acknowledgement.setTypeCode("AE");
        acknowledgement.setMsgId(StringUtil.getUUID());
        xmanIndex.setAcknowledgement(acknowledgement);
        return registerFailed(xmanIndex, errorMsg);
    }

    @Override
    public String documentExistence(String message) {
        String strResult = ProcessXmlUtil.verifyMessage(message);
        Acknowledgement acknowledgement = new Acknowledgement();
        //xml格式错误
        if (strResult.contains("error:传入的参数不符合xml格式")) {
            acknowledgement.setTypeCode("AE");
            acknowledgement.setText(strResult);
            acknowledgement.setMsgId(StringUtil.getUUID());
            return RegisterUtil.registerMessage(RegisterType.MESSAGE, acknowledgement);
        }
        String errorMsg = "";
        try {
            Document document = ProcessXmlUtil.load(message);
            String msgId = document.selectSingleNode("//Id/@extension").getText();
            String healthCardId = document.selectSingleNode("//HealthCardId").getText();
            String identityId = document.selectSingleNode("//IdentityId").getText();
            String documentTitle = document.selectSingleNode("//DocumentTitle").getText();
            acknowledgement.setId(msgId);
            if (strResult.contains("error:数据集内容验证错误")) {
                acknowledgement.setTypeCode("AE");
                acknowledgement.setMsgId(StringUtil.getUUID());
                acknowledgement.setText(strResult + ",档案预判失败");
                return RegisterUtil.registerMessage(RegisterType.EHR_ANTICIPATION_ERROR, acknowledgement);
            }

            XmanIndex xmanIndex = documentRegisterIn.documentRegistryQuery(healthCardId, identityId, documentTitle);
            xmanIndex.setMsgId(msgId);
            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("档案调阅预判成功");
            acknowledgement.setMsgId(StringUtil.getUUID());
            xmanIndex.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.EHR_ANTICIPATION_SUCCESS, xmanIndex);
        } catch (BusinessException e) {
            errorMsg = SafeExecuteUtil.getBusinessExceptionMsg(e, documentRegisterIn.getClass());
        } catch (Exception ex) {
            logger.error("error:", ex);
            acknowledgement.setTypeCode("AE");
            errorMsg = ex.getMessage();
        }
        acknowledgement.setMsgId(StringUtil.getUUID());
        acknowledgement.setText(errorMsg);
        return RegisterUtil.registerMessage(RegisterType.EHR_ANTICIPATION_ERROR, acknowledgement);
    }

    /**
     * 档案检索服务
     * @param message
     * @return
     */
    @Override
    public String getDocumentSetRetrieveInfo(String message) {
        String strResult = ProcessXmlUtil.verifyMessage(message);
        Acknowledgement acknowledgement = new Acknowledgement();
        acknowledgement.setMsgId(StringUtil.getUUID());
        String errorMsg = "";
        //xml格式错误
        if (strResult.contains("error:传入的参数不符合xml格式")) {
            acknowledgement.setTypeCode("AE");
            acknowledgement.setText(strResult);
            return RegisterUtil.registerMessage(RegisterType.MESSAGE, acknowledgement);
        }

        Document document = ProcessXmlUtil.load(message);
        String msgId = document.selectSingleNode("//GetDocumentStroedInfoRequest/Id/@extension").getText();//请求消息ID
        String healthCardId = document.selectSingleNode("//GetDocumentStroedInfoRequest/HealthCardId").getText();;//居民健康卡号
        String identityId = document.selectSingleNode("//GetDocumentStroedInfoRequest/IdentityId").getText();; //居民身份证号
        String documentTitle = document.selectSingleNode("//GetDocumentStroedInfoRequest/DocumentTitle").getText();; //标题
        XmanIndex xmanIndex=null;
        if (strResult != "success:数据集内容验证正确")
        {
            errorMsg="fail:由于查询内容不存在";
            return queryFailed(xmanIndex,acknowledgement,msgId,errorMsg);
        }
        try {
            //从数据库获取值
            xmanIndex=documentRegisterIn.documentRegistryQuery(healthCardId,identityId,documentTitle);
            //响应失败

            //请求成功
            if(StringUtil.isNullOrWhiteSpace(xmanIndex.getMsgId())){
                xmanIndex.setMsgId(msgId);
            }
            acknowledgement.setText("检索成功");
            xmanIndex.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.EHR_SEARCH_SUCCESS, xmanIndex);

        }catch (BusinessException e) {
            errorMsg = SafeExecuteUtil.getBusinessExceptionMsg(e, documentRegisterIn.getClass());
        } catch (Exception ex) {
            logger.error("error:", ex);
            errorMsg = ex.getMessage();
        }
        return queryFailed(xmanIndex,acknowledgement,msgId,errorMsg);
    }

    /**
     * 档案调阅服务
     * @param message
     * @return
     */
    @Override
    public String retrieveDocumentSet(String message) {
        return null;
    }


    private String queryFailed(XmanIndex xmanIndex,Acknowledgement acknowledgement,String msgId,String errorMsg){
        if(xmanIndex==null){
            xmanIndex=new XmanIndex();
        }
        xmanIndex.setMsgId(msgId);
        acknowledgement.setText(errorMsg);
        xmanIndex.setAcknowledgement(acknowledgement);
        return RegisterUtil.registerMessage(RegisterType.EHR_SEARCH_ERROR, xmanIndex);
    }


    /**
     * 注册失败返回值
     *
     * @param xmanIndex
     * @param errorMsg
     * @return
     */
    private String registerFailed(XmanIndex xmanIndex, String errorMsg) {
        return RegisterUtil.responseFailed(xmanIndex, errorMsg, RegisterType.EHR_ADD_ERROR);
    }

    /**
     * 批量提交档案
     *
     * @param message
     * @return
     */
    private String registerBatch(String message) {
        String strResult = ProcessXmlUtil.verifyMessage(message);
        Acknowledgement acknowledgement = new Acknowledgement();
        //xml格式错误
        if (strResult.contains("error:传入的参数不符合xml格式")) {
            acknowledgement.setTypeCode("AE");
            acknowledgement.setText(strResult);
            return RegisterUtil.registerMessage(RegisterType.MESSAGE, acknowledgement);
        }
        Document document = ProcessXmlUtil.load(message);
        String errorMsg = "";
        XmanIndex xmanIndex = null;
        XmanEhr xmanEhr = null;
        XmanEhrContent xmanEhrContent = null;
        try {
            //XmanIndex/XmamEhr/XmanEhrContent
            xmanIndex = XmlBeanUtil.toBean(document, XmanIndex.class, ProcessXmlUtil.getAdapterDom(xmanIndexAdapter));
            xmanEhr = XmlBeanUtil.toBean(document, XmanEhr.class, ProcessXmlUtil.getAdapterDom(xmanEhrAdapter));
            xmanEhrContent = XmlBeanUtil.toBean(document, XmanEhrContent.class, ProcessXmlUtil.getAdapterDom(xmanEhrContentAdapter));
            //xml 验证错误
            if (strResult.contains("error:数据集内容验证错误")) {
                return RegisterUtil.responseFailed(xmanIndex, strResult, RegisterType.EHR_BATCHADD_ERROR);
            }
            //数据抽取，保存到数据库
            XmanIndex result = documentRegisterIn.addDocumentRegistry(xmanIndex, xmanEhr, xmanEhrContent);
            String registryResult = "";
            List<Node> registryNodes = document.selectNodes("//RegistryPackage/SubmissionSet");
            List<Node> documentNodes = document.selectNodes("//Document");
            if (registryNodes.size() > 1) {
                for (int i = 1; i < registryNodes.size(); i++) {
                    xmanIndex.setId(null);
                    xmanIndex.setComments(registryNodes.get(i).selectSingleNode("//RegistryPackage/SubmissionSet/Comments").getText());
                    xmanIndex.setTitle(registryNodes.get(i).selectSingleNode("//RegistryPackage/SubmissionSet/Title").getText());
                    xmanIndex.setServerOrgName(registryNodes.get(i).selectSingleNode("//RegistryPackage/SubmissionSet/ServerOrganization").getText());
                    xmanIndex.setAdmissionDepart(registryNodes.get(i).selectSingleNode("//RegistryPackage/SubmissionSet/AdmissionDepart").getText());
                    xmanIndex.setAdmissionDoctor(registryNodes.get(i).selectSingleNode("//RegistryPackage/SubmissionSet/AdmissionDoctor").getText());
                    xmanIndex.setAdmissionType(registryNodes.get(i).selectSingleNode("//RegistryPackage/SubmissionSet/AdmissionType").getText());
                    xmanIndex.setDiagnosisResult(registryNodes.get(i).selectSingleNode("//RegistryPackage/SubmissionSet/DiagnosisResult").getText());
                    xmanIndex.setAuthorName(registryNodes.get(i).selectSingleNode("//RegistryPackage/SubmissionSet/Author/AuthorName").getText());
                    xmanIndex.setAuthorInstitution(registryNodes.get(i).selectSingleNode("//RegistryPackage/SubmissionSet/Author/AuthorInstitution").getText());
                    xmanIndex.setAuthorSpecialty(registryNodes.get(i).selectSingleNode("//RegistryPackage/SubmissionSet/Author/AuthorSpecialty").getText());
                    xmanIndex.setAuthorRole(registryNodes.get(i).selectSingleNode("//RegistryPackage/SubmissionSet/Author/AuthorRole").getText());
                    xmanIndex.setStartTime(DateUtil.stringToDateTime(registryNodes.get(i).selectSingleNode("//RegistryPackage/SubmissionSet/InTime").getText()));
                    xmanIndex.setEndTime(DateUtil.stringToDateTime(registryNodes.get(i).selectSingleNode("//RegistryPackage/SubmissionSet/OutTime").getText()));
                    xmanIndex.setCreateTime(DateUtil.stringToDateTime(registryNodes.get(i).selectSingleNode("//RegistryPackage/SubmissionSet/CreateTime").getText()));

                    xmanEhr.setId(null);
                    xmanEhr.setSourceId(registryNodes.get(i).selectSingleNode("//RegistryPackage/SubmissionSet/SourceId").getText());
                    xmanEhr.setEventNo(registryNodes.get(i).selectSingleNode("//RegistryPackage/SubmissionSet/EpisodeID").getText());
                    xmanEhr.setDocumentId(documentNodes.get(i).selectSingleNode("//Document/@id").getText());
                    xmanEhr.setParentDocumentRelationship(documentNodes.get(i).selectSingleNode("//Document/@parentDocumentRelationship").getText());
                    xmanEhr.setParentDocumentId(documentNodes.get(i).selectSingleNode("//Document/@parentDocumentId").getText());

                    xmanEhrContent.setId(null);
                    xmanEhrContent.setEventNo(registryNodes.get(i).selectSingleNode("//RegistryPackage/SubmissionSet/EpisodeID").getText());
                    xmanEhrContent.setContent(documentNodes.get(i).selectSingleNode("//Document/Content").getText());

                    XmanIndex res = null;
                    res = documentRegisterIn.addDocumentRegistry(xmanIndex, xmanEhr, xmanEhrContent);
                    registryResult += "<Response id=\"Document." + (i + 1) + "\" documentUniqueId=\"" + res.getEhrId() + "\" repositoryId=\"" + res.getId() + "\" status=\"AA\" doumentUrl=\"\">\n" +
                            "    <Detail>注册成功</Detail>\n" +
                            "  </Response>\n";
                }
            }
            //组装响应信息
            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("注册成功");
            acknowledgement.setMsgId(StringUtil.getUUID());
            result.setAcknowledgement(acknowledgement);
            strResult = RegisterUtil.registerMessage(RegisterType.EHR_BATCHADD_SUCCESS, result);
            StringBuilder sb = new StringBuilder(strResult);
            sb.insert(strResult.length() - 21, registryResult);
            return sb.toString();
        } catch (BusinessException e) {
            errorMsg = SafeExecuteUtil.getBusinessExceptionMsg(e, documentRegisterIn.getClass());
        } catch (Exception ex) {
            logger.error("error:", ex);
            errorMsg = ex.getMessage();
        }
        acknowledgement.setTypeCode("AE");
        acknowledgement.setMsgId(StringUtil.getUUID());
        xmanIndex.setAcknowledgement(acknowledgement);
        return RegisterUtil.responseFailed(xmanIndex, errorMsg, RegisterType.EHR_BATCHADD_ERROR);
    }
}
