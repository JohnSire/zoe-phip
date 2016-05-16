/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.web.service.sdm;

import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.module.service.service.in.IBaseInService;
import com.zoe.phip.web.model.sdm.StElementInfo;
import com.zoe.phip.web.model.sdm.StNormSourceInfo;

import java.util.List;

/**
 * 对外发布的服务接口
 *
 * @author
 * @version 1.0
 * @date 2016-05-03
 */
public interface IStElementInfoService extends IBaseInService<StElementInfo> {


    ServiceResultT<PageList<StElementInfo>> getDataPageList(SystemData systemData, String key, QueryPage queryPage);


    /**
     * 导出数据元
     * @param systemData
     * @param fkSourceId
     * @return
     */
    ServiceResultT<List<StElementInfo>> exportElement(SystemData systemData, String fkSourceId);

    /**
     * 导入数据元
     *
     * @param systemData
     * @param infoList
     * @return
     */
    ServiceResult importElement(SystemData systemData, List<StElementInfo> infoList);
}