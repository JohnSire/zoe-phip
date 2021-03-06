package com.zoe.phip.module.service.entity;


import com.alibaba.fastjson.annotation.JSONField;
import com.zoe.phip.infrastructure.myvalidator.annotation.ValidateNotBlank;
import com.zoe.phip.infrastructure.myvalidator.annotation.ValidateNotNull;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by linqinghuang on 2016/1/26.
 */
public class BaseEntity implements Serializable, First {

    /**
     * 主键id
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUID")
//    @JSONField(name = "Id")
    private String id;
    /**
     * 创建人
     */
//    @JSONField(name = "CreateBy")
    private String createBy;
    /**
     * 实体（数据记录）创建时间
     */
//    @JSONField(name = "CreateAt")
    private Date createAt;

    /**
     * 验证消息
     */
    @JSONField(serialize = false)
    @Transient
    private String  validateMessage;



    /**
     * 获取实体主键id
     *
     * @return id
     */
    public String getId() {

        return id;
    }



    /**
     * 设置实体主键id
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取实体（数据记录）添加时间
     *
     * @return addtime
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * 设置实体（数据记录）添加时间
     *
     * @param createAt
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }


    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getValidateMessage() {
        return validateMessage;
    }

    public void setValidateMessage(String validateMessage) {
        this.validateMessage = validateMessage;
    }
}
