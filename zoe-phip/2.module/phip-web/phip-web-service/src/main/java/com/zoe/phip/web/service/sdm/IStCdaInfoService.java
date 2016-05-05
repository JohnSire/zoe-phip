/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.web.service.sdm;


import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.module.service.service.in.IBaseInService;
import com.zoe.phip.web.model.sdm.StCdaInfo;
import com.zoe.phip.web.model.sdm.StRsCdaSetInfo;

import java.util.List;

/**
 * 对外发布的服务接口
 *
 * @author
 * @version 1.0
 * @date 2016-05-03
 */
public interface IStCdaInfoService extends IBaseInService<StCdaInfo> {
    /**
     * 根据关键字查询CDA列表信息
     *
     * @param systemData
     * @param key
     * @param queryPage
     * @return
     */
    ServiceResultT<PageList<StCdaInfo>> getDataPageList(SystemData systemData, String key, QueryPage queryPage);

    /**
     * CDA和数据集关系维护
     *
     * @param systemData
     * @param infoList   CDA和数据集关系实体
     * @return
     */
    ServiceResult cdaSetRsUpdate(SystemData systemData, List<StRsCdaSetInfo> infoList);

    ServiceResult updateByCdaId(SystemData systemData, String fkCdaId, List<StRsCdaSetInfo> infoList);

}