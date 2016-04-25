package com.zoe.phip.client.host;

import com.zoe.phip.infrastructure.bean.IBootstrapper;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Created by  on 2016/1/22.
 */
public class Main {

    @Autowired(required = false)
    private static List<IBootstrapper> bootstrappers;


    public static void main(String[] args) throws Exception {

        Bootstrapper bootstrapper=new Bootstrapper();
        bootstrapper.start();

        System.out.println("按任意键退出");
        System.in.read();
    }
}
