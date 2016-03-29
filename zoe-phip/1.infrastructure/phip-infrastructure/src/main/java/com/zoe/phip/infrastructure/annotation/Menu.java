package com.zoe.phip.infrastructure.annotation;

import java.lang.annotation.*;

/**
 * Created by zengjiyang on 2016/3/29.
 */
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface  Menu{
    String uri();

    String code();

    int order();
}
