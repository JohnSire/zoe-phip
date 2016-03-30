package com.zoe.phip.service.impl.bootstrapper;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zengjiyang on 2016/3/30.
 */
public final class Bootstrapper {

    public void start(){
        startSpring();
//        initData();
    }

    private void startSpring(){
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(new String[]{"application-context-provider.xml", "spring-mybatis.xml"});
        context.start();
    }

    private void initData(){
        MenuInit.toDatabase();
    }

}
