package com.zoe.phip.register.bootstrapper;

import com.zoe.phip.infrastructure.parser.Parser;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zengjiyang on 2016/4/11.
 */
public class Bootstrapper {
    public static void main(String[] args) throws Exception {


        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"application-context-provider.xml", "spring-mybatis.xml"});
        context.start();

        System.out.println("按任意键退出");

        Parser parser = (Parser) context.getBean("BeetlParserImpl");
        parser.initialize();
        System.in.read();


    }
}
