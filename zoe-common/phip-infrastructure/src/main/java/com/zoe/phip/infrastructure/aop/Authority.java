package com.zoe.phip.infrastructure.aop;

import java.lang.annotation.*;

/**
 * Created by 文彬 on 2016/3/24.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Authority {
}
