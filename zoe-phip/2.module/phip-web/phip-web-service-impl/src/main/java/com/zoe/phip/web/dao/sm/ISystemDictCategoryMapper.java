/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.web.dao.sm;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.module.service.mapper.IMyMapper;
import com.zoe.phip.web.model.sm.SystemDictCategory;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-03-22
 */
public interface ISystemDictCategoryMapper extends IMyMapper<SystemDictCategory> {

    /**
     * 获取字典类别列表
     *
     * @param map
     * @return
     */
    List<SystemDictCategory> getDictCategories(Map map);

    /**
     * 获取具体的字典类别
     *
     * @param map
     * @return
     */
    SystemDictCategory getDictCategory(Map map);

    /**
     * 分页获取字典类别
     *
     * @param key
     * @param queryPage
     * @return
     * @throws Exception
     */
    PageList<SystemDictCategory> getDictCategories(String key, QueryPage queryPage) throws Exception;

    /**
     * 通过编码获取具体的字典类别
     *
     * @param code
     * @return
     * @throws Exception
     */
    SystemDictCategory getDictCategory(String code) throws Exception;
}