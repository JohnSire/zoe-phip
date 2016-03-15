<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>

package ${basepackage}.dto;

import ${basepackage}.dao.MyMapper;
import ${basepackage}.model.${className};

/**
 * @author
 * @date ${now?string("yyyy-MM-dd")}
 * @version 1.0
 */
public interface ${className}Mapper extends MyMapper<${className}> {

 }