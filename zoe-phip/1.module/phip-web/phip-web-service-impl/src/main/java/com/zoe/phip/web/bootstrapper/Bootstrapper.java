package com.zoe.phip.web.bootstrapper;

import com.zoe.phip.infrastructure.bean.IBootstrapper;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

/**
 * Created by zengjiyang on 2016/4/5.
 */
@Repository("web.Bootstrapper")
public class Bootstrapper implements IBootstrapper {

    @Override
    public void startService() {
        start();
//        initData();
    }

    private void start() {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(new String[]{"web-context-provider.xml", "spring-mybatis.xml"});
        context.start();
    }

    @Override
    public int getExecutionOrder() {
        return 1;
    }

    private void initData() {
        MenuInit.toDatabase();
    }
}
