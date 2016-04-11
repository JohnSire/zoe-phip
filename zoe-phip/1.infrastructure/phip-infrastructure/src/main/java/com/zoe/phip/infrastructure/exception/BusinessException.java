package com.zoe.phip.infrastructure.exception;

import com.zoe.phip.infrastructure.entity.ErrorCode;

import java.text.MessageFormat;
import java.util.List;

/**
 * Created by 文彬 on 2016/3/22.
 */
public class BusinessException extends Exception {

    /**
     * 错误代码
     */
    private String code;

    public Object[] getArguments() {
        return arguments;
    }

    private Object[] arguments;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BusinessException(String code, Object... args) {
        this.code = code;
        this.arguments = args;
    }
}
