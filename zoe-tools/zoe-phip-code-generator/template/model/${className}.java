<#include "/java_copyright.include">
<#assign className=table.className>
<#assign classNameLower=className?uncap_first>
        package ${basepackage}.model;

        import javax.persistence.*;
        import java.util.Date;
        import com.zoe.phip.module.service.entity.MasterEntity;


/**
 * @author
 * @version 1.0
 * @date ${now?string("yyyy-MM-dd")}
 */
@Table(name = "${table.sqlName}")
public class ${className} extends MasterEntity{
<#list table.columns as field>
<#assign type=field.javaType/>
<#if field.javaType=="java.sql.Date">
<#assign type="Date"/>
</#if>
<#if field.sqlName!="ID"&field.sqlName!="CREATE_AT"&field.sqlName!="CREATE_BY"&field.sqlName!="MODIFY_AT"&field.sqlName!="MODIFY_BY"&field.sqlName!="STATE">
/**
 * ${field.remarks}
 */
@Column(name = "${field.sqlName}")
private ${type} ${field.entityName?uncap_first};
</#if>
</#list>

<#list table.columns as field>
<#assign type=field.javaType/>
<#if field.javaType=="java.sql.Date">
   <#assign type="Date"/>
</#if>
<#if field.sqlName!="ID"&field.sqlName!="CREATE_AT"&field.sqlName!="CREATE_BY"&field.sqlName!="MODIFY_AT"&field.sqlName!="MODIFY_BY"&field.sqlName!="STATE">
public ${type} get${field.entityName?cap_first}(){
        return this.${field.entityName?uncap_first};
        }


public void set${field.entityName?cap_first}(${type} ${field.entityName?uncap_first}){
        this.${field.entityName?uncap_first}=${field.entityName?uncap_first};
        }
</#if>
</#list>
        }
