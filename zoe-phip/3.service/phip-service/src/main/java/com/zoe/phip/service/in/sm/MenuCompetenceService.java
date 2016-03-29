/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.service.in.sm;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.model.sm.MenuCompetence;
import com.zoe.phip.model.sm.MenuData;
import com.zoe.phip.service.in.BaseInService;

import java.util.List;

/**
 * 对外发布的服务接口
 *
 * @author
 * @version 1.0
 * @date 2016-03-29
 */
public interface MenuCompetenceService extends BaseInService<MenuCompetence> {

    ServiceResult saveList(String competenceCategoryId, List<MenuCompetence> list);

    ServiceResultT<PageList<MenuData>> getMenuListByCompetenceCategory(String categoryId, String key, QueryPage page);

    ServiceResultT<List<String>> getMenuCompetenceIdList(String categoryId);

    ServiceResult cancelMenuCompetence(String id);

    ServiceResult checkExists(String categoryId, String menuId);

    ServiceResult checkExists(String categoryId, int menuCode);

    ServiceResultT<List<String>> getCategoriesByMenuId(String menuId);
}