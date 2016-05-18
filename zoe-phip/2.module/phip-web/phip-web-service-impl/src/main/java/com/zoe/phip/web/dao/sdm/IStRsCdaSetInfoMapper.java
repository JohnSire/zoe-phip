/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.web.dao.sdm;

import com.zoe.phip.module.service.mapper.IMyMapper;
import com.zoe.phip.web.model.sdm.StRsCdaSetInfo;

import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-05-03
 */
public interface IStRsCdaSetInfoMapper extends IMyMapper<StRsCdaSetInfo> {
    int getSingle(Map<String, Object> map);

    int deleteByFkSetIds(String[] fkSetIds);
}