package com.zoe.phip.infrastructure.entity;

import java.util.Objects;

/**
 * Created by 文彬 on 2016/3/22.
 */
public class BusinessException extends Exception {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Object... args) {
        super(String.format(message, args));
    }
}
