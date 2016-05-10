/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.web.dao.sdm;

import com.zoe.phip.module.service.mapper.IMyMapper;
import com.zoe.phip.web.model.sdm.StandardVerRsCda;

import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-05-03
 */

public interface IStandardVerRsCdaMapper extends IMyMapper<StandardVerRsCda> {
    int getSingle(Map<String, Object> map);
}