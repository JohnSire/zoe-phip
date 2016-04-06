<#include"/java_copyright.include">
        package com.zoe.phip.dto;

/**
 * @author
 * @version 1.0
 * @date 2016-04-01
 */
public class SystemParameter extends CoreModel{
/**
 * 主键
 */
private java.lang.Stringid;

/**
 * 名称
 */
private java.lang.Stringname;

/**
 * 编码
 */
private java.lang.Stringcode;

/**
 * 值
 */
private java.lang.Stringvalue;

/**
 * 值定义
 */
private java.lang.StringvalueDefinition;

/**
 * 描述
 */
private java.lang.Stringdescr;

/**
 * 创建时间
 */
private java.sql.TimestampcreateAt;

/**
 * 创建人
 */
private java.lang.StringcreateBy;

/**
 * 更新时间
 */
private java.sql.TimestampmodifyAt;

/**
 * 更新人
 */
private java.lang.StringmodifyBy;

/**
 * 类型
 */
private java.math.BigDecimaltype;

/**
 * 正则表达式
 */
private java.lang.StringvalidateRegular;

/**
 * 错误提示消息
 */
private java.lang.StringvalidateMessage;

/**
 * 状态
 */
private java.math.BigDecimalstate;


public java.lang.StringgetId(){
        return this.id;
        }

public void setId(java.lang.Stringid){
        this.id=id;
        }

public java.lang.StringgetName(){
        return this.name;
        }

public void setName(java.lang.Stringname){
        this.name=name;
        }

public java.lang.StringgetCode(){
        return this.code;
        }

public void setCode(java.lang.Stringcode){
        this.code=code;
        }

public java.lang.StringgetValue(){
        return this.value;
        }

public void setValue(java.lang.Stringvalue){
        this.value=value;
        }

public java.lang.StringgetValueDefinition(){
        return this.valueDefinition;
        }

public void setValueDefinition(java.lang.StringvalueDefinition){
        this.valueDefinition=valueDefinition;
        }

public java.lang.StringgetDescr(){
        return this.descr;
        }

public void setDescr(java.lang.Stringdescr){
        this.descr=descr;
        }

public java.sql.TimestampgetCreateAt(){
        return this.createAt;
        }

public void setCreateAt(java.sql.TimestampcreateAt){
        this.createAt=createAt;
        }

public java.lang.StringgetCreateBy(){
        return this.createBy;
        }

public void setCreateBy(java.lang.StringcreateBy){
        this.createBy=createBy;
        }

public java.sql.TimestampgetModifyAt(){
        return this.modifyAt;
        }

public void setModifyAt(java.sql.TimestampmodifyAt){
        this.modifyAt=modifyAt;
        }

public java.lang.StringgetModifyBy(){
        return this.modifyBy;
        }

public void setModifyBy(java.lang.StringmodifyBy){
        this.modifyBy=modifyBy;
        }

public java.math.BigDecimalgetType(){
        return this.type;
        }

public void setType(java.math.BigDecimaltype){
        this.type=type;
        }

public java.lang.StringgetValidateRegular(){
        return this.validateRegular;
        }

public void setValidateRegular(java.lang.StringvalidateRegular){
        this.validateRegular=validateRegular;
        }

public java.lang.StringgetValidateMessage(){
        return this.validateMessage;
        }

public void setValidateMessage(java.lang.StringvalidateMessage){
        this.validateMessage=validateMessage;
        }

public java.math.BigDecimalgetState(){
        return this.state;
        }

public void setState(java.math.BigDecimalstate){
        this.state=state;
        }

        }
