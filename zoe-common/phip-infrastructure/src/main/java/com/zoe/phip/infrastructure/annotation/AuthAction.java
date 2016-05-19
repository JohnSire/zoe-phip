package com.zoe.phip.infrastructure.annotation;

import com.zoe.phip.infrastructure.security.Permission;

import java.lang.annotation.*;

/**
 * Created by zengjiyang on 2016/3/31.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthAction {
    /**
     * 权限
     *
     * @return
     */
    Permission[] permission();

    /**
     * 中文名称
     *
     * @return
     */
    String name();
}
