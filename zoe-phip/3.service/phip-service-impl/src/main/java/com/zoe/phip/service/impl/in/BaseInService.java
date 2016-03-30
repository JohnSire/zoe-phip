package com.zoe.phip.service.impl.in;


import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.SystemData;
import com.zoe.phip.model.base.BaseEntity;

import java.util.List;


/**
 * 内部服务基接口
 * Created by qiuyungen on 2016/3/26.
 */
public interface BaseInService<T extends BaseEntity> {

    SystemData getSystemData();

    void setSystemData(SystemData systemData);

    /**
     * 新增
     *
     * @param entity
     * @return
     */
    int add(T entity) throws Exception;

    /**
     * 批量新增
     *
     * @param entities
     * @return
     */
    int addList(List<T> entities) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int deleteById(String id) throws Exception;

    /**
     * 批量删除
     *
     * @param entities
     * @return
     */
    int deleteByList(List<T> entities) throws Exception;


    /**
     * 批量删除
     *
     * @param idList
     * @return
     */
    int deleteByIds(List<String> idList) throws Exception;

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    int update(T entity) throws Exception;

    /**
     * 批量更新
     *
     * @param entities
     * @return
     */
    boolean updateList(List<T> entities) throws Exception;

    /**
     * 获取单个对象
     *
     * @param id
     * @return
     */
    T getById(String id) throws Exception;


    /**
     * 返回所有对象 不分页
     *
     * @return
     */
    List<T> getList(SystemData systemData) throws Exception;


    /**
     * 分页查询
     *
     * @param queryPage
     * @return
     */
    PageList<T> getList(QueryPage queryPage, Class<T> cls) throws Exception;

}
