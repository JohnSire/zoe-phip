package com.zoe.phip.dao;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by zengjiyang on 2016/1/25.
 */
public interface MyMapper<T> extends Mapper<T> {

    //todo 增加批量插入的方法
    int addList(List<T> entities);

    int deleteByIds(List<String> ids);

    int updateList(List<T> entities);
}
