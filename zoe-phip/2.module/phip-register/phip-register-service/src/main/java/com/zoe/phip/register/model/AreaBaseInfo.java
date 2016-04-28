/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.register.model;

import javax.persistence.*;


import com.alibaba.fastjson.annotation.JSONField;
import com.zoe.phip.infrastructure.annotation.XPath;
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
    @XPath(value = "//code/@value")
    private String code;
    /**
     * 区域名称
     */
    @Column(name = "NAME")
    @XPath(value = "//name/@value")
    private String name;
    /**
     * 父级区域
     */
    @Column(name = "PID")
    @XPath(value = "//Pid/@value")
    private String Pid;
    /**
     * 成立时间
     */
    @Column(name = "BUILD_TIME")
    @XPath(value = "//buildTime/@value")
    private Date buildTime;

    public Date getAreaCancellationDate() {
        return areaCancellationDate;
    }

    public void setAreaCancellationDate(Date areaCancellationDate) {
        this.areaCancellationDate = areaCancellationDate;
    }

    /**
     * 注销日期
     */
    @Column(name = "AREA_CANCELLATION_DATE")
    @XPath(value = "//areaCancellationDate/@value")
    @JSONField(format = "yyyy-MM-dd")
    private Date areaCancellationDate;

    public String getAreaCancelReason() {
        return areaCancelReason;
    }

    public void setAreaCancelReason(String areaCancelReason) {
        this.areaCancelReason = areaCancelReason;
    }

    /**
     * 注销原因
     */
    @Column(name = "AREA_CANCEL_REASON")
    @XPath(value = "//areaCancelReason/@value")
    private String areaCancelReason;
    /**
     * 注销前区域信息，用以做历史沿革关联
     */
    @Column(name = "HISTORY_AREA_ID")
    @XPath(value = "//historyAreaId/@value")
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

    public String getPid() {
        return this.Pid;
    }

    public void setPid(String Pid) {
        this.Pid = Pid;
    }

    public Date getBuildTime() {
        return this.buildTime;
    }

    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }

    public String getHistoryAreaId() {
        return this.historyAreaId;
    }

    public void setHistoryAreaId(String historyAreaId) {
        this.historyAreaId = historyAreaId;
    }
}
