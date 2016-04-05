/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.web.dao.sm;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.module.infrastructure.dao.IMyMapper;
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

    boolean saveList(String competenceCategoryId, List<MenuCompetence> list) throws Exception;

    PageList<MenuData> getMenuListByCompetenceCategory(String categoryId, String key, QueryPage page);

    List<String> getMenuCompetenceIdList(String categoryId);

    boolean cancelMenuCompetence(String id) throws Exception;

    boolean checkExists(String categoryId, String menuId);

    boolean checkExists(String categoryId, int menuCode);

    List<String> getCategoriesByMenuId(String menuId);

    int checkExists(Map map);

    List<MenuData> getMenuListByCompetenceCategory(Map map);
}