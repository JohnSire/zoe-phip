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

    ServiceResult saveList(SystemData systemData, String competenceCategoryId, List<MenuCompetence> list);

    ServiceResultT<PageList<MenuData>> getMenuListByCompetenceCategory(SystemData systemData, String categoryId, String key, QueryPage page);

    ServiceResultT<List<String>> getMenuCompetenceIdList(SystemData systemData, String categoryId);

    ServiceResult cancelMenuCompetence(SystemData systemData, String id);

    ServiceResult checkExists(SystemData systemData, String categoryId, String menuId);

    ServiceResult checkExists(SystemData systemData, String categoryId, int menuCode);

    ServiceResultT<List<String>> getCategoriesByMenuId(SystemData systemData, String menuId);
}