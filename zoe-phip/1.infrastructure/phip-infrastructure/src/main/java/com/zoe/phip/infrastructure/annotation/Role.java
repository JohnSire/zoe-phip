package com.zoe.phip.infrastructure.annotation;

import com.zoe.phip.infrastructure.security.Permission;

import java.lang.annotation.*;

/**
 * Created by zengjiyang on 2016/3/29.
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface  Role {
    /**
     * 权限
     * @return
     */
    Permission[] permission();

    /**
     * 菜单编码
     * @return
     */
    String code() default "";

}
