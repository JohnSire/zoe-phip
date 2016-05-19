/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.web.service.sdm;


import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.module.service.service.in.IBaseInService;
import com.zoe.phip.web.model.sdm.StNormSourceInfo;

/**
 * 对外发布的服务接口
 *
 * @author
 * @version 1.0
 * @date 2016-05-03
 */
public interface IStNormSourceInfoService extends IBaseInService<StNormSourceInfo> {

    ServiceResultT<PageList<StNormSourceInfo>> getDataPageList(SystemData systemData, String key, QueryPage queryPage);


    ServiceResultT<PageList<StNormSourceInfo>> getDataPageByTypeList(SystemData systemData, String type, String key, QueryPage queryPage);


    ServiceResult addNormSource(SystemData systemData, StNormSourceInfo stNormSourceInfo);

    ServiceResultT<StNormSourceInfo> getNormById(SystemData systemData, String id);
}
