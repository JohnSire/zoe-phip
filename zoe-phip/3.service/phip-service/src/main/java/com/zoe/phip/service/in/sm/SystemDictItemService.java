/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.service.in.sm;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.model.sm.SystemDictItem;
import com.zoe.phip.service.in.BaseInService;

import java.util.List;

/**
 * 对外发布的服务接口
 *
 * @author
 * @version 1.0
 * @date 2016-03-22
 */
public interface SystemDictItemService extends BaseInService<SystemDictItem> {

    /// <summary>
    /// 检查字典类别是否存在字典项
    /// </summary>
    /// <param name="categoryId"></param>
    /// <returns></returns>
    ServiceResult categoryExists(String categoryId);

    ServiceResultT<PageList<SystemDictItem>> getDictItems(String categoryId, String key, QueryPage page);

    ServiceResultT<List<SystemDictItem>> getDictItems(String categoryId, String key);

    ServiceResultT<PageList<SystemDictItem>> getDictItemsByCategoryCode(String categoryCode, QueryPage page);

    ServiceResultT<SystemDictItem>  getDictItemByCategoryId(String categoryId, String code);

    ServiceResultT<SystemDictItem>  getDictItemByCategoryCode(String categoryCode, String code);

    ServiceResultT<List<SystemDictItem>>  getDictItemsByCategoryCode(String categoryCode);


}