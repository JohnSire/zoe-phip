<#include"/java_copyright.include">
<#assign className=table.className>
<#assign classNameLower=className?uncap_first>

        package ${basepackage}.service.impl.out;

        import com.github.pagehelper.PageHelper;
        import com.github.pagehelper.PageInfo;
        import ${basepackage}.dto.*;

        import ${basepackage}.service.internal.BaseService;
        import ${basepackage}.service.out.DeptService;
        import ${basepackage}.model.Dept;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;
        import tk.mybatis.mapper.entity.Example;

/**
 * @author
 * @version 1.0
 * @date ${now?string("yyyy-MM-dd")}
 */
@Service("out${className?cap_first}Service")
public class $ {
    className
}Impl extends ${className}Service{

        }