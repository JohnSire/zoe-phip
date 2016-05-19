/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.web.service.sdm;

import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.module.service.service.in.IBaseInService;
import com.zoe.phip.web.model.sdm.StRsSetElementInfo;
import com.zoe.phip.web.model.sdm.StSetInfo;

import java.util.List;

/**
 * 对外发布的服务接口
 *
 * @author
 * @version 1.0
 * @date 2016-05-03
 */
public interface IStSetInfoService extends IBaseInService<StSetInfo> {
    /**
     * 根据关键字查询数据集列表
     *
     * @param systemData
     * @param key
     * @param queryPage
     * @return
     */
    ServiceResultT<PageList<StSetInfo>> getDataPageList(SystemData systemData, String key, QueryPage queryPage);

    ServiceResultT<PageList<StSetInfo>> getByPid(SystemData systemData, String pid,String Keyword, QueryPage queryPage);

    ServiceResultT<PageList<StSetInfo>> getByCdaId(SystemData systemData, String fkCdaId, String key, QueryPage queryPage);

    ServiceResult rsSetElementAdd(SystemData systemData, StRsSetElementInfo info);

    ServiceResult rsSetElementUpdate(SystemData systemData, StRsSetElementInfo info);

    ServiceResult rsSetElementDel(SystemData systemData, String id);

    ServiceResult importSetAndRsElement(SystemData systemData, List<StSetInfo> setInfoList, List<StRsSetElementInfo> elementInfoList);

    ServiceResult importRsSetElementInfo(SystemData systemData, List<StRsSetElementInfo> infoList);

    /**
     * 获取数据集详情关联（标准来源表）
     * @return
     */
    ServiceResult getRelationById(String id );
}