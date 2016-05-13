/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.web.service.sdm;


import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.entity.SystemData;
import com.zoe.phip.module.service.service.in.IBaseInService;
import com.zoe.phip.web.model.sdm.StSetInfo;
import com.zoe.phip.web.model.sdm.StandardVerRsSet;

import java.util.List;

/**
 * 对外发布的服务接口
 *
 * @author
 * @version 1.0
 * @date 2016-05-04
 */
public interface IStandardVerRsSetService extends IBaseInService<StandardVerRsSet> {

    ServiceResult versionStandardStruct(SystemData systemData, String fkVersionId, List<StandardVerRsSet> setList);


    /**
     * 通过标准版本ID和CDA的ID获取数据集
     * @param systemData
     * @param fkVersionId
     * @param fkCdaId
     * @return
     */
    ServiceResultT<List<StSetInfo>> getVerRsSetInfo(SystemData systemData,String fkVersionId,String fkCdaId);
}