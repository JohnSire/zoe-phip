/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.model.sm;

import javax.persistence.*;

import com.zoe.phip.model.base.BaseEntity;

/**
 * @author
 * @version 1.0
 * @date 2016-03-22
 */
@Table(name = "SYS_SYSTEM_DICT_ITEM")
public class SystemDictItem extends BaseEntity {
    /**
     * 字典类别
     */
    @Column(name = "FK_SYSTEM_DICT_CATEGORY_ID")
    private String fkSystemDictCategoryId;
    /**
     * 名称
     */
    @Column(name = "NAME")
    private String name;
    /**
     * 编码
     */
    @Column(name = "CODE")
    private String code;

    public String getFkSystemDictCategoryId() {
        return this.fkSystemDictCategoryId;
    }

    public void setFkSystemDictCategoryId(String fkSystemDictCategoryId) {
        this.fkSystemDictCategoryId = fkSystemDictCategoryId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
