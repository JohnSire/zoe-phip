/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.register.service.internal;


import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.entity.SystemData;
import com.zoe.phip.module.service.service.in.IBaseInService;
import com.zoe.phip.register.model.NationalStandards;

/**
 * 对外发布的服务接口
 *
 * @author
 * @version 1.0
 * @date 2016-04-25
 */
public interface INationalStandardsService extends IBaseInService<NationalStandards> {

    /**
     * 根据关键字查询国家标准
     * @param systemData
     * @param key
     * @param queryPage
     * @return
     */
    ServiceResultT<PageList<NationalStandards>> getDataPageList(SystemData systemData, String key, QueryPage queryPage);

}