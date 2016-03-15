package com.zoe.phip.infrastructure.util;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by zengjiyang on 2016/3/15.
 */
public class User {

    @JSONField(name = "Na")
    private String name;
    private int age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

