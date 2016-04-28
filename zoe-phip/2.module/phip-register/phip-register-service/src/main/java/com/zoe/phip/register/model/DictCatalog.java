/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.register.model;

import com.zoe.phip.module.service.entity.MasterEntity;

import javax.persistence.*;


/**
 * @author
 * @version 1.0
 * @date 2016-04-11
 */
@Table(name = "PHIP_DICT_CATALOG")
public class DictCatalog extends MasterEntity {
    /**
     * 字典分类编码
     */
    @Column(name = "CODE")
    private String code;
    /**
     * 字典分类名称
     */
    @Column(name = "NAME")
    private String name;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 字典父分类编码
     */
    @Column(name = "PID")
    private String pid;

    /**
     * 字典父分类名称
     */
    @Transient
    private String parentName;

    /**
     * 字典父分类
     */
    @Transient
    private DictCatalog parentCatalog;

    /**
     * 字典分类类别
     */
    @Column(name = "TYPE")
    private int type;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public DictCatalog getParentCatalog() {
        return parentCatalog;
    }

    public void setParentCatalog(DictCatalog parentCatalog) {
        this.parentCatalog = parentCatalog;
    }
}
