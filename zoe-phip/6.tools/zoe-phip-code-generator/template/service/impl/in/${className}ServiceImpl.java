<#include"/java_copyright.include">
<#assign className=table.className>
<#assign classNameLower=className?uncap_first>

        package ${basepackage}.service.impl.in;

        import ${basepackage}.model.${className};
        import ${basepackage}.service.impl.in.BaseInServiceImpl;
        import ${basepackage}.service.in.${className}Service;
        import org.springframework.stereotype.Service;

/**
 * @author
 * @version 1.0
 * @date ${now?string("yyyy-MM-dd")}
 */
@Service("${className?uncap_first}Service")
public class $ {
    className
}ServiceImpl extends BaseInServiceImpl<${className}>implements ${className}Service{

        }