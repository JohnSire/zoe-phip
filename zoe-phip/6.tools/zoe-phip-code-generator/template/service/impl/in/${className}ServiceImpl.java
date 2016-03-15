<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>

package ${basepackage}.service.impl.in;

import ${basepackage}.model.Dept;
import ${basepackage}.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author
 * @date ${now?string("yyyy-MM-dd")}
 * @version 1.0
 */
@Service("${className?uncap_first}Service")
public class ${className}Impl extends BaseInService<${className}> {

}