package com.zoe.phip.module.service.impl.in;


import com.alibaba.dubbo.rpc.service.GenericException;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.github.pagehelper.PageInfo;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.SystemData;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.module.service.entity.MasterEntity;
import com.zoe.phip.module.service.mapper.IMyMapper;
import com.zoe.phip.module.service.util.SqlHelper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by qiuyungen on 2016/3/26.
 */
public abstract class BaseInServiceImpl<T extends MasterEntity, TMapper extends IMyMapper<T>> implements IBaseInService<T>, Mapper<T>, GenericService {

    private SystemData systemData;

    @Autowired
    @SuppressWarnings("all")
    private IMyMapper<T> mapper;

    public TMapper getMapper() {
        return (TMapper) mapper;
    }

    @Override
    public SystemData getSystemData() {
        return this.systemData;
    }

    @Override
    public void setSystemData(SystemData systemData) {
        this.systemData = systemData;
    }

    @Override
    public int add(T entity) throws Exception {
        return mapper.insertSelective(entity);
    }

    @Override
    public int addList(List<T> entities) throws Exception {

        entities.forEach(e -> {
            //设置主键
            e.setId(StringUtil.getUUID());
            e.setModifyAt(new Date());
        });
        return mapper.addList(entities);
    }

    @Override
    public int deleteById(String id) throws Exception {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByIds(String ids) throws Exception {
        if(StringUtil.isNullOrWhiteSpace(ids))
            throw new BusinessException("批量删除参数不能为空");
        return mapper.deleteByIds(ids.split(","));
    }

    public int deleteByIds(String[] ids) throws Exception{
        return mapper.deleteByIds(ids);
    }

    @Override
    public int update(T entity) throws Exception {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public boolean updateList(List<T> entities) throws Exception {
        mapper.updateList(entities);
        return true;
    }

    @Override
    public T getById(String id) throws Exception {

        if (StringUtil.isNullOrWhiteSpace(id))
            throw new BusinessException("Id不能为空!");
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<T> getList(SystemData systemData) throws Exception {
        return mapper.selectAll();
    }

    @Override
    public PageList<T> getList(QueryPage queryPage, Class<T> cls) throws Exception {
        PageList<T> pageList = new PageList<T>();
        Example example = new Example(cls);
        SqlHelper.startPage(queryPage);
        List<T> results = mapper.selectByExample(example);
        PageInfo<T> pageInfo = new PageInfo<T>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        return pageList;
    }

    //region //空函数，转接

    @Override
    public int deleteByExample(Object o) {
        return getMapper().deleteByExample(o);
    }

    @Override
    public int deleteByPrimaryKey(Object o) {
        return getMapper().deleteByPrimaryKey(o);
    }

    @Override
    public int delete(T t) {
        return getMapper().delete(t);
    }

    @Override
    public int insert(T t) {
        return getMapper().insert(t);
    }

    @Override
    public int insertSelective(T t) {
        return getMapper().insertSelective(t);
    }

    @Override
    public List<T> selectAll() {
        return getMapper().selectAll();
    }

    @Override
    public List<T> selectByExample(Object o) {
        return getMapper().selectByExample(o);
    }

    @Override
    public List<T> selectByExampleAndRowBounds(Object o, RowBounds rowBounds) {
        return getMapper().selectByExampleAndRowBounds(o, rowBounds);
    }

    @Override
    public T selectByPrimaryKey(Object o) {
        return getMapper().selectByPrimaryKey(o);
    }

    @Override
    public int selectCountByExample(Object o) {
        return getMapper().selectCountByExample(o);
    }

    @Override
    public int selectCount(T t) {
        return getMapper().selectCount(t);
    }

    @Override
    public List<T> select(T t) {
        return getMapper().select(t);
    }

    @Override
    public T selectOne(T t) {
        return getMapper().selectOne(t);
    }

    @Override
    public List<T> selectByRowBounds(T t, RowBounds rowBounds) {
        return getMapper().selectByRowBounds(t, rowBounds);
    }

    @Override
    public int updateByExample(@Param("record") T t, @Param("example") Object o) {
        return getMapper().updateByExample(t, o);
    }

    @Override
    public int updateByExampleSelective(@Param("record") T t, @Param("example") Object o) {
        return getMapper().updateByExampleSelective(t, o);
    }

    @Override
    public int updateByPrimaryKey(T t) {
        return getMapper().updateByPrimaryKey(t);
    }

    @Override
    public int updateByPrimaryKeySelective(T t) {
        return getMapper().updateByPrimaryKeySelective(t);
    }

    @Override
    public Object $invoke(String s, String[] strings, Object[] objects) throws GenericException {
        return null;
    }



    //endregion
}
