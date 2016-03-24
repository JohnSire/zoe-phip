package com.zoe.phip.service.impl.in.sm;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.model.sm.MenuData;
import com.zoe.phip.service.impl.BaseTest;
import com.zoe.phip.service.in.sm.MenuDataService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/3/21.
 */
public class MenuDataServiceImplTest extends BaseTest {


    @Autowired
    private MenuDataService menuDataService;


    @Test
    public void testAdd() throws Exception {
        MenuData menuData = new MenuData();
        menuData.setCode(51);
        menuData.setName("注册管理");
        menuData.setId("12345677");
        ServiceResult result = menuDataService.add(null,menuData);
    }

    @Test
    public void testGetMenuByCode() {
        ServiceResultT<List<MenuData>> menuByCode = menuDataService.getMenuByCode(null,"61");
    }

    @Test
    public void testGetMenuChild() {
        ServiceResultT<List<MenuData>> childMenus = menuDataService.getChildMenus(null,"39ec692d-bc21-4f29-bc61-47fffe63ae4e");
        childMenus.getResult().forEach(System.out::println);

    }

    @Test
    public void testGetCompetenceMenuByUser() throws Exception {
        ServiceResultT<List<MenuData>> resultT = menuDataService.getCompetenceMenuByUser(null, "1761543318e744f781427ab325e6a45b");
    }

    @Test
    public void testGetMenuPages() throws Exception {
        QueryPage page = new QueryPage();
        page.setPageNum(1);
        page.setPageSize(10);
        ServiceResultT<PageList<MenuData>> resultT = menuDataService.getMenuPages(null,"%管理%", page);
    }
}
