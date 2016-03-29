<#include "/java_copyright.include">
<#assign className=table.className>
<#assign classNameLower=className?uncap_first>

        package ${basepackage}.dto;

        import ${basepackage}.dao.MyMapper;
        import ${basepackage}.model.${className};

/**
 * @author
 * @version 1.0
 * @date ${now?string("yyyy-MM-dd")}
 */
public interface ${className}Mapper extends MyMapper<${className}>{

        }