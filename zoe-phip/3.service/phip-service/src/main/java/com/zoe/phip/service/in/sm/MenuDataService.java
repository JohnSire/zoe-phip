/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.service.in.sm;

import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.model.sm.MenuData;
import com.zoe.phip.model.sm.MenuTreeNode;
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


    ServiceResultT<PageList<MenuData>> getMenuPages(int state, String key, QueryPage page);

    ServiceResultT<List<MenuData>> getMenus(String key);

    ServiceResultT<List<MenuData>> getMenuByCode(String code);

    ServiceResultT<List<MenuData>> getChildMenus(String parentMenuId);

    ServiceResult updateState(String id, int state);

    ServiceResultT<List<MenuData>> getCompetenceMenuByUser(SystemData systemData, String userId);

    ServiceResultT<List<MenuData>> getCompetenceMenuByUser(String userId);
}