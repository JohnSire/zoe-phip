package com.zoe.phip.register.service.impl.external;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.util.XmlBeanUtil;
import com.zoe.phip.register.model.DictCatalog;
import com.zoe.phip.register.service.external.IDictRegister;
import com.zoe.phip.register.service.impl.internal.DictRegisterInImpl;
import com.zoe.phip.register.service.internal.IDictRegisterIn;
import com.zoe.phip.register.util.ProcessXmlUtil;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by zengjiyang on 2016/4/11.
 */
@Repository("DictRegister")
@Service(interfaceClass = IDictRegister.class, proxy = "sdpf", protocol = {"webservice"}, dynamic = true)
public class DictRegisterImpl implements IDictRegister {

    private static final Logger logger = LoggerFactory.getLogger(DictRegisterImpl.class);
    private final String catalogAdapterPath = "/template/dict/input/adapter/DictCatalogAdapter.xml";
    private final String itemAdapterPath = "/template/dict/input/adapter/DictItemAdapter.xml";

    @Autowired
    private DictRegisterInImpl dictRegisterIn;

    @Override
    public String addDictCatalogRequest(String message) {
        Document document = ProcessXmlUtil.load(message);
        SAXReader reader = new SAXReader();
        Document parserDoc = null;
        DictCatalog catalog;
        try {
            parserDoc = reader.read(this.getClass().getResourceAsStream(catalogAdapterPath));
            catalog = XmlBeanUtil.toBean(document, DictCatalog.class, parserDoc);
            DictCatalog result = dictRegisterIn.addDictCatalogRequest(catalog);
        } catch (Exception e) {
            logger.error("error", e);
            return "新增失败:" + e.getMessage();
        }
        return "新增成功";
    }

    @Override
    public String updateDictCatalogRequest(String message) {
        return null;
    }

    @Override
    public String dictCatalogDetailQuery(String message) {
        return null;
    }

    @Override
    public String dictCatalogDetailDelete(String message) {
        return null;
    }

    @Override
    public String addDictItemRequest(String message) {
        return null;
    }

    @Override
    public String updateDictItemRequest(String message) {
        return null;
    }

    @Override
    public String dictItemDetailQuery(String message) {
        return null;
    }

    @Override
    public String dictItemDetailDelete(String message) {
        return null;
    }
}
