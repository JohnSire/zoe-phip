/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.web.model.sdm;

import javax.persistence.*;


import com.zoe.phip.module.service.entity.MasterEntity;


/**
 * @author
 * @version 1.0
 * @date 2016-05-04
 */
@Table(name = "PHIP_STANDARD_VER_RS_FIELD")
public class StandardVerRsField extends MasterEntity {
    /**
     * 标准版本
     */
    @Column(name = "FK_VERSION_ID")
    private String fkVersionId;
    /**
     * 字典主键
     */
    @Column(name = "FK_DICT_ID")
    private String fkDictId;

    public String getFkVersionId() {
        return this.fkVersionId;
    }


    public void setFkVersionId(String fkVersionId) {
        this.fkVersionId = fkVersionId;
    }

    public String getFkDictId() {
        return this.fkDictId;
    }


    public void setFkDictId(String fkDictId) {
        this.fkDictId = fkDictId;
    }
}
