<#include "/java_copyright.include">
<#assign className=table.className>
<#assign classNameLower=className?uncap_first>
        package ${basepackage}.service.impl.in;
        import ${basepackage}.model.${className};
        import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
        import ${basepackage}.service.impl.in.BaseInServiceImpl;
        import ${basepackage}.service.in.${className}Service;
        import org.springframework.stereotype.Repository;
        import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author
 * @version 1.0
 * @date ${now?string("yyyy-MM-dd")}
 */
@Repository("${className?uncap_first}Service")
@Service(interfaceClass = I${className}Service.class, proxy = "sdpf", dynamic = true)
public class ${className}ServiceImpl extends BaseInServiceImpl<${className},I${className}Mapper> implements I${className}Mapper{

        }