/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.register.dao;

import com.zoe.phip.register.mapper.IServiceMapper;
import com.zoe.phip.register.model.DictItem;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-04-12
 */
public interface IDictItemMapper extends IServiceMapper<DictItem> {

    List<DictItem> getDictItemListByCatalogCode(Map<String, Object> args);

    List<DictItem> getDictItemListByCatalogId(Map<String, Object> args);

    List<DictItem> getDictItemOrgList(Map<String, Object> args);

    int dictItemExist(Map<String, Object> args);

    DictItem getDictItemById(Map<String, Object> args);

    List<DictItem> getDictItemNewOrgTree(Map<String, Object> args);

}