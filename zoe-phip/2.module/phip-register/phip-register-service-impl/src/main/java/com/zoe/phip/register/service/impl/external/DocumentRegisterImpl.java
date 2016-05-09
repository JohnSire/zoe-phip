package com.zoe.phip.register.service.impl.external;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.infrastructure.util.UtilString;
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
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        String errorMsg = "";
        XmanIndex xmanIndex = null;
        XmanEhr xmanEhr = null;
        XmanEhrContent xmanEhrContent = null;
        try {
            SAXReader reader = new SAXReader();
            //XmanIndex/XmamEhr/XmanEhrContent
            Document parserDoc = reader.read(this.getClass().getResourceAsStream(xmanIndexAdapter));
            xmanIndex = XmlBeanUtil.toBean(document, XmanIndex.class, parserDoc);

            Document xmanEhrParserDoc = reader.read(this.getClass().getResourceAsStream(xmanEhrAdapter));
            xmanEhr = XmlBeanUtil.toBean(document, XmanEhr.class, xmanEhrParserDoc);

            Document xmanEhrContentParserDoc = reader.read(this.getClass().getResourceAsStream(xmanEhrContentAdapter));
            xmanEhrContent = XmlBeanUtil.toBean(document, XmanEhrContent.class, xmanEhrContentParserDoc);

            //xml 验证错误
            if (strResult.contains("error:数据集内容验证错误")) {
                return registerFailed(xmanIndex, strResult);
            }

            XmanIndex result = documentRegisterIn.addDocumentRegistry(xmanIndex, xmanEhr, xmanEhrContent);
            acknowledgement.setTypeCode("AA");
            acknowledgement.setText("注册成功");
            xmanIndex.setAcknowledgement(acknowledgement);
            return RegisterUtil.registerMessage(RegisterType.EHR_ADD_SUCCESS, result);
        } catch (BusinessException e) {
            errorMsg = SafeExecuteUtil.getBusinessExceptionMsg(e, documentRegisterIn.getClass());
        } catch (Exception ex) {
            logger.error("error:", ex);
            errorMsg = ex.getMessage();
        }
        return registerFailed(xmanIndex, errorMsg);
    }

    @Override
    public String documentExistence(String message) {
        return null;
    }

    @Override
    public String getDocumentSetRetrieveInfo(String message) {
        String strResult = ProcessXmlUtil.verifyMessage(message);
        Acknowledgement acknowledgement = new Acknowledgement();
        String errorMsg = "";
        //xml格式错误
        if (strResult.contains("error:传入的参数不符合xml格式")) {
            acknowledgement.setTypeCode("AE");
            acknowledgement.setText(strResult);
            return RegisterUtil.registerMessage(RegisterType.MESSAGE, acknowledgement);
        }
        Document document = ProcessXmlUtil.load(message);
        String strMsgId = document.selectSingleNode("//GetDocumentStroedInfoRequest/Id/@extension").getText();//请求消息ID
        String strHealthCardId = document.selectSingleNode("//GetDocumentStroedInfoRequest/HealthCardId").getText();;//居民健康卡号
        String strIdentityId = document.selectSingleNode("//GetDocumentStroedInfoRequest/Id/@extension").getText();; //居民身份证号
        String strDocumentTitle = document.selectSingleNode("//GetDocumentStroedInfoRequest/Id/@extension").getText();; //标题



        return null;
    }

    @Override
    public String retrieveDocumentSet(String message) {
        return null;
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
}
