/*
 * Powered By zoe
 * Since 2008 - 2016
 */

        package com.zoe.phip.model;

        import javax.persistence.*;


        import com.zoe.phip.model.base.BaseEntity;


/**
 * @author
 * @version 1.0
 * @date 2016-04-01
 */
@Table(name = "SYS_SYSTEM_PARAMETER")
public class SystemParameter extends BaseEntity{
/**
 * 名称
 */
@Column(name = "NAME")
private java.lang.String name;
/**
 * 编码
 */
@Column(name = "CODE")
private java.lang.String code;
/**
 * 值
 */
@Column(name = "VALUE")
private java.lang.String value;
/**
 * 值定义
 */
@Column(name = "VALUE_DEFINITION")
private java.lang.String valueDefinition;
/**
 * 描述
 */
@Column(name = "DESCR")
private java.lang.String descr;
/**
 * 类型
 */
@Column(name = "TYPE")
private java.math.BigDecimal type;
/**
 * 正则表达式
 */
@Column(name = "VALIDATE_REGULAR")
private java.lang.String validateRegular;
/**
 * 错误提示消息
 */
@Column(name = "VALIDATE_MESSAGE")
private java.lang.String validateMessage;
/**
 * 状态
 */
@Column(name = "STATE")
private java.math.BigDecimal state;

public java.lang.String getName(){
        return this.name;
        }

public void setName(java.lang.String name){
        this.name=name;
        }
public java.lang.String getCode(){
        return this.code;
        }

public void setCode(java.lang.String code){
        this.code=code;
        }
public java.lang.String getValue(){
        return this.value;
        }

public void setValue(java.lang.String value){
        this.value=value;
        }
public java.lang.String getValueDefinition(){
        return this.valueDefinition;
        }

public void setValueDefinition(java.lang.String valueDefinition){
        this.valueDefinition=valueDefinition;
        }
public java.lang.String getDescr(){
        return this.descr;
        }

public void setDescr(java.lang.String descr){
        this.descr=descr;
        }
public java.math.BigDecimal getType(){
        return this.type;
        }

public void setType(java.math.BigDecimal type){
        this.type=type;
        }
public java.lang.String getValidateRegular(){
        return this.validateRegular;
        }

public void setValidateRegular(java.lang.String validateRegular){
        this.validateRegular=validateRegular;
        }
public java.lang.String getValidateMessage(){
        return this.validateMessage;
        }

public void setValidateMessage(java.lang.String validateMessage){
        this.validateMessage=validateMessage;
        }
public java.math.BigDecimal getState(){
        return this.state;
        }

public void setState(java.math.BigDecimal state){
        this.state=state;
        }
        }
