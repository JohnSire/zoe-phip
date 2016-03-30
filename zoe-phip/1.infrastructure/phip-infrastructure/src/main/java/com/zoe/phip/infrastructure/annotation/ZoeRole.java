package com.zoe.phip.infrastructure.annotation;

import com.zoe.phip.infrastructure.security.MenuCode;
import com.zoe.phip.infrastructure.security.Permission;

import java.lang.annotation.*;

/**
 * Created by zengjiyang on 2016/3/29.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ZoeRole {
    /**
     * 权限
     * @return
     */
    Permission[] permission();

    /**
     * 菜单编码
     * @return
     */
    MenuCode[] code();

}
