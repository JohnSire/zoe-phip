package com.zoe.phip.register.bootstrapper;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zengjiyang on 2016/4/11.
 */
public class Bootstrapper {
    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(new String[]{"application-context-provider.xml", "spring-mybatis.xml"});
        context.start();

        System.out.println("按任意键退出");
        System.in.read();
    }
}
