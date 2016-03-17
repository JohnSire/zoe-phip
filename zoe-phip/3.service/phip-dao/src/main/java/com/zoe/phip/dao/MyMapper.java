package com.zoe.phip.dao;

import com.zoe.phip.model.base.BaseEntity;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by zengjiyang on 2016/1/25.
 */
public interface MyMapper<T extends BaseEntity> extends Mapper<T> {

    int addList(List<T> entities);

    int deleteByIds(List<String> ids);

    int deleteByList(List<T> entities);
    //todo 批量更新？
    int updateList(List<T> entities);
}
