/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.web.dao.sm;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.module.infrastructure.dao.IMyMapper;
import com.zoe.phip.web.model.sm.SystemDictCategory;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-03-22
 */
public interface ISystemDictCategoryMapper extends IMyMapper<SystemDictCategory> {

    List<SystemDictCategory> getDictCategories(Map map);

    SystemDictCategory getDictCategory(Map map);

    PageList<SystemDictCategory> getDictCategories(String key, QueryPage queryPage) throws Exception;

    SystemDictCategory getDictCategory(String code) throws Exception;
}