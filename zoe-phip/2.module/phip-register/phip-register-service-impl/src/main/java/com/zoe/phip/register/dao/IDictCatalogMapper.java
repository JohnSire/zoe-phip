/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.register.dao;

import com.zoe.phip.module.service.mapper.IMyMapper;
import com.zoe.phip.register.mapper.IServiceMapper;
import com.zoe.phip.register.model.DictCatalog;
import com.zoe.phip.register.model.DictItem;

/**
 * @author
 * @version 1.0
 * @date 2016-04-12
 */
public interface IDictCatalogMapper extends IServiceMapper<DictCatalog> {

    DictCatalog addDictCatalogRequest(DictCatalog dictCatalog) throws Exception;

    DictCatalog updateDictCatalogRequest(DictCatalog dictCatalog) throws Exception;

    DictCatalog dictCatalogDetailQuery(String dictCatalogCode);

    int dictCatalogDetailDelete(String catalogId);

    DictItem addDictItemRequest(DictItem dictItem);

    DictItem updateDictItemRequest(DictItem dictItem);

    DictItem dictItemDetailQuery(String dictItemCode);

    int dictItemDetailDelete(String dictItemId);
}