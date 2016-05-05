package com.zoe.phip.register.service.impl.external;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.register.service.external.IDocumentRegister;
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
        return null;
    }

    @Override
    public String retrieveDocumentSet(String message) {
        return null;
    }
}
