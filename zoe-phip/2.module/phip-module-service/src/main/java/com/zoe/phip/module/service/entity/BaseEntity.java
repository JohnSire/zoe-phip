package com.zoe.phip.module.service.entity;


import com.zoe.phip.infrastructure.myvalidator.annotation.ValidateNotBlank;
import com.zoe.phip.infrastructure.myvalidator.annotation.ValidateNotNull;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by linqinghuang on 2016/1/26.
 */
public class BaseEntity implements Serializable,First {

    /**
     * 主键id
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUID")
    @ValidateNotNull(message = "id不能为空",groups = {First.class})
//    @JSONField(name = "Id")
    private String id;
    /**
     * 创建人
     */
//    @JSONField(name = "CreateBy")
    @ValidateNotBlank(message = "修改人不能为空")
    private String createBy;
    /**
     * 实体（数据记录）创建时间
     */
//    @JSONField(name = "CreateAt")
    @ValidateNotNull(message = "创建时间不能为空")
    private Date createAt;


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



}
