/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.web.service.sm;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.entity.SystemData;
import com.zoe.phip.module.service.service.in.IBaseInService;
import com.zoe.phip.web.model.sm.SystemDictCategory;


/**
 * 对外发布的服务接口
 *
 * @author
 * @version 1.0
 * @date 2016-03-22
 */
public interface ISystemDictCategoryService extends IBaseInService<SystemDictCategory> {

    /**
     * 获取字典类别列表
     *
     * @param systemData
     * @param key
     * @param queryPage
     * @return
     */
    ServiceResultT<PageList<SystemDictCategory>> getDictCategories(SystemData systemData, String key, QueryPage queryPage);

    /**
     * 通过编码获取唯一的菜单类别
     *
     * @param systemData
     * @param code
     * @return
     */
    ServiceResultT<SystemDictCategory> getDictCategory(SystemData systemData, String code);
}