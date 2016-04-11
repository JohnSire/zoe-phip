package com.zoe.phip.module.service.validator;

import java.util.Map;

/**
 * Created by huangyinfu on 2016/4/11.
 */
public class ValidationResult {

    //校验结果是否有错
    private boolean hasErrors;

    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    //校验错误信息
    private Map<String,String> errorMsg;

    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public Map<String, String> getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Map<String, String> errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "ValidationResult [hasErrors=" + hasErrors + ", errorMsg="
                + errorMsg + "]";
    }

}
