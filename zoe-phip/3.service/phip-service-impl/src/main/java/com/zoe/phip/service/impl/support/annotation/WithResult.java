package com.zoe.phip.service.impl.support.annotation;

import java.lang.annotation.*;

/**
 * Created by qiuyungen on 2016/3/28.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface WithResult {
}
