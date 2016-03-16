package com.zoe.phip.infrastructure.aop;

import java.lang.annotation.*;

/**
 * Created by 文彬 on 2016/3/16.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Action {

    /**
     * 要执行的操作类型
     **/
    public String operationType() default "NONE";

    /**
     * 要执行的方法的描述
     **/
    public String description() default "NONE";
}
