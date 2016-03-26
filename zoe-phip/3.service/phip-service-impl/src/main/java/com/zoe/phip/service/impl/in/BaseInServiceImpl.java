package com.zoe.phip.service.impl.in;

import com.github.pagehelper.PageInfo;
import com.zoe.phip.dao.MyMapper;
import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.model.base.BaseEntity;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.service.impl.util.SqlHelper;
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
    public ServiceResult add(SystemData systemData, T entity) {

        return SafeExecuteUtil.execute(() -> mapper.insertSelective(entity));
    }

    @Override
    public ServiceResult addList(SystemData systemData, List<T> entities) {
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
    public ServiceResult deleteById(SystemData systemData, String id) {
        return SafeExecuteUtil.execute(
                () -> mapper.deleteByPrimaryKey(id));
    }

    @Override
    public ServiceResult deleteByList(SystemData systemData, List<T> entities) {
        return SafeExecuteUtil.execute(
                () -> mapper.deleteByList(entities));
    }

    @Override
    public ServiceResult deleteByIds(SystemData systemData, List<String> ids) {
        return SafeExecuteUtil.execute(
                () -> {
                    if (ids == null || ids.size() <= 0)
                        throw new BusinessException("批量删除参数不能为空");
                    return mapper.deleteByIds(ids);
                });
    }

    @Override
    public ServiceResult update(SystemData systemData, T entity) {
        return SafeExecuteUtil.execute(
                () -> mapper.updateByPrimaryKeySelective(entity));
    }

    @Override
    public ServiceResult updateList(SystemData systemData, List<T> entities) {
        return SafeExecuteUtil.execute(
                () -> mapper.updateList(entities));
    }

    @Override
    public ServiceResultT<T> getById(SystemData systemData, String id) {
        SafeExecuteUtil<T> safeExecute = new SafeExecuteUtil<T>();
        return safeExecute.executeT(() -> {
                    if (StringUtil.isNullOrWhiteSpace(id))
                        throw new BusinessException("Id不能为空!");
                    return mapper.selectByPrimaryKey(id);
                }
        );
    }

    @Override
    public ServiceResultT<List<T>> getList(SystemData systemData) {
        SafeExecuteUtil<List<T>> safeExecute = new SafeExecuteUtil<List<T>>();
        return safeExecute.executeT(() -> mapper.selectAll());
    }

    @Override
    public ServiceResultT<PageList<T>> getList(SystemData systemData, QueryPage queryPage, Class<T> cls) {
        SafeExecuteUtil<PageList<T>> safeExecute = new SafeExecuteUtil<PageList<T>>();
        return safeExecute.executeT(() ->
        {
            PageList<T> pageList = new PageList<T>();
            Example example = new Example(cls);
            SqlHelper.startPage(queryPage);
            List<T> results = mapper.selectByExample(example);
            PageInfo<T> pageInfo = new PageInfo<T>(results);
            pageList.setTotal((int) pageInfo.getTotal());
            pageList.setRows(results);
            return pageList;
        });
    }
}
