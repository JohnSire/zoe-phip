/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.model.sm;

import com.zoe.phip.model.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author
 * @version 1.0
 * @date 2016-03-22
 */
@Table(name = "SYS_SYSTEM_PARAMETER")
public class SystemParameter extends BaseEntity {
    /**
     * 名称
     */
    @Column(name = "NAME")
    private String name;
    /**
     * 编码
     */
    @Column(name = "CODE")
    private String code;
    /**
     * 值
     */
    @Column(name = "VALUE")
    private String value;
    /**
     * 值定义
     */
    @Column(name = "VALUE_DEFINITION")
    private String valueDefinition;
    /**
     * 描述
     */
    @Column(name = "DESCR")
    private String descr;
    /**
     * 类型
     */
    @Column(name = "TYPE")
    private java.math.BigDecimal type;
    /**
     * 正则表达式
     */
    @Column(name = "VALIDATE_REGULAR")
    private String validateRegular;
    /**
     * 错误提示消息
     */
    @Column(name = "VALIDATE_MESSAGE")
    private String validateMessage;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueDefinition() {
        return this.valueDefinition;
    }

    public void setValueDefinition(String valueDefinition) {
        this.valueDefinition = valueDefinition;
    }

    public String getDescr() {
        return this.descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public java.math.BigDecimal getType() {
        return this.type;
    }

    public void setType(java.math.BigDecimal type) {
        this.type = type;
    }

    public String getValidateRegular() {
        return this.validateRegular;
    }

    public void setValidateRegular(String validateRegular) {
        this.validateRegular = validateRegular;
    }

    public String getValidateMessage() {
        return this.validateMessage;
    }

    public void setValidateMessage(String validateMessage) {
        this.validateMessage = validateMessage;
    }
}
