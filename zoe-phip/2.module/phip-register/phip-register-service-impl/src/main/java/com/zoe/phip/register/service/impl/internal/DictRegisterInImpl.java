package com.zoe.phip.register.service.impl.internal;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.register.model.DictCatalog;
import com.zoe.phip.register.model.DictItem;
import com.zoe.phip.register.service.internal.IDictRegisterIn;
import org.springframework.stereotype.Repository;

/**
 * Created by zengjiyang on 2016/4/22.
 */
@Repository("DictRegisterIn")
@Service(interfaceClass = IDictRegisterIn.class, proxy = "sdpf",protocol = {"dubbo"},dynamic = true)
public class DictRegisterInImpl implements IDictRegisterIn {
    @Override
    public ServiceResultT<DictCatalog> addDictCatalogRequest(DictCatalog dictCatalog) {
        return null;
    }

    @Override
    public ServiceResultT<DictCatalog> updateDictCatalogRequest(DictCatalog dictCatalog) {
        return null;
    }

    @Override
    public ServiceResultT<DictCatalog> dictCatalogDetailQuery(DictCatalog dictCatalog) {
        return null;
    }

    @Override
    public ServiceResult dictCatalogDetailDelete(String catalogId) {
        return null;
    }

    @Override
    public ServiceResultT<DictItem> addDictItemRequest(DictItem dictItem) {
        return null;
    }

    @Override
    public ServiceResultT<DictItem> updateDictItemRequest(DictItem dictItem) {
        return null;
    }

    @Override
    public ServiceResultT<DictItem> dictItemDetailQuery(DictItem dictItem) {
        return null;
    }

    @Override
    public ServiceResult dictItemDetailDelete(String dictItemId) {
        return null;
    }
}
