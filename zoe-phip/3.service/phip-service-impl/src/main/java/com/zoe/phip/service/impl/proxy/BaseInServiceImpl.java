package com.zoe.phip.service.impl.proxy;

import com.github.pagehelper.PageInfo;
import com.zoe.phip.dao.MyMapper;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.SystemData;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.model.base.BaseEntity;
import com.zoe.phip.service.impl.util.SqlHelper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * Created by qiuyungen on 2016/3/26.
 */
public abstract class BaseInServiceImpl<T extends BaseEntity> implements BaseInService<T>, Mapper<T> {

    private SystemData systemData;

    @Autowired
    @SuppressWarnings("all")
    private MyMapper<T> mapper;

    public MyMapper<T> getMapper() {
        return mapper;
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
    public int deleteByList(List<T> entities) throws Exception {
        return mapper.deleteByList(entities);
    }

    @Override
    public int deleteByIds(List<String> ids) throws Exception {
        if (ids == null || ids.size() <= 0)
            throw new BusinessException("批量删除参数不能为空");
        return mapper.deleteByIds(ids);
    }

    @Override
    public int update(T entity) throws Exception {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int updateList(List<T> entities) throws Exception {
        return mapper.updateList(entities);
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

    //endregion
}
