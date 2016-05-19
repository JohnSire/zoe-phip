package com.zoe.phip.infrastructure.annotation;

import com.zoe.phip.infrastructure.security.MenuCode;

import java.lang.annotation.*;

/**
 * Created by zengjiyang on 2016/3/31.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthControllers {
    /**
     * 菜单编码
     *
     * @return
     */
    AuthController[] value();
}
