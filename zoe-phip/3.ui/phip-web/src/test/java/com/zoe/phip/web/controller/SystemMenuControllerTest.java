package com.zoe.phip.web.controller;

import com.zoe.phip.web.controller.SystemManager.SystemMenuController;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zengjiyang on 2016/3/25.
 */
public class SystemMenuControllerTest extends BaseControllerTest {

    @Autowired
    private SystemMenuController systemMenuController;

/*    @Test
    public void testGetUserInfo() throws Exception {
        try {
            ServiceResultT<SystemUser> result= systemUserController.getUserInfo(request,null);
            System.out.println(result.getResult().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


/*    @Test
    public void userAddTest(){
        SystemUser user=new SystemUser();
        user.setState(1);
        user.setName("test");
        user.setPassword("1");
        user.setLoginName("test");
        ServiceResult result=systemUserController.addUserInfo(user);
        Assert.assertTrue(result.getIsSuccess());
    }*/

 /*   @Test*/
 /*   public void menuAddTest(){
        MenuData date=new MenuData();
        date.setState(1);
        date.setCode("22");


        ServiceResult result=systemMenuController.addMenuInfo(date);
        Assert.assertTrue(result.getIsSuccess());
    }*/
}