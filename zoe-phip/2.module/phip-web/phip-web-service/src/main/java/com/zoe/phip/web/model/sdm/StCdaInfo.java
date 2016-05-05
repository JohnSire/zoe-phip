/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.web.model.sdm;

import com.zoe.phip.module.service.entity.MasterEntity;

import javax.persistence.*;


/**
 * @author
 * @version 1.0
 * @date 2016-05-03
 */
@Table(name = "PHIP_ST_CDA_INFO")
public class StCdaInfo extends MasterEntity {
    /**
     * CDA编码
     */
    @Column(name = "CODE")
    private String code;
    /**
     * CDA名称
     */
    @Column(name = "NAME")
    private String name;
    /**
     * 标准来源外键
     */
    @Column(name = "FK_NORM_SOURCE_ID")
    private String fkNormSourceId;
    /**
     * 定义
     */
    @Column(name = "DEFINE")
    private String define;
    /**
     * 档案显示xsl
     */
    @Column(name = "TO_HTML_XSL")
    private String toHtmlXsl;
    /**
     * 档案摘要显示xsl
     */
    @Column(name = "TO_SUMMARY_XSL")
    private String toSummaryXsl;
    /**
     * 档案生成解析xsl
     */
    @Column(name = "TO_SET_XSL")
    private String toSetXsl;
    /**
     * 样例xml
     */
    @Column(name = "SAMPLE_XML")
    private String sampleXml;
    /**
     * 直报表单
     */
    @Column(name = "FORM_XML")
    private String formXml;
    /**
     * 问卷调查
     */
    @Column(name = "QUESTION_HTML")
    private String questionHtml;
    /**
     * 档案类型
     */
    @Column(name = "ARCHIVES_TYPE")
    private String archivesType;
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

    public String getFkNormSourceId() {
        return this.fkNormSourceId;
    }


    public void setFkNormSourceId(String fkNormSourceId) {
        this.fkNormSourceId = fkNormSourceId;
    }

    public String getDefine() {
        return this.define;
    }


    public void setDefine(String define) {
        this.define = define;
    }

    public String getToHtmlXsl() {
        return this.toHtmlXsl;
    }


    public void setToHtmlXsl(String toHtmlXsl) {
        this.toHtmlXsl = toHtmlXsl;
    }

    public String getToSummaryXsl() {
        return this.toSummaryXsl;
    }


    public void setToSummaryXsl(String toSummaryXsl) {
        this.toSummaryXsl = toSummaryXsl;
    }

    public String getToSetXsl() {
        return this.toSetXsl;
    }


    public void setToSetXsl(String toSetXsl) {
        this.toSetXsl = toSetXsl;
    }

    public String getSampleXml() {
        return this.sampleXml;
    }


    public void setSampleXml(String sampleXml) {
        this.sampleXml = sampleXml;
    }

    public String getFormXml() {
        return this.formXml;
    }


    public void setFormXml(String formXml) {
        this.formXml = formXml;
    }

    public String getQuestionHtml() {
        return this.questionHtml;
    }


    public void setQuestionHtml(String questionHtml) {
        this.questionHtml = questionHtml;
    }

    public String getArchivesType() {
        return this.archivesType;
    }


    public void setArchivesType(String archivesType) {
        this.archivesType = archivesType;
    }

    public String getDescr() {
        return this.descr;
    }


    public void setDescr(String descr) {
        this.descr = descr;
    }

    /**
     * 档案类别名称
     */
    @Transient
    private String archiveName;

    public String getArchiveName() {
        return archiveName;
    }

    public void setArchiveName(String archiveName) {
        this.archiveName = archiveName;
    }
}
