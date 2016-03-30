package com.zoe.phip.service.impl.in.sm;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.model.sm.MenuData;
import com.zoe.phip.service.impl.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2016/3/21.
 */
public class MenuDataServiceImplTest extends BaseTest {


    @Autowired
    private MenuDataServiceImpl menuDataService;


    @Test
    public void testAdd() throws Exception {
        MenuData menuData = new MenuData();
        menuData.setCode("51");
        menuData.setName("注册管理");
        menuData.setId("12345677");
        int result = menuDataService.add(menuData);
    }

    @Test
    public void testGetMenuByCode() throws Exception {
        List<MenuData> menuByCode = menuDataService.getMenuByCode("61");
    }

    @Test
    public void testGetMenuChild() throws Exception {
        List<MenuData> childMenus = menuDataService.getChildMenus("39ec692d-bc21-4f29-bc61-47fffe63ae4e");
        childMenus.forEach(System.out::println);

    }

    @Test
    public void testGetCompetenceMenuByUser() throws Exception {
        List<MenuData> resultT = menuDataService.getCompetenceMenuByUser("1761543318e744f781427ab325e6a45b");
    }

    @Test
    public void testGetMenuPages() throws Exception {
        QueryPage page = new QueryPage();
        page.setPageNum(1);
        page.setPageSize(10);
        PageList<MenuData> resultT = menuDataService.getMenuPages("%list%", page);
        System.out.println();
        System.out.println(resultT.getTotal());
    }

    @Test
    public void testGetParentMenuById() throws Exception {
        MenuData menuData = menuDataService.getParentMenuById(null, "d8dc95b8a3be4cc886e58848c8d19975");
        System.out.println(menuData.toString());
    }
}
