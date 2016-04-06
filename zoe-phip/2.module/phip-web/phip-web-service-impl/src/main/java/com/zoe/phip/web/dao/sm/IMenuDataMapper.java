/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.web.dao.sm;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.module.service.mapper.IMyMapper;
import com.zoe.phip.web.model.sm.MenuData;

import java.util.List;
import java.util.Map;

/**
 * @author zhanghao
 * @version 1.0
 * @date 2016-03-21
 */
public interface IMenuDataMapper extends IMyMapper<MenuData> {

    List<MenuData> getMenuDataList(Map<String, Object> args);


    /**
     * 通过关键字分页获取菜单列�
     * @param key
     * @param page
     * @return
     * @throws Exception
     */
    PageList<MenuData> getMenuPages(String key, QueryPage page) throws Exception;

    /**
     * 通过关键字获取菜单列�
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
     * 获取子菜�
     * @param parentMenuId
     * @return
     * @throws Exception
     */
    List<MenuData> getChildMenus(String parentMenuId) throws Exception;

    /**
     * 获取用户的菜单权�
     * @param userId
     * @return
     * @throws Exception
     */
    List<MenuData> getCompetenceMenuByUser(String userId) throws Exception;

    /**
     * 更新菜单状�
     * @param id
     * @param state
     * @return
     * @throws Exception
     */
    int updateState(String id, int state) throws Exception;


    /**
     * 关键字查询菜单数�
     * @param key
     * @param page
     * @return
     * @throws Exception
     */
    PageList<MenuData> getMenuList(String key, QueryPage page) throws Exception;

    /**
     * 插入菜单及其子菜�
     * @param menuData
     * @return
     */
    boolean insertMenuData(List<MenuData> menuData);


    MenuData getMenuById(String id);
}