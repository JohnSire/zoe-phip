/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.web.dao.sdm;

import com.zoe.phip.module.service.mapper.IMyMapper;
import com.zoe.phip.web.model.sdm.StandardVerRsSet;

import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-05-04
 */
public interface IStandardVerRsSetMapper extends IMyMapper<StandardVerRsSet> {
    int getSingle(Map<String, Object> map);
}