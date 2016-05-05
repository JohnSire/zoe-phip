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
@Table(name = "PHIP_STANDARD_VER_RS_SET")
public class StandardVerRsSet extends MasterEntity {
    /**
     * 标准版本
     */
    @Column(name = "FK_VERSION_ID")
    private String fkVersionId;
    /**
     * CDA主键
     */
    @Column(name = "FK_CDA_ID")
    private String fkCdaId;
    /**
     * 数据集主键
     */
    @Column(name = "FK_SET_ID")
    private String fkSetId;

    public String getFkVersionId() {
        return this.fkVersionId;
    }


    public void setFkVersionId(String fkVersionId) {
        this.fkVersionId = fkVersionId;
    }

    public String getFkCdaId() {
        return this.fkCdaId;
    }


    public void setFkCdaId(String fkCdaId) {
        this.fkCdaId = fkCdaId;
    }

    public String getFkSetId() {
        return this.fkSetId;
    }


    public void setFkSetId(String fkSetId) {
        this.fkSetId = fkSetId;
    }
}
