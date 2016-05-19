/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.register.model;

import com.zoe.phip.module.service.entity.MasterEntity;
import com.zoe.phip.module.service.entity.base.RegisterEntity;

import javax.persistence.*;


/**
 * @author
 * @version 1.0
 * @date 2016-04-11
 */
@Table(name = "PHIP_DICT_ITEM")
public class DictItem extends RegisterEntity {
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
     * 字典分类id
     */
    @Column(name = "Fk_CATALOG_ID")
    private String fkCatalogId;

    /**
     * 字典分类名称
     */
    @Transient
    private String fkCatalogName;

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

    public String getFkCatalogId() {
        return fkCatalogId;
    }

    public void setFkCatalogId(String fkCatalogId) {
        this.fkCatalogId = fkCatalogId;
    }

    public String getFkCatalogName() {
        return fkCatalogName;
    }

    public void setFkCatalogName(String fkCatalogName) {
        this.fkCatalogName = fkCatalogName;
    }
}
