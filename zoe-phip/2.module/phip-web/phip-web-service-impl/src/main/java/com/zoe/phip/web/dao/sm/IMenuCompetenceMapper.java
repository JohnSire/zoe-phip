/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.web.dao.sm;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.module.service.mapper.IMyMapper;
import com.zoe.phip.web.model.sm.MenuCompetence;
import com.zoe.phip.web.model.sm.MenuData;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-03-29
 */
public interface IMenuCompetenceMapper extends IMyMapper<MenuCompetence> {

    /**
     * 添加菜单权限
     *
     * @param competenceCategoryId
     * @param list
     * @return
     * @throws Exception
     */
    boolean saveList(String competenceCategoryId, List<MenuCompetence> list) throws Exception;

    /**
     * 通过权限类别获取菜单列表
     *
     * @param categoryId
     * @param key
     * @param page
     * @return
     */
    PageList<MenuData> getMenuListByCompetenceCategory(String categoryId, String key, QueryPage page);

    /**
     * 获取菜单权限列表
     *
     * @param categoryId
     * @return
     */
    List<String> getMenuCompetenceIdList(String categoryId);

    /**
     * 取消菜单权限
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean cancelMenuCompetence(String id) throws Exception;

    /**
     * 通过菜单ID检查是否在当前权限下存在
     *
     * @param categoryId
     * @param menuId
     * @return
     */
    boolean checkExists(String categoryId, String menuId);

    /**
     * 通过菜单编码检查是否在当前权限下存在
     *
     * @param categoryId
     * @param menuCode
     * @return
     */
    boolean checkExists(String categoryId, int menuCode);

    /**
     * 通过菜单ID获取权限类别
     *
     * @param menuId
     * @return
     */
    List<String> getCategoriesByMenuId(String menuId);

    /**
     * 检查是否存在该权限
     *
     * @param map
     * @return
     */
    int checkExists(Map map);

    /**
     * 通过权限类别获取菜单列表
     *
     * @param map
     * @return
     */
    List<MenuData> getMenuListByCompetenceCategory(Map map);
}