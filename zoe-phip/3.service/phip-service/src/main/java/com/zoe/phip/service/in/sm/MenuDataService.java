/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.service.in.sm;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.entity.SystemData;
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


    ServiceResultT<PageList<MenuData>> getMenuPages(SystemData systemData, String key, QueryPage page);

    ServiceResultT<List<MenuData>> getMenus(SystemData systemData, String key);

    ServiceResultT<List<MenuData>> getMenuByCode(SystemData systemData, String code);

    ServiceResultT<List<MenuData>> getChildMenus(SystemData systemData, String parentMenuId);


    ServiceResultT<List<MenuData>> getCompetenceMenuByUser(SystemData systemData, String userId);

    ServiceResultT<List<MenuData>> getCompetenceMenuByUser(String userId);
}