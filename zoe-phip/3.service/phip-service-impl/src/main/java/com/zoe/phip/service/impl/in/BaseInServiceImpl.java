package com.zoe.phip.service.impl.in;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoe.phip.dao.MyMapper;
import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.model.base.BaseEntity;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.service.in.BaseInService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * Created by zengjiyang on 2016/3/12.
 */
public abstract class BaseInServiceImpl<T extends BaseEntity> implements BaseInService<T> {

    @Autowired
    @SuppressWarnings("all")
    private MyMapper<T> mapper;

    public MyMapper<T> getMapper() {
        return mapper;
    }

    @Override
    public ServiceResult add(T entity) {

        return SafeExecuteUtil.execute(() -> mapper.insertSelective(entity));
    }

    @Override
    public ServiceResult addList(List<T> entities) {
        return SafeExecuteUtil.execute(
                () -> {
                    entities.forEach(e -> {
                        //设置主键
                        e.setId(StringUtil.getUUID());
                        e.setModifyAt(new Date());
                    });
                    return mapper.addList(entities);
                });
    }

    @Override
    public ServiceResult deleteById(String id) {
        return SafeExecuteUtil.execute(
                () -> mapper.deleteByPrimaryKey(id));
    }

    @Override
    public ServiceResult deleteByList(List<T> entities) {
        return SafeExecuteUtil.execute(
                () -> mapper.deleteByList(entities));
    }

    @Override
    public ServiceResult deleteByIds(List<String> ids) {
        return SafeExecuteUtil.execute(
                () -> mapper.deleteByIds(ids));
    }

    @Override
    public ServiceResult update(T entity) {
        return SafeExecuteUtil.execute(
                () -> mapper.updateByPrimaryKeySelective(entity));
    }

    @Override
    public ServiceResult updateList(List<T> entities) {
        return SafeExecuteUtil.execute(
                () -> mapper.updateList(entities));
    }

    @Override
    public ServiceResultT<T> getById(String id) {
        SafeExecuteUtil<T> safeExecute = new SafeExecuteUtil<T>();
        return safeExecute.executeT(() -> mapper.selectByPrimaryKey(id));
    }

    @Override
    public ServiceResultT<List<T>> getList() {
        SafeExecuteUtil<List<T>> safeExecute = new SafeExecuteUtil<List<T>>();
        return safeExecute.executeT(() -> mapper.selectAll());
    }

    @Override
    public ServiceResultT<PageList<T>> getList(QueryPage queryPage, Class<T> cls) {
        SafeExecuteUtil<PageList<T>> safeExecute = new SafeExecuteUtil<PageList<T>>();
        return safeExecute.executeT(() ->
        {
            PageList<T> pageList = new PageList<T>();
            Example example = new Example(cls);
            if (queryPage.getOrderBy() != null) {
                PageHelper.startPage(queryPage.getPageNum(), queryPage.getPageSize(), queryPage.getOrderBy());
            } else {
                PageHelper.startPage(queryPage.getPageNum(), queryPage.getPageSize());
            }
            List<T> results = mapper.selectByExample(example);
            PageInfo<T> pageInfo = new PageInfo<T>(results);
            pageList.setTotal((int) pageInfo.getTotal());
            pageList.setRows(results);
            return pageList;
        });
    }
}
