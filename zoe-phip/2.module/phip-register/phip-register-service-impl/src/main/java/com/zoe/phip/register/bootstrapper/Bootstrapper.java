package com.zoe.phip.register.bootstrapper;

import com.zoe.phip.infrastructure.bean.IBootstrapper;
import com.zoe.phip.infrastructure.parser.Parser;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

/**
 * Created by zengjiyang on 2016/4/11.
 */
@Repository("register.Bootstrapper")
public class Bootstrapper implements IBootstrapper {

    public static void main(String[] args) throws Exception {

        //todo 待注释
        Bootstrapper bootstrapper = new Bootstrapper();
        bootstrapper.startService();

        /*System.out.println("按任意键退出");

        Parser parser = (Parser) context.getBean("BeetlParserImpl");
        parser.initialize();
        System.in.read();*/


    }

    @Override
    public void startService() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"register-context-provider.xml", "spring-mybatis.xml"});
        context.start();
    }

    @Override
    public int getExecutionOrder() {
        return 2;
    }
}
