package com.zoe.phip.client.host;

import com.zoe.phip.web.bootstrapper.Bootstrapper;

/**
 * Created by  on 2016/1/22.
 */
public class Main {
    public static void main(String[] args) throws Exception {

        Bootstrapper bootstrapper=new Bootstrapper();
        bootstrapper.start();

        System.out.println("按任意键退出");
        System.in.read();
    }
}
