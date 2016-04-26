package com.zoe.phip.web.controller;

import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.register.model.NationalStandards;
import com.zoe.phip.web.context.ComSession;
import com.zoe.phip.web.context.ServiceFactory;
import com.zoe.phip.web.controller.SystemManager.SystemUserController;
import com.zoe.phip.web.model.sm.SystemUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by zengjiyang on 2016/3/25.
 */
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SystemUserControllerTest extends BaseControllerTest {

    @Autowired
    private SystemUserController systemUserController;

    @Test
    public void testGetUserInfo() throws Exception {
        try {
            ServiceResultT<SystemUser> result = systemUserController.getUserInfo(request, null);
            System.out.println(result.getResult().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void userAddTest(){
       /* SystemUser user=new SystemUser();
        user.setState(1);
       user.setName("2");
        user.setPassword("1");
        user.setLoginName("1test4");
       ServiceResult result=systemUserController.addUserInfo(user);*/
        NationalStandards nationalStandards=new NationalStandards();
        nationalStandards.setName("123");
        nationalStandards.setCode("456");
        ServiceFactory.getNationalStandardsService().add(ComSession.getUserInfo(),nationalStandards);
     //  Assert.assertTrue(result.getIsSuccess());
    }
}