/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.dao.sm;

import com.zoe.phip.dao.MyMapper;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.model.sm.MenuData;

import java.util.List;

/**
 * @author zhanghao
 * @version 1.0
 * @date 2016-03-21
 */
public interface MenuDataMapper extends MyMapper<MenuData> {

    PageList<MenuData> getMenuPages(String key, QueryPage page) throws Exception;

    List<MenuData> getMenus(String key) throws Exception;

    List<MenuData> getMenuByCode(String code) throws Exception;

    List<MenuData> getChildMenus(String parentMenuId) throws Exception;

    List<MenuData> getCompetenceMenuByUser(String userId) throws Exception;


}