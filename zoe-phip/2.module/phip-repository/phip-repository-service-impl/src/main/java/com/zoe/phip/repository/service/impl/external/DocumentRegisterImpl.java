package com.zoe.phip.repository.service.impl.external;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.config.PropertyPlaceholder;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.DateUtil;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.module.service.util.ProcessXmlUtil;
import com.zoe.phip.module.service.util.RegisterUtil;
import com.zoe.phip.module.service.util.XmlBeanUtil;
import com.zoe.phip.module.service.entity.base.Acknowledgement;
import com.zoe.phip.repository.model.XmanEhr;
import com.zoe.phip.repository.model.XmanEhrContent;
import com.zoe.phip.repository.model.XmanIndex;
import com.zoe.phip.repository.service.external.IDocumentRegister;
import com.zoe.phip.repository.service.impl.internal.DocumentRegisterInImpl;
import com.zoe.phip.repository.util.RegisterType;
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
        if (document.selectNodes(PropertyPlaceholder.getProperty("provideDoc.submissionSet")).size() > 1) {
            return registerBatch(message);
        }
        String errorMsg = "";
        XmanIndex xmanIndex = null;
        XmanEhr xmanEhr = null;
        XmanEhrContent xmanEhrContent = null;
        try {
            //XmanIndex/XmamEhr/XmanEhrContent
            xmanIndex = XmlBeanUtil.toBean(document, XmanIndex.class, ProcessXmlUtil.getAdapterDom(xmanIndexAdapter));
            if(null != xmanIndex && null != xmanIndex.getValidateMessage()){
                throw new Exception(xmanIndex.getValidateMessage());
            }
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
            String msgId = document.selectSingleNode(PropertyPlaceholder.getProperty("getDocInfo.msgId")).getText().trim();
            String healthCardId = document.selectSingleNode(PropertyPlaceholder.getProperty("getDocInfo.healthId")).getText().trim();
            String identityId = document.selectSingleNode(PropertyPlaceholder.getProperty("getDocInfo.identityId")).getText().trim();
            String documentTitle = document.selectSingleNode(PropertyPlaceholder.getProperty("getDocInfo.title")).getText().trim();
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
        String msgId = document.selectSingleNode(PropertyPlaceholder.getProperty("getDocInfo.msgId")).getText().trim();//请求消息ID
        String healthCardId = document.selectSingleNode(PropertyPlaceholder.getProperty("getDocInfo.healthId")).getText().trim();//居民健康卡号
        String identityId = document.selectSingleNode(PropertyPlaceholder.getProperty("getDocInfo.identityId")).getText().trim(); //居民身份证号
        String documentTitle = document.selectSingleNode(PropertyPlaceholder.getProperty("getDocInfo.title")).getText().trim(); //标题
        XmanIndex xmanIndex=null;
        if (strResult != "success:数据集内容验证正确")
        {
            errorMsg="fail:"+strResult;
            return queryFailed(xmanIndex,acknowledgement,msgId,errorMsg);
        }
        try {
            //从数据库获取值
            xmanIndex=documentRegisterIn.documentRegistryQuery(healthCardId,identityId,documentTitle);
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
        String msgId = document.selectSingleNode(PropertyPlaceholder.getProperty("retrieveDoc.msgId")).getText().trim();//请求消息ID
        String repositoryUniqueId = document.selectSingleNode(PropertyPlaceholder.getProperty("retrieveDoc.repId")).getText().trim();
        String documentUniqueId = document.selectSingleNode(PropertyPlaceholder.getProperty("retrieveDoc.docId")).getText().trim();
        XmanIndex xmanIndex=null;
        if (strResult != "success:数据集内容验证正确")
        {
            xmanIndex=new XmanIndex();
            xmanIndex.setEhrId(documentUniqueId);
            errorMsg="fail:"+strResult;
            return docGetFailed(xmanIndex,acknowledgement,msgId,errorMsg);
        }
        try {
            //从数据库获取值
            xmanIndex=documentRegisterIn.getDocumentInfo(repositoryUniqueId,documentUniqueId);
            //请求成功
            if(StringUtil.isNullOrWhiteSpace(xmanIndex.getMsgId())){
                xmanIndex.setMsgId(msgId);
            }
            acknowledgement.setText("调阅成功");
            xmanIndex.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.EHR_GET_SUCCESS, xmanIndex);

        }catch (BusinessException e) {
            errorMsg = SafeExecuteUtil.getBusinessExceptionMsg(e, documentRegisterIn.getClass());
        } catch (Exception ex) {
            logger.error("error:", ex);
            errorMsg = ex.getMessage();
        }
        if(xmanIndex==null){
            xmanIndex=new XmanIndex();
            xmanIndex.setEhrId(documentUniqueId);
        }
        return docGetFailed(xmanIndex,acknowledgement,msgId,errorMsg);
    }


    /**
     * 档案检索失败返回
     * @param xmanIndex
     * @param acknowledgement
     * @param msgId
     * @param errorMsg
     * @return
     */
    private String queryFailed(XmanIndex xmanIndex,Acknowledgement acknowledgement,String msgId,String errorMsg){
        setFailed(xmanIndex,acknowledgement,msgId,errorMsg);
        return RegisterUtil.registerMessage(RegisterType.EHR_SEARCH_ERROR, xmanIndex);
    }

    /**
     * 档案检索失败返回
     * @param xmanIndex
     * @param acknowledgement
     * @param msgId
     * @param errorMsg
     * @return
     */
    private String docGetFailed(XmanIndex xmanIndex,Acknowledgement acknowledgement,String msgId,String errorMsg){
        setFailed(xmanIndex,acknowledgement,msgId,errorMsg);
        return RegisterUtil.registerMessage(RegisterType.EHR_GET_ERROR, xmanIndex);
    }

    private void setFailed(XmanIndex xmanIndex,Acknowledgement acknowledgement,String msgId,String errorMsg){
        if(xmanIndex==null){
            xmanIndex=new XmanIndex();
        }
        xmanIndex.setMsgId(msgId);
        acknowledgement.setText(errorMsg);
        xmanIndex.setAcknowledgement(acknowledgement);
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
            if(null != xmanIndex && null != xmanIndex.getValidateMessage()){
                throw new Exception(xmanIndex.getValidateMessage());
            }
            xmanEhr = XmlBeanUtil.toBean(document, XmanEhr.class, ProcessXmlUtil.getAdapterDom(xmanEhrAdapter));
            xmanEhrContent = XmlBeanUtil.toBean(document, XmanEhrContent.class, ProcessXmlUtil.getAdapterDom(xmanEhrContentAdapter));
            //xml 验证错误
            if (strResult.contains("error:数据集内容验证错误")) {
                return RegisterUtil.responseFailed(xmanIndex, strResult, RegisterType.EHR_BATCHADD_ERROR);
            }
            //数据抽取，保存到数据库
            XmanIndex result = documentRegisterIn.addDocumentRegistry(xmanIndex, xmanEhr, xmanEhrContent);
            StringBuffer registryResult = new StringBuffer();
            List<Node> registryNodes = document.selectNodes(PropertyPlaceholder.getProperty("provideDoc.submissionSet"));
            List<Node> documentNodes = document.selectNodes(PropertyPlaceholder.getProperty("provideDoc.document"));
            if (registryNodes.size() > 1) {
                for (int i = 1; i < registryNodes.size(); i++) {
                    Document doc = ProcessXmlUtil.load(registryNodes.get(i).asXML());
                    Document doc2 = ProcessXmlUtil.load(documentNodes.get(i).asXML());
                    xmanIndex.setId(null);
                    xmanIndex.setComments(doc.selectSingleNode(PropertyPlaceholder.getProperty("provideDoc.comments")).getText().trim());
                    xmanIndex.setTitle(doc.selectSingleNode(PropertyPlaceholder.getProperty("provideDoc.title")).getText().trim());
                    xmanIndex.setServerOrgName(doc.selectSingleNode(PropertyPlaceholder.getProperty("provideDoc.serverOrgName")).getText().trim());
                    xmanIndex.setAdmissionDepart(doc.selectSingleNode(PropertyPlaceholder.getProperty("provideDoc.admissionDepart")).getText().trim());
                    xmanIndex.setAdmissionDoctor(doc.selectSingleNode(PropertyPlaceholder.getProperty("provideDoc.admissionDoctor")).getText().trim());
                    xmanIndex.setAdmissionType(doc.selectSingleNode(PropertyPlaceholder.getProperty("provideDoc.admissionType")).getText().trim());
                    xmanIndex.setDiagnosisResult(doc.selectSingleNode(PropertyPlaceholder.getProperty("provideDoc.diagnosisResult")).getText().trim());
                    xmanIndex.setAuthorName(doc.selectSingleNode(PropertyPlaceholder.getProperty("provideDoc.authorName")).getText().trim());
                    xmanIndex.setAuthorInstitution(doc.selectSingleNode(PropertyPlaceholder.getProperty("provideDoc.authorInstitution")).getText().trim());
                    xmanIndex.setAuthorSpecialty(doc.selectSingleNode(PropertyPlaceholder.getProperty("provideDoc.authorSpecialty")).getText().trim());
                    xmanIndex.setAuthorRole(doc.selectSingleNode(PropertyPlaceholder.getProperty("provideDoc.authorRole")).getText().trim());
                    xmanIndex.setStartTime(DateUtil.stringToDateTime(doc.selectSingleNode(PropertyPlaceholder.getProperty("provideDoc.startTime")).getText().trim()));
                    xmanIndex.setEndTime(DateUtil.stringToDateTime(doc.selectSingleNode(PropertyPlaceholder.getProperty("provideDoc.endTime")).getText().trim()));
                    xmanIndex.setCreateTime(DateUtil.stringToDateTime(doc.selectSingleNode(PropertyPlaceholder.getProperty("provideDoc.createTime")).getText().trim()));

                    xmanEhr.setId(null);
                    xmanEhr.setSourceId(doc.selectSingleNode(PropertyPlaceholder.getProperty("provideDoc.sourceId")).getText().trim());
                    xmanEhr.setEventNo(doc.selectSingleNode(PropertyPlaceholder.getProperty("provideDoc.eventNo")).getText().trim());
                    xmanEhr.setDocumentId(doc2.selectSingleNode(PropertyPlaceholder.getProperty("provideDoc.documentId")).getText().trim());
                    xmanEhr.setParentDocumentRelationship(doc2.selectSingleNode(PropertyPlaceholder.getProperty("provideDoc.parentDocRelationship")).getText().trim());
                    xmanEhr.setParentDocumentId(doc2.selectSingleNode(PropertyPlaceholder.getProperty("provideDoc.parentDocId")).getText().trim());

                    xmanEhrContent.setId(null);
                    xmanEhrContent.setEventNo(doc.selectSingleNode(PropertyPlaceholder.getProperty("provideDoc.eventNo")).getText().trim());
                    xmanEhrContent.setContent(doc2.selectSingleNode(PropertyPlaceholder.getProperty("provideDoc.content")).getText().trim());

                    XmanIndex res = null;
                    res = documentRegisterIn.addDocumentRegistry(xmanIndex, xmanEhr, xmanEhrContent);
                    registryResult.append("<Response id=\"Document.");
                    registryResult.append(i + 1);
                    registryResult.append("\" documentUniqueId=\"");
                    registryResult.append(res.getEhrId());
                    registryResult.append("\" repositoryId=\"");
                    registryResult.append(res.getId());
                    registryResult.append("\" status=\"AA\" doumentUrl=\"\">\n");
                    registryResult.append("    <Detail>注册成功</Detail>\n");
                    registryResult.append("  </Response>\n");
                    /*registryResult += "<Response id=\"Document." + (i + 1) + "\" documentUniqueId=\"" + res.getEhrId() + "\" repositoryId=\"" + res.getId() + "\" status=\"AA\" doumentUrl=\"\">\n" +
                            "    <Detail>注册成功</Detail>\n" +
                            "  </Response>\n";*/
                }
            }
            //组装响应信息
            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("注册成功");
            acknowledgement.setMsgId(StringUtil.getUUID());
            result.setAcknowledgement(acknowledgement);
            strResult = RegisterUtil.registerMessage(RegisterType.EHR_BATCHADD_SUCCESS, result);
            StringBuilder sb = new StringBuilder(strResult);
            sb.insert(strResult.length() - 21, registryResult.toString());
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
