/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.register.dao;


import com.zoe.phip.register.mapper.IServiceMapper;
import com.zoe.phip.register.model.AreaBaseInfo;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-04-11
 */
public interface IAreaBaseInfoMapper extends IServiceMapper<AreaBaseInfo> {

    /**
     *行政区域基本信息查询
     * @param map
     * @return
     */
    AreaBaseInfo getAreaBaseInfo(Map<String, Object> map);

    /**
     * 所辖行政区域信息查询
     * @param map
     * @return
     */
    List<AreaBaseInfo> getChildren(Map<String,Object> map);

    /**
     * 根据关键字查询区域信息
     * @param map
     * @return
     */
    List<AreaBaseInfo> getDataList(Map<String,Object> map);

}