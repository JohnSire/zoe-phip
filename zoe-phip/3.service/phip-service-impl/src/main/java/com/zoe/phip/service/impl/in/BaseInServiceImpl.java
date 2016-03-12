package com.zoe.phip.service.impl.in;

import com.zoe.phip.dao.MyMapper;
import com.zoe.phip.model.base.PageList;
import com.zoe.phip.model.base.QueryPage;
import com.zoe.phip.model.base.ServiceResult;
import com.zoe.phip.model.base.ServiceResultT;
import com.zoe.phip.service.impl.util.SafeExecuteUtil;
import com.zoe.phip.service.in.BaseInService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by zengjiyang on 2016/3/12.
 */
public abstract class BaseInServiceImpl<T> implements BaseInService<T> {

    private static final Logger logger = LoggerFactory.getLogger(BaseInServiceImpl.class);

    @Autowired
    protected MyMapper<T> mapper;

    public MyMapper<T> getMapper() {
        return mapper;
    }

    @Override
    public ServiceResult add(T entity) {
        ServiceResult serviceResult= SafeExecuteUtil.execute(logger,
                ()-> mapper.insert(entity));
        return serviceResult;
    }

    @Override
    public ServiceResult addList(List<T> entities) {
        ServiceResult serviceResult= SafeExecuteUtil.execute(logger,
                ()-> mapper.addList(entities));
        return serviceResult;
    }

    @Override
    public ServiceResult deleteById(String id) {
        ServiceResult serviceResult= SafeExecuteUtil.execute(logger,
                ()-> mapper.deleteByPrimaryKey(id));
        return serviceResult;
    }

    @Override
    public ServiceResult deleteByIds(List<String> ids) {
        ServiceResult serviceResult= SafeExecuteUtil.execute(logger,
                ()->"1");// mapper.deleteByIds(ids));
        return serviceResult;
    }

    @Override
    public ServiceResult update(T entity) {
        ServiceResult serviceResult= SafeExecuteUtil.execute(logger,
                ()-> mapper.updateByPrimaryKeySelective(entity));
        return serviceResult;
    }

    @Override
    public ServiceResult updateList(List<T> entities) {
        ServiceResult serviceResult= SafeExecuteUtil.execute(logger,
                ()->"1");// mapper.updateList(entities));
        return serviceResult;
    }

    @Override
    public ServiceResultT<T> getById(String id) {
        ServiceResultT<T> t=new ServiceResultT<T>();
        return null;
    }

    @Override
    public ServiceResultT<List<T>> getList() {
        return null;
    }

    @Override
    public ServiceResultT<PageList> getList(QueryPage queryPage) {
        return null;
    }
}
