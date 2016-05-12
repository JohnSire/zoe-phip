package com.zoe.phip.register.dao;

import com.zoe.phip.register.mapper.IServiceMapper;
import com.zoe.phip.register.model.XmanIndex;

import java.util.Map;

/**
 * Created by zengjiyang on 2016/5/6.
 */
public interface IXmanIndexMapper extends IServiceMapper<XmanIndex> {
    String getXmanId(Map<String, Object> args);

    XmanIndex documentRegistryQuery(Map<String, Object> paras);

    /**
     * 根据条件获取一条记录
     * @param paras
     * @return
     */
    XmanIndex getOneByCondition(Map<String, Object> paras);
}
