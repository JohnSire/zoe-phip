package com.zoe.phip.register.service.impl.internal;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.register.dao.IDictCatalogMapper;
import com.zoe.phip.register.dao.IDictItemMapper;
import com.zoe.phip.register.model.DictCatalog;
import com.zoe.phip.register.model.DictItem;
import com.zoe.phip.register.model.XmanBaseInfo;
import com.zoe.phip.register.service.internal.IDictRegisterIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

/**
 * Created by zengjiyang on 2016/4/22.
 */
@Repository("DictRegisterIn")
@Service(interfaceClass = IDictRegisterIn.class, proxy = "sdpf", protocol = {"dubbo"}, dynamic = true)
public class DictRegisterInImpl implements IDictRegisterIn {

    @Autowired
    private IDictCatalogMapper dictCatalogMapper;

    @Autowired
    private IDictItemMapper dictItemMapper;

    @Override
    public ServiceResultT<DictCatalog> addDictCatalogRequest(DictCatalog dictCatalog) {
        return SafeExecuteUtil.execute0(() -> {
            dictCatalogMapper.defaultAdd(dictCatalog);
            return dictCatalog;
        }, null);
    }

    @Override
    public ServiceResultT<DictCatalog> updateDictCatalogRequest(DictCatalog dictCatalog) {
        return SafeExecuteUtil.execute0(() -> {
            dictCatalogMapper.defaultUpdate(dictCatalog);
            return dictCatalog;
        }, null);
    }

    @Override
    public ServiceResultT<DictCatalog> dictCatalogDetailQuery(String dictCatalogCode) {
        return SafeExecuteUtil.execute0(()->{
            Example example = new Example(XmanBaseInfo.class);
            example.createCriteria().andEqualTo("code", dictCatalogCode);
            DictCatalog catalog=dictCatalogMapper.selectByExample(example).get(0);
            return catalog;
        },null);
    }

    @Override
    public ServiceResult dictCatalogDetailDelete(String catalogId) {
        return SafeExecuteUtil.execute0(()-> dictCatalogMapper.deleteByPrimaryKey(catalogId),null);
    }

    @Override
    public ServiceResultT<DictItem> addDictItemRequest(DictItem dictItem) {
        return SafeExecuteUtil.execute0(() -> {
            dictItemMapper.defaultAdd(dictItem);
            return dictItem;
        }, null);
    }

    @Override
    public ServiceResultT<DictItem> updateDictItemRequest(DictItem dictItem) {
        return SafeExecuteUtil.execute0(() -> {
            dictItemMapper.defaultUpdate(dictItem);
            return dictItem;
        }, null);
    }

    @Override
    public ServiceResultT<DictItem> dictItemDetailQuery(String dictItemCode) {
        return SafeExecuteUtil.execute0(()->{
            Example example = new Example(XmanBaseInfo.class);
            example.createCriteria().andEqualTo("code", dictItemCode);
            DictItem catalog=dictItemMapper.selectByExample(example).get(0);
            return catalog;
        },null);
    }

    @Override
    public ServiceResult dictItemDetailDelete(String dictItemId) {
        return SafeExecuteUtil.execute0(()-> dictItemMapper.deleteByPrimaryKey(dictItemId),null);
    }
}
