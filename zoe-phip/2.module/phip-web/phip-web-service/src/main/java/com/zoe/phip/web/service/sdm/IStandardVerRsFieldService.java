/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.web.service.sdm;

import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.SystemData;
import com.zoe.phip.module.service.service.in.IBaseInService;
import com.zoe.phip.web.model.sdm.StandardVerRsDict;
import com.zoe.phip.web.model.sdm.StandardVerRsField;

import java.util.List;

/**
 * 对外发布的服务接口
 *
 * @author
 * @version 1.0
 * @date 2016-05-04
 */
public interface IStandardVerRsFieldService extends IBaseInService<StandardVerRsField> {

    ServiceResult versionStandardStruct(SystemData systemData, String fkVersionId, List<StandardVerRsField> fieldList);

}