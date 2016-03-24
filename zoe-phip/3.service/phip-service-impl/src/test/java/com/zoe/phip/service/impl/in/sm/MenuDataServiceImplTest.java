package com.zoe.phip.service.impl.in.sm;

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
    public void addTest() {
        MenuData menuData = new MenuData();
        menuData.setId("123456");
        menuData.setName("测试");
        menuData.setCode(1111);
        menuData.setFkParentMenuId("0000");
        menuData.setState(1);
        menuData.setCreateAt(new Date());
        menuData.setCreateBy("abc");
        ServiceResult serviceResult = menuDataService.add(menuData);
    }

    @Test
    public void testGetMenuByCode() {
        ServiceResultT<List<MenuData>> menuByCode = menuDataService.getMenuByCode("61");
    }

    @Test
    public void testGetMenuChild() {
        ServiceResultT<List<MenuData>> childMenus = menuDataService.getChildMenus("39ec692d-bc21-4f29-bc61-47fffe63ae4e");
        childMenus.getResult().forEach(System.out::println);

    }

    @Test
    public void testGetCompetenceMenuByUser() throws Exception {
        ServiceResultT<List<MenuData>> resultT= menuDataService.getCompetenceMenuByUser(null,"1761543318e744f781427ab325e6a45b");
    }
}
