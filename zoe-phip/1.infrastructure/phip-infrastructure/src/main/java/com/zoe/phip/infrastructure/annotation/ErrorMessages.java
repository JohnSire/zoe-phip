package com.zoe.phip.infrastructure.annotation;

import java.lang.annotation.*;

/**
 * Created by zengjiyang on 2016/4/8.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ErrorMessages {
    ErrorMessage[] value();
}
