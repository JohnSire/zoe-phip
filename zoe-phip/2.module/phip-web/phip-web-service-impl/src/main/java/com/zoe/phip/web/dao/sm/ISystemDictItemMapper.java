/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.web.dao.sm;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.module.service.mapper.IMyMapper;
import com.zoe.phip.web.model.sm.SystemDictItem;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-03-22
 */
public interface ISystemDictItemMapper extends IMyMapper<SystemDictItem> {

    /**
     * 获取菜单列表
     *
     * @param map
     * @return
     */
    List<SystemDictItem> getDataItemList(Map map);

    /// <summary>
    /// 检查字典类别是否存在字典项
    /// </summary>
    /// <param name="categoryId"></param>
    /// <returns></returns>

    /**
     * 检查该字典类别是否存在
     *
     * @param categoryId
     * @return
     * @throws Exception
     */
    int categoryExists(String categoryId) throws Exception;

    /**
     * 通过关键字分页获取字典列表
     *
     * @param categoryId
     * @param key
     * @param page
     * @return
     * @throws Exception
     */
    PageList<SystemDictItem> getDictItems(String categoryId, String key, QueryPage page) throws Exception;

    /**
     * 通过关键字获取字典列表
     *
     * @param categoryId
     * @param key
     * @return
     * @throws Exception
     */
    List<SystemDictItem> getDictItems(String categoryId, String key) throws Exception;

    /**
     * 通过字典类别编码分页获取字典列表
     *
     * @param categoryCode
     * @param page
     * @return
     * @throws Exception
     */
    PageList<SystemDictItem> getDictItemsByCategoryCode(String categoryCode, QueryPage page) throws Exception;

    /**
     * 通过字典类别ID获取字典项
     *
     * @param categoryId
     * @param code
     * @return
     * @throws Exception
     */
    SystemDictItem getDictItemByCategoryId(String categoryId, String code) throws Exception;

    /**
     * 通过字典类别编码获取字典列表
     *
     * @param categoryCode
     * @param code
     * @return
     * @throws Exception
     */
    SystemDictItem getDictItemByCategoryCode(String categoryCode, String code) throws Exception;

    /**
     * 通过字典类别编码获取字典列表
     *
     * @param categoryCode
     * @return
     * @throws Exception
     */
    List<SystemDictItem> getDictItemsByCategoryCode(String categoryCode) throws Exception;

    SystemDictItem getSysDataItemByCode(String code);
}

