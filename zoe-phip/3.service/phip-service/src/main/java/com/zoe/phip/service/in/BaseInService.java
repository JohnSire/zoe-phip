package com.zoe.phip.service.in;


import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.model.base.BaseEntity;

import java.util.List;


/**
 * 内部服务基接口
 * Created by zengjiyang on 2016/1/25.
 */
public interface BaseInService<T extends BaseEntity> {

    /**
     * 新增
     *
     * @param entity
     * @return
     */
    ServiceResult add(T entity);

    /**
     * 批量新增
     *
     * @param entities
     * @return
     */
    ServiceResult addList(List<T> entities);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    ServiceResult deleteById(String id);

    /**
     * 批量删除
     *
     * @param entities
     * @return
     */
    ServiceResult deleteByList(List<T> entities);


    /**
     * 批量删除
     *
     * @param idList
     * @return
     */
    ServiceResult deleteByIds(List<String> idList);

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    ServiceResult update(T entity);

    /**
     * 批量更新
     *
     * @param entities
     * @return
     */
    ServiceResult updateList(List<T> entities);

    /**
     * 获取单个对象
     *
     * @param id
     * @return
     */
    ServiceResultT<T> getById(String id);


    /**
     * 返回所有对象 不分页
     *
     * @return
     */
    ServiceResultT<List<T>> getList();


    /**
     * 分页查询
     *
     * @param queryPage
     * @return
     */
    ServiceResultT<PageList<T>> getList(QueryPage queryPage, Class<T> cls );

}
