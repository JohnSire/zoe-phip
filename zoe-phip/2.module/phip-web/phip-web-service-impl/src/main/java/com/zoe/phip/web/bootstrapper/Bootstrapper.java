package com.zoe.phip.web.bootstrapper;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zengjiyang on 2016/4/5.
 */
public class Bootstrapper {

    public static void main(String[] args) throws Exception{
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(new String[]{"application-context-provider.xml", "spring-mybatis.xml"});
        context.start();
    }
}
