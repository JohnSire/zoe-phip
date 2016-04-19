package com.zoe.phip.register.mapper;

import com.zoe.phip.module.service.entity.BaseEntity;
import com.zoe.phip.module.service.mapper.IMyMapper;

/**
 * Created by zengjiyang on 2016/4/19.
 */
public interface IServiceMapper<T extends BaseEntity> extends IMyMapper<T> {

    /**
     * 新增，会默认设置ID及当前时间
     * @param t
     * @return
     */
    int defaultAdd(T t);

    int defaultUpdate(T t);
}
