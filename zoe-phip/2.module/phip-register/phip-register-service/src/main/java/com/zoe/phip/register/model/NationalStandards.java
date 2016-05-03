/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.register.model;

import com.zoe.phip.module.service.entity.MasterEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;


/**
 * @author
 * @version 1.0
 * @date 2016-04-25
 */
@Table(name = "PHIP_NATIONAL_STANDARDS")
public class NationalStandards extends MasterEntity {
    /**
     * 编码,内部使用
     */
    @Column(name = "CODE")
    private String code;
    /**
     * 标准名称
     */
    @Column(name = "NAME")
    private String name;
    /**
     * 代码
     */
    @Column(name = "CODE_SYSTEM")
    private String codeSystem;



    /**
     * 标准编码
     */
    @Column(name = "DICT_CODE")
    private String dictCode;

    /**
     * 描述
     */
    @Column(name = "DESCR")
    private String descr;

    @Transient
    private List<DictItem> dictItemList;

    public String getDictCode() {
        return this.dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }


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

    public String getCodeSystem() {
        return this.codeSystem;
    }


    public void setCodeSystem(String codeSystem) {
        this.codeSystem = codeSystem;
    }

    public String getDescr() {
        return this.descr;
    }


    public void setDescr(String descr) {
        this.descr = descr;
    }
    public List<DictItem> getDictItemList() {
        return dictItemList;
    }

    public void setDictItemList(List<DictItem> dictItemList) {
        this.dictItemList = dictItemList;
    }
}
