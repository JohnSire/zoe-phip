/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.register.model;

import javax.persistence.*;


import com.zoe.phip.module.service.entity.MasterEntity;

import java.util.Date;


/**
 * @author
 * @version 1.0
 * @date 2016-04-11
 */
@Table(name = "PHIP_AREA_BASE_INFO")
public class AreaBaseInfo extends MasterEntity {
    /**
     * 区域代码
     */
    @Column(name = "CODE")
    private String code;
    /**
     * 区域名称
     */
    @Column(name = "NAME")
    private String name;
    /**
     * 父级区域
     */
    @Column(name = "P_AREA_ID")
    private String pareaId;
    /**
     * 成立时间
     */
    @Column(name = "BUILD_TIME")
    private Date buildTime;
    /**
     * 注销日期
     */
    @Column(name = "AREA_CANCELLATION_DATE")
    private Date areaCancellationDate;
    /**
     * 注销原因
     */
    @Column(name = "AREA_CANCEL_RESON")
    private String areaCancelReson;
    /**
     * 注销前区域信息，用以做历史沿革关联
     */
    @Column(name = "HISTORY_AREA_ID")
    private String historyAreaId;

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

    public String getPareaId() {
        return this.pareaId;
    }

    public void setPareaId(String pareaId) {
        this.pareaId = pareaId;
    }

    public Date getBuildTime() {
        return this.buildTime;
    }

    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }

    public Date getAreaCancellationDate() {
        return this.areaCancellationDate;
    }

    public void setAreaCancellationDate(Date areaCancellationDate) {
        this.areaCancellationDate = areaCancellationDate;
    }

    public String getAreaCancelReson() {
        return this.areaCancelReson;
    }

    public void setAreaCancelReson(String areaCancelReson) {
        this.areaCancelReson = areaCancelReson;
    }

    public String getHistoryAreaId() {
        return this.historyAreaId;
    }

    public void setHistoryAreaId(String historyAreaId) {
        this.historyAreaId = historyAreaId;
    }
}
