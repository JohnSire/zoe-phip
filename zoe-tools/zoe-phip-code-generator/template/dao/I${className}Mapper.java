<#include "/java_copyright.include">
<#assign className=table.className>
<#assign classNameLower=className?uncap_first>

        package ${basepackage}.dto;

        import ${basepackage}.module.service.mapper.IMyMapper;

/**
 * @author
 * @version 1.0
 * @date ${now?string("yyyy-MM-dd")}
 */
public interface I${className}Mapper extends IMyMapper<${className}>{

        }