<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>

package ${basepackage}.service.out;

import ${basepackage}.dto.${className}Dto;
import ${basepackage}.service.BaseOutService;

/**
 * 对外发布的服务接口
 * @author
 * @date ${now?string("yyyy-MM-dd")}
 * @version 1.0
 */
public interface ${className}Service extends BaseOutService<${className}Dto>  {

}