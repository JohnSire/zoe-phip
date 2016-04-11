package com.zoe.phip.infrastructure.annotation;

import java.lang.annotation.*;
import java.util.Map;

/**
 * Created by zengjiyang on 2016/4/8.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(ErrorMessages.class)
public @interface ErrorMessage {

    /**
     * 错误代码
     * @return
     */
    String code();

    /**
     * 错误消息
     * @return
     */
    String message();

}
