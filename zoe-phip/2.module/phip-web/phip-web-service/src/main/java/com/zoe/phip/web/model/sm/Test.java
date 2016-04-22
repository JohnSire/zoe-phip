package com.zoe.phip.web.model.sm;

import lombok.Data;

/**
 * Created by huangyinfu on 2016/4/22.
 * get(),set()方法省去
 */
@Data
public class Test {
    private int state;

    public static void main(String[] args) {
        Test a = new Test();
        a.setState(1);
        a.getState();
        System.out.println(a);

    }


}
