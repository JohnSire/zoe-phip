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
import java.util.Map;

/**
 * @author zhanghao
 * @version 1.0
 * @date 2016-03-21
 */
public interface MenuDataMapper extends MyMapper<MenuData> {

    List<MenuData> getMenuDataList(Map<String, Object> args);


    /**
     * 通过关键字分页获取菜单列表
     * @param key
     * @param page
     * @return
     * @throws Exception
     */
    PageList<MenuData> getMenuPages(String key, QueryPage page) throws Exception;

    /**
     * 通过关键字获取菜单列表
     * @param key
     * @return
     * @throws Exception
     */
    List<MenuData> getMenus(String key) throws Exception;

    /**
     * 通过code获取菜单列表
     * @param code
     * @return
     * @throws Exception
     */
    List<MenuData> getMenuByCode(String code) throws Exception;

    /**
     * 获取子菜单
     * @param parentMenuId
     * @return
     * @throws Exception
     */
    List<MenuData> getChildMenus(String parentMenuId) throws Exception;

    /**
     * 获取用户的菜单权限
     * @param userId
     * @return
     * @throws Exception
     */
    List<MenuData> getCompetenceMenuByUser(String userId) throws Exception;

    /**
     * 更新菜单状态
     * @param id
     * @param state
     * @return
     * @throws Exception
     */
    int updateState(String id,int state) throws Exception;


    /**
     * 关键字查询菜单数据
     * @param key
     * @param page
     * @return
     * @throws Exception
     */
    PageList<MenuData> getMenuList( String key, QueryPage page) throws Exception;

    /**
     * 插入菜单及其子菜单
     * @param menuData
     * @return
     */
    boolean insertMenuData(List<MenuData> menuData);
}