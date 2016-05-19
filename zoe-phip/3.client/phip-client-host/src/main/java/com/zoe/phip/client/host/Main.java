package com.zoe.phip.client.host;

import com.zoe.phip.infrastructure.bean.IBootstrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by  on 2016/1/22.
 */
public class Main {

    public static void main(String[] args) throws Exception {


        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext
                (new String[]{"phip-context-provider.xml", "spring-mybatis.xml"});
        context.start();

       /*
        Bootstrapper bootstrapper=new Bootstrapper();
        bootstrapper.start();
*/
        System.out.println("按任意键退出");
        System.in.read();
    }
}
