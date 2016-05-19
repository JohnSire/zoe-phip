package com.zoe.phip.infrastructure.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by linqinghuang on 2016/1/26.
 */

public class LogMessage implements Serializable {

    //    @JSONField(name = "Content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
