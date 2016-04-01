/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.model.sm;

import com.alibaba.fastjson.annotation.JSONField;
import com.zoe.phip.model.base.BaseEntity;
import com.zoe.phip.model.base.MasterEntity;

import javax.persistence.*;

/**
 * @author
 * @version 1.0
 * @date 2016-03-22
 */
@Table(name = "SYS_SYSTEM_DICT_CATEGORY")
public class SystemDictCategory extends MasterEntity {
    /**
     * 名称
     */
    @Column(name = "NAME")
//    @JSONField(name = "Name")
    private String name;
    /**
     * 编码
     */
    @Column(name = "CODE")
//    @JSONField(name = "Code")
    private String code;
    /**
     * 描述
     */
    @Column(name = "DESCR")
//    @JSONField(name = "Descr")
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
