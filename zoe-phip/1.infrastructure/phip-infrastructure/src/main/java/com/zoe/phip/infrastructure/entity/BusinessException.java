package com.zoe.phip.infrastructure.entity;

import java.text.MessageFormat;

/**
 * Created by 文彬 on 2016/3/22.
 */
public class BusinessException extends Exception {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Object... args) {
        super(MessageFormat.format(message, args));
    }
}
