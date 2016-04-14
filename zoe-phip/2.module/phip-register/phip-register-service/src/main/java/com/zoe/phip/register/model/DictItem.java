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
@Table(name = "PHIP_DICT_ITEM")
public class DictItem extends MasterEntity {
    /**
     * 字典项编码
     */
    @Column(name = "CODE")
    private String code;
    /**
     * 字典项名称
     */
    @Column(name = "NAME")
    private String name;
    /**
     * 字典分类编码
     */
    @Column(name = "FK_CATALOG_CODE")
    private String fkCatalogCode;

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

    public String getFkCatalogCode() {
        return this.fkCatalogCode;
    }

    public void setFkCatalogCode(String fkCatalogCode) {
        this.fkCatalogCode = fkCatalogCode;
    }
}
