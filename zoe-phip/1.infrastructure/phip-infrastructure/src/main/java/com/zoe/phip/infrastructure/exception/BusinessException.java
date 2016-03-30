package com.zoe.phip.infrastructure.exception;

import com.zoe.phip.infrastructure.entity.ErrorCode;

import java.text.MessageFormat;

/**
 * Created by 文彬 on 2016/3/22.
 */
public class BusinessException extends Exception {

    /**
     * 错误代码
     */
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BusinessException(String message) {
        super(message);
        this.code= ErrorCode.DEFAULT;
    }

    public BusinessException(String message, Object... args) {
        super(MessageFormat.format(message, args));
        this.code= ErrorCode.DEFAULT;
    }

    public BusinessException(String message,String code, Object... args) {
        super(MessageFormat.format(message, args));
        this.code=code;
    }
}
