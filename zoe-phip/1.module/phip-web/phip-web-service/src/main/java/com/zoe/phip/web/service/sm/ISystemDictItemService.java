/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.web.service.sm;

import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.module.service.service.in.IBaseInService;
import com.zoe.phip.web.model.sm.SystemDictItem;


import java.util.List;

/**
 * 对外发布的服务接口
 *
 * @author
 * @version 1.0
 * @date 2016-03-22
 */
public interface ISystemDictItemService extends IBaseInService<SystemDictItem> {

    /// <summary>
    /// 检查字典类别是否存在字典项
    /// </summary>
    /// <param name="categoryId"></param>
    /// <returns></returns>

    /**
     * 检查该字典类别是否存在
     *
     * @param systemData
     * @param categoryId
     * @return
     */
    ServiceResult categoryExists(SystemData systemData, String categoryId);

    /**
     * 分页获取字典类别下的字典项列表
     *
     * @param systemData
     * @param categoryId
     * @param key
     * @param page
     * @return
     */
    ServiceResultT<PageList<SystemDictItem>> getDictItems(SystemData systemData, String categoryId, String key, QueryPage page);

    /**
     * 获取字典类别下的字典项列表
     *
     * @param systemData
     * @param categoryId
     * @param key
     * @return
     */
    ServiceResultT<List<SystemDictItem>> getDictItems(SystemData systemData, String categoryId, String key);

    /**
     * 通过字典类别编码分页获取字典列表
     *
     * @param systemData
     * @param categoryCode
     * @param page
     * @return
     */
    ServiceResultT<PageList<SystemDictItem>> getDictItemsByCategoryCode(SystemData systemData, String categoryCode, QueryPage page);

    /**
     * 通过字典类别ID获取字典项
     *
     * @param systemData
     * @param categoryId
     * @param code
     * @return
     */
    ServiceResultT<SystemDictItem> getDictItemByCategoryId(SystemData systemData, String categoryId, String code);

    /**
     * 通过字典类别编码获取字典项
     *
     * @param systemData
     * @param categoryCode
     * @param code
     * @return
     */
    ServiceResultT<SystemDictItem> getDictItemByCategoryCode(SystemData systemData, String categoryCode, String code);

    /**
     * 通过字典类别编码获取字典项列表
     *
     * @param systemData
     * @param categoryCode
     * @return
     */
    ServiceResultT<List<SystemDictItem>> getDictItemsByCategoryCode(SystemData systemData, String categoryCode);


}