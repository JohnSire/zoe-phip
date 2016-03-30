/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.service.in.sm;

import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.model.sm.MenuData;
import com.zoe.phip.service.in.BaseInService;

import java.util.List;


/**
 * 对外发布的服务接口
 *
 * @author
 * @version 1.0
 * @date 2016-03-21
 */
public interface MenuDataService extends BaseInService<MenuData> {


    /**
     * 通过关键字分页获取菜单列表
     * @param systemData
     * @param key
     * @param page
     * @return
     */
    ServiceResultT<PageList<MenuData>> getMenuPages(SystemData systemData, String key, QueryPage page);

    /**
     * 通过关键字获取菜单列表
     * @param systemData
     * @param key
     * @return
     */
    ServiceResultT<List<MenuData>> getMenus(SystemData systemData, String key);

    /**
     * 通过编码获得菜单列表
     * @param systemData
     * @param code
     * @return
     */
    ServiceResultT<List<MenuData>> getMenuByCode(SystemData systemData, String code);

    /**
     * 获取子菜单列表
     * @param systemData
     * @param parentMenuId
     * @return
     */
    ServiceResultT<List<MenuData>> getChildMenus(SystemData systemData, String parentMenuId);

    /**
     * 获取用户的菜单权限
     * @param systemData
     * @param userId
     * @return
     */
    ServiceResultT<List<MenuData>> getCompetenceMenuByUser(SystemData systemData, String userId);



    /**
     * 更新用户状态
     * @param systemData
     * @param id
     * @param state
     * @return
     */
    ServiceResult updateState(SystemData systemData, String id, int state);


    /**
     * 通过关键字查询菜单数据
     * @param systemData
     * @param key
     * @param page
     * @return
     */
    ServiceResultT<PageList<MenuData>> getMenuList(SystemData systemData, String key, QueryPage page);


    /**
     * 获取父级菜单
     * @param systemData
     * @param id
     * @return
     */
    ServiceResultT<MenuData> getParentMenuById(SystemData systemData, String id);
}