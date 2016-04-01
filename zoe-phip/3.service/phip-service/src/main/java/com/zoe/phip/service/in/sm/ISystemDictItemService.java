/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.service.in.sm;

import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.model.sm.SystemDictItem;
import com.zoe.phip.service.in.IBaseInService;

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
    ServiceResult categoryExists(SystemData systemData, String categoryId);

    ServiceResultT<PageList<SystemDictItem>> getDictItems(SystemData systemData, String categoryId, String key, QueryPage page);

    ServiceResultT<List<SystemDictItem>> getDictItems(SystemData systemData, String categoryId, String key);

    ServiceResultT<PageList<SystemDictItem>> getDictItemsByCategoryCode(SystemData systemData, String categoryCode, QueryPage page);

    ServiceResultT<SystemDictItem> getDictItemByCategoryId(SystemData systemData, String categoryId, String code);

    ServiceResultT<SystemDictItem> getDictItemByCategoryCode(SystemData systemData, String categoryCode, String code);

    ServiceResultT<List<SystemDictItem>> getDictItemsByCategoryCode(SystemData systemData, String categoryCode);


}