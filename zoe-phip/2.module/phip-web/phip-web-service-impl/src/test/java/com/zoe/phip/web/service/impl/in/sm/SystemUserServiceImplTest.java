package com.zoe.phip.web.service.impl.in.sm;

import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by zengjiyang on 2016/4/8.
 */
public class SystemUserServiceImplTest {


    @Autowired
    private SystemUserServiceImpl systemUserService;


    @org.junit.Test
    public void testUpdatePassword() throws Exception {
        systemUserService.updateState("123",1);

    }
}