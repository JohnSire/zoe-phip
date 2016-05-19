package com.zoe.phip.module.service.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Created by zhanghao on 2016/5/17.
 */
public class SimpleEntity implements Serializable {


    /**
     * 主键id
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUID")
//    @JSONField(name = "Id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    /**
     * 验证消息
     */
    @JSONField(serialize = false)
    @Transient
    private String validateMessage;

    public String getValidateMessage() {
        return validateMessage;
    }

    public void setValidateMessage(String validateMessage) {
        this.validateMessage = validateMessage;
    }
}
