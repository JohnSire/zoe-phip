package com.zoe.phip.module.service.mapper;

import com.zoe.phip.module.service.entity.BaseEntity;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by zengjiyang on 2016/1/25.
 */
public interface IMyMapper<T extends BaseEntity> extends Mapper<T> {

    int addList(List<T> entities) throws Exception;

  /*  int deleteByIds(List<String> ids) throws Exception;

    int deleteByList(List<T> entities) throws Exception;*/

    boolean updateList(List<T> entities) throws Exception;

    int deleteByIds(String[] ids) throws Exception;

    int defaultUpdate(T t);

}
