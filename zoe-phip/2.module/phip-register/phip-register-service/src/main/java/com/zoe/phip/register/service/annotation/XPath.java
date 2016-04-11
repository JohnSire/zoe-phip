package com.zoe.phip.register.service.annotation;

/**
 * Created by zhangwenbin on 2016/4/11.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 注册服务属性路径注解
 */
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface XPath {

    /**
     * 路径
     *
     * @return
     */
    String value();

    /**
     * 描述
     *
     * @return
     */

    String descr() default "";
}
