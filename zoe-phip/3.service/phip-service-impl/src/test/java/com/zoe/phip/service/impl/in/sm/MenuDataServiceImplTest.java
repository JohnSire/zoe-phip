package com.zoe.phip.service.impl.in.sm;

import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.model.sm.MenuData;
import com.zoe.phip.service.impl.BaseTest;
import com.zoe.phip.service.in.sm.MenuDataService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

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

}
