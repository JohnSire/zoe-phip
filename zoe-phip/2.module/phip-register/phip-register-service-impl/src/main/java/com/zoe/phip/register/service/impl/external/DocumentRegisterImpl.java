package com.zoe.phip.register.service.impl.external;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.register.model.base.Acknowledgement;
import com.zoe.phip.register.service.external.IDocumentRegister;
import com.zoe.phip.register.util.ProcessXmlUtil;
import com.zoe.phip.register.util.RegisterType;
import com.zoe.phip.register.util.RegisterUtil;
import org.dom4j.Document;
import org.springframework.stereotype.Repository;

/**
 * Created by zengjiyang on 2016/5/5.
 */
@Repository("DocumentRegister")
@Service(interfaceClass = IDocumentRegister.class, proxy = "sdpf", protocol = {"webservice"}, dynamic = true)
public class DocumentRegisterImpl implements IDocumentRegister {
    @Override
    public String provideAndRegisterDocumentSet(String message) {
        return null;
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
}
