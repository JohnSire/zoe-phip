<#include"/java_copyright.include">
<#assign className=table.className>
<#assign classNameLower=className?uncap_first>

        package ${basepackage}.service.in;

        import ${basepackage}.model.${className};
        import ${basepackage}.service.in.BaseInService;

/**
 * 对外发布的服务接口
 *
 * @author
 * @version 1.0
 * @date ${now?string("yyyy-MM-dd")}
 */
public interface $ {
    className
}Service extends BaseInService<${className}>{

        }