package com.zoe.phip.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by zengjiyang on 2016/3/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-mvc.xml","classpath*:application-context-consumer.xml"})
public class SystemUserControllerTest {

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Autowired
    private SystemUserController systemUserController;

    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    @Test
    public void testGetUserInfo() throws Exception {
        try {
            request.setParameter("userName", "admin");
            request.setParameter("password", "2");
            assertEquals("login",systemUserController.getUserInfo(request,null)) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}