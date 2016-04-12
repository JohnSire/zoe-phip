package com.zoe.phip.infrastructure.annotation;

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
    String value() default "";

    /**
     * 描述
     *
     * @return
     */

    String descr() default "";

    //默认值,如果路径为空,取默认
    String defaultValue() default "";

    //级别(0:单个,1:多个节点)
    int level() default 0;
}
