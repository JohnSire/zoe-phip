<#include "/java_copyright.include">
<#assign className=table.className>
<#assign classNameLower=className?uncap_first>
        package ${basepackage}.model;

        import javax.persistence.*;


        import ${basepackage}.model.base.BaseEntity;


/**
 * @author
 * @version 1.0
 * @date ${now?string("yyyy-MM-dd")}
 */
@Table(name = "${table.sqlName}")
public class ${className} extends BaseEntity{
<#list table.columns as field>
<#if field.sqlName!="ID"&field.sqlName!="CREATE_AT"&field.sqlName!="CREATE_BY"&field.sqlName!="MODIFY_AT"&field.sqlName!="MODIFY_BY">
/**
 * ${field.remarks}
 */
@Column(name = "${field.sqlName}")
private ${field.javaType} ${field.entityName?uncap_first};
</#if>
</#list>

<#list table.columns as field>
<#if field.sqlName!="ID"&field.sqlName!="CREATE_AT"&field.sqlName!="CREATE_BY"&field.sqlName!="MODIFY_AT"&field.sqlName!="MODIFY_BY">
public ${field.javaType} get${field.entityName?cap_first}(){
        return this.${field.entityName?uncap_first};
        }

public void set${field.entityName?cap_first}(${field.javaType} ${field.entityName?uncap_first}){
        this.${field.entityName?uncap_first}=${field.entityName?uncap_first};
        }
</#if>
</#list>
        }
