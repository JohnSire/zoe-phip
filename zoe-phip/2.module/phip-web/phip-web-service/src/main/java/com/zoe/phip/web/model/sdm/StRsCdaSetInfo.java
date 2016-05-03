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
 * @date 2016-05-03
 */
@Table(name = "PHIP_ST_RS_CDA_SET_INFO")
public class StRsCdaSetInfo extends MasterEntity {
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
