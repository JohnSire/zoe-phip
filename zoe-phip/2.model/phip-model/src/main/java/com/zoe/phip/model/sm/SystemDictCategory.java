/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.model.sm;

import com.zoe.phip.model.base.BaseEntity;

import javax.persistence.*;

/**
 * @author
 * @version 1.0
 * @date 2016-03-22
 */
@Table(name = "SYS_SYSTEM_DICT_CATEGORY")
public class SystemDictCategory extends BaseEntity {
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
    /**
     * 描述
     */
    @Column(name = "DESCR")
    private String descr;

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

    public String getDescr() {
        return this.descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}
