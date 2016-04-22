package com.zoe.phip.register.service.impl.external;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.register.dao.IDictCatalogMapper;
import com.zoe.phip.register.model.DictCatalog;
import com.zoe.phip.register.service.external.IDictRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by zengjiyang on 2016/4/11.
 */
@Repository("DictRegister")
@Service(interfaceClass = IDictRegister.class, proxy = "sdpf", dynamic = true)
public class DictRegisterImpl implements IDictRegister {

    @Autowired
    private IDictCatalogMapper dictCatalogMapper;

    @Override
    public String addDictCatalogRequest(String message) {
        DictCatalog catalog=new DictCatalog();
        catalog.setName("test");
        catalog.setId("2");
        catalog.setCode("123");
        catalog.setType(12);
        dictCatalogMapper.insertSelective(catalog);
        return null;
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
