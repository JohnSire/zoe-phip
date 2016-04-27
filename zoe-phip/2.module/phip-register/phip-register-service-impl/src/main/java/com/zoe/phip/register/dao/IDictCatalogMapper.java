/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.register.dao;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.module.service.mapper.IMyMapper;
import com.zoe.phip.register.mapper.IServiceMapper;
import com.zoe.phip.register.model.DictCatalog;
import com.zoe.phip.register.model.DictItem;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-04-12
 */
public interface IDictCatalogMapper extends IServiceMapper<DictCatalog> {

    DictCatalog addDictCatalogRequest(DictCatalog dictCatalog) throws Exception;

    DictCatalog updateDictCatalogRequest(DictCatalog dictCatalog) throws Exception;

    DictCatalog dictCatalogDetailQuery(String dictCatalogCode) throws Exception;

    int dictCatalogDetailDelete(String catalogId);

    DictItem addDictItemRequest(DictItem dictItem) throws Exception;

    DictItem updateDictItemRequest(DictItem dictItem) throws Exception;

    DictItem dictItemDetailQuery(String dictItemCode) throws Exception;

    int dictItemDetailDelete(String dictItemId);

    PageList<DictCatalog> dictCatalogListQuery(String pId, QueryPage page, String key);

    PageList<DictItem> dictItemListQuery(String catalogCode, QueryPage page, String key);

    List<DictCatalog> getDictCatalogList(Map<String, Object> args);


}