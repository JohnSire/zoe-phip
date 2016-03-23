package com.zoe.phip.client.host;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zengjiyang on 2016/1/22.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(new String[]{"application-context-provider.xml", "spring-mybatis.xml"});
        context.start();
        System.out.println("按任意键退出");
        System.in.read();
    }
}
