package com.zoe.phip.dao;

import com.zoe.phip.model.base.BaseEntity;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by zengjiyang on 2016/1/25.
 */
public interface MyMapper<T extends BaseEntity> extends Mapper<T> {

    int addList(List<T> entities) throws Exception;

    int deleteByIds(List<String> ids) throws Exception;

    int deleteByList(List<T> entities) throws Exception;

    //todo 批量更新？
    @Deprecated
    int updateList(List<T> entities) throws Exception;

}
