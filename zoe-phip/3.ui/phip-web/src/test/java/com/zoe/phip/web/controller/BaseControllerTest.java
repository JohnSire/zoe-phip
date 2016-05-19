package com.zoe.phip.web.controller;

import com.zoe.phip.web.context.DataContext;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zengjiyang on 2016/3/25.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-mvc.xml", "classpath*:application-context-consumer.xml"})
public abstract class BaseControllerTest {

    protected MockHttpServletRequest request;
    protected MockHttpServletResponse response;

    @Autowired
    private FrameController frameController;

    @Before
    public void baseInit() {
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
        //模拟用户登录
        request.setParameter("userCode", "admin");
        request.setParameter("userPwd", "1");
        DataContext.init(request, response);
        frameController.loginAuth(request, null);
    }
}
