/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.dao.sm;

import com.zoe.phip.dao.IMyMapper;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.model.sm.SystemDictItem;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-03-22
 */
public interface ISystemDictItemMapper extends IMyMapper<SystemDictItem> {


    List<SystemDictItem> getDataItemList(Map map);

    /// <summary>
    /// 检查字典类别是否存在字典项
    /// </summary>
    /// <param name="categoryId"></param>
    /// <returns></returns>
    int categoryExists(String categoryId) throws Exception;

    PageList<SystemDictItem> getDictItems(String categoryId, String key, QueryPage page) throws Exception;

    List<SystemDictItem> getDictItems(String categoryId, String key) throws Exception;

    PageList<SystemDictItem> getDictItemsByCategoryCode(String categoryCode, QueryPage page) throws Exception;

    SystemDictItem getDictItemByCategoryId(String categoryId, String code) throws Exception;

    SystemDictItem getDictItemByCategoryCode(String categoryCode, String code) throws Exception;

    List<SystemDictItem> getDictItemsByCategoryCode(String categoryCode) throws Exception;
}

