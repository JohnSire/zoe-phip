package com.zoe.phip.web.controller;

import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.model.sm.SystemUser;
import com.zoe.phip.web.context.DataContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by zengjiyang on 2016/3/25.
 */
public class SystemUserControllerTest extends BaseControllerTest {

    @Autowired
    private SystemUserController systemUserController;

    @Test
    public void testGetUserInfo() throws Exception {
        try {
            ServiceResultT<SystemUser> result= systemUserController.getUserInfo(request,null);
            System.out.println(result.getResult().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void userAddTest(){
        SystemUser user=new SystemUser();
        user.setState(1);
        user.setName("test");
        user.setPassword("1");
        user.setLoginName("test");
        ServiceResult result=systemUserController.addUserInfo(user);
        Assert.assertTrue(result.getIsSuccess());
    }
}