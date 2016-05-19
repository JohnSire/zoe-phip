/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.web.model.sdm;

import com.zoe.phip.module.service.entity.MasterEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;


/**
 * @author
 * @version 1.0
 * @date 2016-05-03
 */
@Table(name = "PHIP_ST_SET_INFO")
public class StSetInfo extends MasterEntity {

    /**
     * 编码
     */
    @Column(name = "CODE")
    private String code;
    /**
     * 名称
     */
    @Column(name = "NAME")
    private String name;
    /**
     * 父级数据集
     */
    @Column(name = "PID")
    private String pid;
    /**
     * 别名
     */
    @Column(name = "ALIAS")
    private String alias;
    /**
     * 数据来源
     */
    @Column(name = "FK_NORM_SOURCE_ID")
    private String fkNormSourceId;
    /**
     * 数据集路径
     */
    @Column(name = "XPATH")
    private String xpath;
    /**
     * 业务时间对应的字段
     */
    @Column(name = "BUSS_TIME_FIELD_ID")
    private String bussTimeFieldId;

    /**
     * 起用时间
     */
    @Column(name = "START_TIME")
    private Date startTime;
    /**
     * 停用时间
     */
    @Column(name = "END_TIME")
    private Date endTime;


    /**
     * 版本
     */
    @Column(name = "VERSION")
    private java.math.BigDecimal version;

    /**
     *
     */
    @Column(name = "DESCR")
    private String descr;

    /**
     * 业务时间对应的字段
     */
    @Column(name = "UPDATE_TIME_FIELD_ID")
    private String updateTimeFiledId;

    @Transient
    private String sourceName;

    @Transient
    private String pName;

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getBUSS_TIME_NAME() {
        return BUSS_TIME_NAME;
    }

    public void setBUSS_TIME_NAME(String BUSS_TIME_NAME) {
        this.BUSS_TIME_NAME = BUSS_TIME_NAME;
    }

    public String getUPDATE_TIME_NAME() {
        return UPDATE_TIME_NAME;
    }

    public void setUPDATE_TIME_NAME(String UPDATE_TIME_NAME) {
        this.UPDATE_TIME_NAME = UPDATE_TIME_NAME;
    }

    @Transient
    private String BUSS_TIME_NAME;

    @Transient
    private String UPDATE_TIME_NAME;



    public String getCode() {
        return this.code;
    }

    public String getUpdateTimeFiledId() {
        return updateTimeFiledId;
    }

    public void setUpdateTimeFiledId(String updateTimeFiledId) {
        this.updateTimeFiledId = updateTimeFiledId;
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

    public String getPid() {
        return this.pid;
    }


    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getAlias() {
        return this.alias;
    }


    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getFkNormSourceId() {
        return this.fkNormSourceId;
    }


    public void setFkNormSourceId(String fkNormSourceId) {
        this.fkNormSourceId = fkNormSourceId;
    }

    public String getXpath() {
        return this.xpath;
    }


    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    public String getBussTimeFieldId() {
        return this.bussTimeFieldId;
    }


    public void setBussTimeFieldId(String bussTimeFieldId) {

        this.bussTimeFieldId = bussTimeFieldId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public java.math.BigDecimal getVersion() {
        return this.version;
    }


    public void setVersion(java.math.BigDecimal version) {
        this.version = version;
    }

    public String getDescr() {
        return this.descr;
    }


    public void setDescr(String descr) {
        this.descr = descr;
    }



    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }


}
