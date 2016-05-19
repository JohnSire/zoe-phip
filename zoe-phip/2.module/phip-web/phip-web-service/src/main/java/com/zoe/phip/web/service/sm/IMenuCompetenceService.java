/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.web.service.sm;

import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.module.service.service.in.IBaseInService;
import com.zoe.phip.web.model.sm.MenuCompetence;
import com.zoe.phip.web.model.sm.MenuData;


import java.util.List;

/**
 * 对外发布的服务接口
 *
 * @author
 * @version 1.0
 * @date 2016-03-29
 */
public interface IMenuCompetenceService extends IBaseInService<MenuCompetence> {


    /**
     * 添加菜单权限
     *
     * @param systemData
     * @param competenceCategoryId
     * @param list
     * @return
     */
    ServiceResult saveList(SystemData systemData, String competenceCategoryId, List<MenuCompetence> list);

    /**
     * 通过权限类别获取菜单列表
     *
     * @param systemData
     * @param categoryId
     * @param key
     * @param page
     * @return
     */
    ServiceResultT<PageList<MenuData>> getMenuListByCompetenceCategory(SystemData systemData, String categoryId, String key, QueryPage page);

    /**
     * 获取菜单权限列表
     *
     * @param systemData
     * @param categoryId
     * @return
     */
    ServiceResultT<List<String>> getMenuCompetenceIdList(SystemData systemData, String categoryId);

    /**
     * 取消菜单权限
     *
     * @param systemData
     * @param id
     * @return
     */
    ServiceResult cancelMenuCompetence(SystemData systemData, String id);

    /**
     * 通过菜单ID检查是否在当前权限下存在
     *
     * @param systemData
     * @param categoryId
     * @param menuId
     * @return
     */
    ServiceResult checkExists(SystemData systemData, String categoryId, String menuId);

    /**
     * 通过菜单编码检查是否在当前权限下存在
     *
     * @param systemData
     * @param categoryId
     * @param menuCode
     * @return
     */
    ServiceResult checkExists(SystemData systemData, String categoryId, int menuCode);

    /**
     * 通过菜单ID获取权限类别
     *
     * @param systemData
     * @param menuId
     * @return
     */
    ServiceResultT<List<String>> getCategoriesByMenuId(SystemData systemData, String menuId);
}