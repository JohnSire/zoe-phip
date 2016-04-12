package com.zoe.phip.infrastructure.util;

import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.annotation.ErrorMessages;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

/**
 * Created by zhangwenbin on 2016/2/29.
 */
public final class SafeExecuteUtil {

    private static final Logger logger = LoggerFactory.getLogger(SafeExecuteUtil.class);

    public static ServiceResult execute(Function<Boolean> invoker, ErrorMessage[]  errors) {
        ServiceResult executeResult = new ServiceResult();
        try {
            executeResult.setIsSuccess(invoker.apply());
        } catch (BusinessException ex) {
            //日志消息
            setErrorMessage(errors,executeResult,ex);
            logger.error(ex.getMessage());
        } catch (Exception e) {
            //错误消息
            executeResult.setIsSuccess(false);
            executeResult.addLogData(e.toString());
            executeResult.addLogData(getStackMsg(e));
            logger.error("方法执行报错:", e);
        }
        return executeResult;
    }


    /**
     * 处理webservice服务  todo:错误返回处理 统一生成XML？
     * @param invoker
     * @param errors
     * @return
     */
    public static String executeWebService(Function<Object> invoker, ErrorMessage[]  errors) {
        String result;
        try {
            result= (String) invoker.apply();
        } catch (BusinessException ex) {
            //日志消息
            result=ex.getMessage();
            logger.error(ex.getMessage());
        } catch (Exception e) {
            //错误消息
            result=e.getMessage();
            logger.error("方法执行报错:", e);
        }
        return result;
    }

    public static <R> ServiceResultT<R> execute0(Function<Object> invoker,ErrorMessage[]  errors) {
        ServiceResultT<R> executeResult = new ServiceResultT<R>();
        try {
            R result = (R) invoker.apply();
            executeResult.setResult(result);
            executeResult.setIsSuccess(result != null);
        } catch (BusinessException ex) {
            //日志消息
            setErrorMessage(errors,executeResult,ex);
            logger.error(ex.getMessage());
        } catch (Exception e) {
            executeResult.setIsSuccess(false);
            executeResult.addLogData(e.toString());
            executeResult.addLogData(getStackMsg(e));
            logger.error("方法执行报错:", e);
        }
        return executeResult;
    }

    private static void setErrorMessage(ErrorMessage[]  errors,ServiceResult result,BusinessException ex){
        if(errors!=null){
            for (ErrorMessage er : errors) {
                if(er.code().equals(ex.getCode())){
                    String message= MessageFormat.format(er.message(),ex.getArguments());
                    result.addMessage(er.code(),message);
                    result.setIsSuccess(false);
                    break;
                }
            }
        }
    }

    public static String getStackMsg(Throwable e) {

        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stackArray = e.getStackTrace();
        for (int i = 0; i < stackArray.length; i++) {
            StackTraceElement element = stackArray[i];
            String msg = element.toString();
            if (msg.startsWith("com.zoe")) {
                sb.append(element.toString() + "\n");
            }
        }
        return sb.toString();
    }
}

