/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.web.dao.sdm;

import com.zoe.phip.module.service.mapper.IMyMapper;
import com.zoe.phip.web.model.sdm.StCdaInfo;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-05-03
 */
public interface IStCdaInfoMapper extends IMyMapper<StCdaInfo> {
    List<StCdaInfo> getDataPageList(Map<String, Object> map);

    int getSingle(Map<String,Object> map);
}