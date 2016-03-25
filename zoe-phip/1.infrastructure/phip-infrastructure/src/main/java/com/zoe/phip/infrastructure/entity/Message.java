package com.zoe.phip.infrastructure.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by linqinghuang on 2016/1/26.
 */
public class Message implements Serializable {
    /**
     * 消息id
     */
//    @JSONField(name = "Id")
    private String id;
    /**
     * 消息内容 content
     */
//    @JSONField(name = "Content")
    private String content;

    /**
     * 获取消息id
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置消息id
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取消息内容 content
     *
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置消息内容 content
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

}
