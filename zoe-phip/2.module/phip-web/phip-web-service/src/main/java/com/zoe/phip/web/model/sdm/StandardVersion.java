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
@Table(name = "PHIP_STANDARD_VERSION")
public class StandardVersion extends MasterEntity {
    /**
     * 代码
     */
    @Column(name = "CODE")
    private String code;
    /**
     * 名称
     */
    @Column(name = "NAME")
    private String name;
    /**
     * 路径
     */
    @Column(name = "DOWNLOAD_URL")
    private String downloadUrl;
    /**
     * 版本号
     */
    @Column(name = "VERSION")
    private String version;
    /**
     * 父级版本
     */
    @Column(name = "PID")
    private String pid;
    /**
     * 版本标识(0:父级平台 1:本级平台)
     */
    @Column(name = "IDENTIFICATION")
    private Integer identification;
    /**
     * 上传状态
     */
    @Column(name = "UPLOAD_STATUS")
    private Integer uploadStatus;
    /**
     *
     */
    @Column(name = "DESCR")
    private String descr;

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    /**
     * 0:新增 1:更新
     */
    @Column(name = "IS_NEW")
    private Integer isNew;

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

    public String getDownloadUrl() {
        return this.downloadUrl;
    }


    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getVersion() {
        return this.version;
    }


    public void setVersion(String version) {
        this.version = version;
    }

    public String getPid() {
        return this.pid;
    }


    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getIdentification() {
        return this.identification;
    }


    public void setIdentification(Integer identification) {
        this.identification = identification;
    }

    public Integer getUploadStatus() {
        return this.uploadStatus;
    }


    public void setUploadStatus(Integer uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public String getDescr() {
        return this.descr;
    }


    public void setDescr(String descr) {
        this.descr = descr;
    }
}
