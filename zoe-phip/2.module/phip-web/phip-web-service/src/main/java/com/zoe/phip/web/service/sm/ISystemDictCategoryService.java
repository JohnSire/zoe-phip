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

    ServiceResultT<PageList<SystemDictCategory>> getDictCategories(SystemData systemData, String key, QueryPage queryPage);

    ServiceResultT<SystemDictCategory> getDictCategory(SystemData systemData, String code);
}