package com.zoe.phip.register.service.impl.internal;

import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.register.model.DictCatalog;
import com.zoe.phip.register.model.DictItem;
import com.zoe.phip.register.service.internal.IDictRegisterIn;

/**
 * Created by zengjiyang on 2016/4/22.
 */
public class DictRegisterInImpl implements IDictRegisterIn {
    @Override
    public ServiceResultT<DictCatalog> addDictCatalogRequest(DictCatalog message) {
        return null;
    }

    @Override
    public ServiceResultT<DictCatalog> updateDictCatalogRequest(DictCatalog message) {
        return null;
    }

    @Override
    public ServiceResultT<DictCatalog> dictCatalogDetailQuery(DictCatalog message) {
        return null;
    }

    @Override
    public ServiceResult dictCatalogDetailDelete(String catalogId) {
        return null;
    }

    @Override
    public ServiceResultT<DictItem> addDictItemRequest(DictItem message) {
        return null;
    }

    @Override
    public ServiceResultT<DictItem> updateDictItemRequest(DictItem message) {
        return null;
    }

    @Override
    public ServiceResultT<DictItem> dictItemDetailQuery(DictItem message) {
        return null;
    }

    @Override
    public ServiceResult dictItemDetailDelete(String dictItemId) {
        return null;
    }
}
