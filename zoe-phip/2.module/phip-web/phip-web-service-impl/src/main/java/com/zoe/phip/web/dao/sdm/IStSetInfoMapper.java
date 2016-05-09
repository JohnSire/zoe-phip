/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.web.dao.sdm;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.module.service.mapper.IMyMapper;
import com.zoe.phip.web.model.sdm.StElementInfo;
import com.zoe.phip.web.model.sdm.StSetInfo;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-05-03
 */
public interface IStSetInfoMapper extends IMyMapper<StSetInfo> {
    /**
     * 根据关键字查询数据集
     *
     * @param map
     * @return
     */
    List<StSetInfo> getDataPageList(Map<String, Object> map);

    /**
     * 根据CDA查询数据集
     *
     * @param map
     * @return
     */

    List<StSetInfo> getByCdaId(Map<String, Object> map);

    /**
     * 判断代码是否存在
     *
     * @param map
     * @return
     */
    int getSingle(Map<String, Object> map);

    List<StSetInfo> getByPid(String pid);
}