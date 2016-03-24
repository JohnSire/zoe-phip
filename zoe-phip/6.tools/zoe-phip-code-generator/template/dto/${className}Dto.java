<#include"/java_copyright.include">
<#assign className=table.className>
<#assign classNameLower=className?uncap_first>
        package ${basepackage}.dto;

/**
 * @author
 * @version 1.0
 * @date ${now?string("yyyy-MM-dd")}
 */
public class $ {
    className
}extends CoreModel{
<#list table.columns as field>
/**
 * ${field.remarks}
 */
private ${field.javaType}${field.entityName?uncap_first};

</#list>

<#list table.columns as field>
public ${field.javaType}get${field.entityName?cap_first}(){
        return this.${field.entityName?uncap_first};
        }

public void set${field.entityName?cap_first}(${field.javaType}${field.entityName?uncap_first}){
        this.${field.entityName?uncap_first}=${field.entityName?uncap_first};
        }

</#list>
        }
