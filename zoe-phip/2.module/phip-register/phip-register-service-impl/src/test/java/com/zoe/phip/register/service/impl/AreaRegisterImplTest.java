package com.zoe.phip.register.service.impl;

import com.zoe.phip.register.service.IAreaRegister;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by zengjiyang on 2016/4/11.
 */
public class AreaRegisterImplTest {

    @org.junit.Test
    public void testAddAreaRequest() throws Exception {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("application-context-consumer.xml");
        classPathXmlApplicationContext.start();

        IAreaRegister helloService = (IAreaRegister) classPathXmlApplicationContext.getBean("AreaRegister");
        String world = helloService.addAreaRequest("World");

        System.out.println("=====================================");
        System.out.println(world);
        System.out.println("=====================================");
    }
}