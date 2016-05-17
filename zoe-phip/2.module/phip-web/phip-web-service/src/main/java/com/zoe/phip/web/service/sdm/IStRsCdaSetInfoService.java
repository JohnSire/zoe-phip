/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.web.service.sdm;

import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.SystemData;
import com.zoe.phip.module.service.service.in.IBaseInService;
import com.zoe.phip.web.model.sdm.StRsCdaSetInfo;

import java.util.List;

/**
 * 对外发布的服务接口
 *
 * @author
 * @version 1.0
 * @date 2016-05-05
 */
public interface IStRsCdaSetInfoService extends IBaseInService<StRsCdaSetInfo> {
    ServiceResult updateByCdaId(SystemData systemData, String fkCdaId, List<StRsCdaSetInfo> infoList);

}