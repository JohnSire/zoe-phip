package com.zoe.phip.model.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by linqinghuang on 2016/1/26.
 */
public class BaseEntity {
    /**
     * 主键id
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUID")
    private String id;
    /**
     * 实体（数据记录）创建时间
     */
    private Date addTime;
    /**
     * 实体（数据记录）修改时间
     */
    private Date updateTime;

    /**
     * 获取实体主键id
     * @return id
     */
    public String getId() {

        return id;
    }

    /**
     * 设置实体主键id
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取实体（数据记录）添加时间
     * @return addtime
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置实体（数据记录）添加时间
     * @param addTime
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取实体（数据记录）修改时间
     * @return
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置实体（数据记录）修改时间
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
