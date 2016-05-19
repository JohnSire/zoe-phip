/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.web.service.sdm;


import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.module.service.service.in.IBaseInService;
import com.zoe.phip.web.model.sdm.StRsSetElementInfo;
import com.zoe.phip.web.model.sm.SystemDictItem;

import java.util.List;

/**
 * 对外发布的服务接口
 *
 * @author
 * @version 1.0
 * @date 2016-05-03
 */
public interface IStRsSetElementInfoService extends IBaseInService<StRsSetElementInfo> {



    ServiceResultT<PageList<StRsSetElementInfo>> getDataPageList(SystemData systemData, String fkSetId, String key, QueryPage queryPage);
}