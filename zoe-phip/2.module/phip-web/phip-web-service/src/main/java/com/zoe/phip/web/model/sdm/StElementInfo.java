/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.web.model.sdm;

import com.zoe.phip.module.service.entity.MasterEntity;

import javax.persistence.*;
import java.util.Date;


/**
 * @author
 * @version 1.0
 * @date 2016-05-03
 */
@Table(name = "PHIP_ST_ELEMENT_INFO")
public class StElementInfo extends MasterEntity {
    /**
     * 标识
     */
    @Column(name = "CODE")
    private String code;
    /**
     * 名称
     */
    @Column(name = "NAME")
    private String name;
    /**
     * 标准来源
     */
    @Column(name = "FK_SOURCE_ID")
    private String fkSourceId;
    /**
     * 分类
     */
    @Column(name = "FK_TYPE_ID")
    private String fkTypeId;
    /**
     * 字典关联
     */
    @Column(name = "FK_DICT_ID")
    private String fkDictId;
    /**
     * 数据类型
     */
    @Column(name = "DATA_TYPE")
    private String dataType;
    /**
     * 表示格式
     */
    @Column(name = "DATA_FORMAT")
    private String dataFormat;
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
     * 允许值
     */
    @Column(name = "ALLOW_VALUE")
    private String allowValue;
    /**
     * 定义
     */
    @Column(name = "DEFINE")
    private String define;
    /**
     *
     */
    @Column(name = "DESCR")
    private String descr;

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

    public String getFkSourceId() {
        return this.fkSourceId;
    }


    public void setFkSourceId(String fkSourceId) {
        this.fkSourceId = fkSourceId;
    }

    public String getFkTypeId() {
        return this.fkTypeId;
    }


    public void setFkTypeId(String fkTypeId) {
        this.fkTypeId = fkTypeId;
    }

    public String getFkDictId() {
        return this.fkDictId;
    }


    public void setFkDictId(String fkDictId) {
        this.fkDictId = fkDictId;
    }

    public String getDataType() {
        return this.dataType;
    }


    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataFormat() {
        return this.dataFormat;
    }


    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }

    public Date getStartTime() {
        return this.startTime;
    }


    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }


    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getAllowValue() {
        return this.allowValue;
    }


    public void setAllowValue(String allowValue) {
        this.allowValue = allowValue;
    }

    public String getDefine() {
        return this.define;
    }


    public void setDefine(String define) {
        this.define = define;
    }

    public String getDescr() {
        return this.descr;
    }


    public void setDescr(String descr) {
        this.descr = descr;
    }

    /**
     * 标准来源名称
     */
    @Transient
    private String sourceName;

    @Transient
    private String DictCode;

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    /**
     * 字典代码
     */
    @Transient
    private String dictCode;

    /**
     * 字典名称
     */
    @Transient
    private String dictName;


    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    /**
     * 分类名称
     */
    @Transient
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
