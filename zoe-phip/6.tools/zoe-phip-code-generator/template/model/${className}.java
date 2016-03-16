<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.model;

import javax.persistence.*;

/**
 * @author
 * @date ${now?string("yyyy-MM-dd")}
 * @version 1.0
 */
public class ${className} extends BaseEntity {
<#list table.columns as field>
    /** ${field.remarks} */
    @Column(name = "${field.sqlName}")
    private ${field.javaType} ${field.entityName?uncap_first};

</#list>

<#list table.columns as field>
    public ${field.javaType} get${field.entityName?cap_first}(){
        return  this.${field.entityName?uncap_first};
    }

    public void set${field.entityName?cap_first}(${field.javaType} ${field.entityName?uncap_first}){
        this.${field.entityName?uncap_first} = ${field.entityName?uncap_first};
    }

</#list>
}
