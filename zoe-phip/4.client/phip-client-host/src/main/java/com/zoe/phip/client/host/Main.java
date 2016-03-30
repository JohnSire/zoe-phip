package com.zoe.phip.client.host;

import com.zoe.phip.service.impl.bootstrapper.Bootstrapper;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by  on 2016/1/22.
 */
public class Main {
    public static void main(String[] args) throws Exception {
       /* ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(new String[]{"application-context-provider.xml", "spring-mybatis.xml"});
        context.start();*/

        Bootstrapper bootstrapper=new Bootstrapper();
        bootstrapper.start();

        System.out.println("按任意键退出");
        System.in.read();
    }
}
