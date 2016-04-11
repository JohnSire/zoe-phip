package com.zoe.phip.module.service.service.in;

import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.module.service.entity.MasterEntity;

import java.util.List;


/**
 * 内部服务基接口
 * Created by zengjiyang on 2016/1/25.
 */
public interface IBaseInService<T extends MasterEntity> {

    /**
     * 新增
     *
     * @param entity
     * @return
     */
    ServiceResult add(SystemData systemData, T entity );

    /**
     * 批量新增
     *
     * @param entities
     * @return
     */
    ServiceResult addList(SystemData systemData, List<T> entities);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    ServiceResult deleteById(SystemData systemData, String id);


    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    ServiceResult deleteByIds(SystemData systemData, String ids);

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    ServiceResult update(SystemData systemData, T entity);

    /**
     * 批量更新
     *
     * @param entities
     * @return
     */
    ServiceResult updateList(SystemData systemData, List<T> entities);

    /**
     * 获取单个对象
     *
     * @param id
     * @return
     */
    ServiceResultT<T> getById(SystemData systemData, String id);


    /**
     * 返回所有对象 不分页
     *
     * @return
     */
    ServiceResultT<List<T>> getList(SystemData systemData);


    /**
     * 分页查询
     *
     * @param queryPage
     * @return
     */
    ServiceResultT<PageList<T>> getList(SystemData systemData, QueryPage queryPage, Class<T> cls);

}
