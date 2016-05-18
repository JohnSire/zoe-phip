package com.zoe.phip.web.aop;

import com.alibaba.dubbo.rpc.RpcException;
import com.zoe.phip.infrastructure.entity.ErrorCode;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.infrastructure.util.StringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;

/**
 * Created by zengjiyang on 2016/3/14.
 */
@Aspect
public class OperationBehavior {

    private static final Logger logger = LoggerFactory.getLogger(OperationBehavior.class);

    @Pointcut("execution(* com.zoe.phip.web.controller..*.*(..))")
    private void pointCutMethod() {
    }

    //声明前置通知
    @Before("pointCutMethod()")
    public void doBefore() {
        //todo 相关权限验证
    }

    //声明后置通知
    @AfterReturning(pointcut = "pointCutMethod()", returning = "result")
    public void doAfterReturning(String result) {
    }

    //声明例外通知
    @AfterThrowing(pointcut = "pointCutMethod()", throwing = "e")
    public void doAfterThrowing(Exception e) {
        //todo 错误处理
    }


    //声明最终通知
    @After("pointCutMethod()")
    public void doAfter() {
    }

    //声明环绕通知  统一作错误处理
    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Class cl = ((MethodSignature) joinPoint.getSignature()).getReturnType();
        //返回值是string类型，表示是视图
        if (cl == String.class) {
            return joinPoint.proceed();
        }
        Object result;
        try {
            result = joinPoint.proceed();
        }
        catch (RpcException e){
            logger.error("error:", e);
            ServiceResult executeResult;
            if (cl == ServiceResult.class) {
                executeResult = new ServiceResult();
            } else {
                executeResult = new ServiceResultT();
            }
            if(e.getCode()==2){
                executeResult.addMessage("", "后台连接超时，请重新操作!");
            }else {
                executeResult.addMessage("", e.toString());
            }
            executeResult.addLogData(e.toString());
            executeResult.addLogData(SafeExecuteUtil.getStackMsg(e));
            executeResult.setIsSuccess(false);
            return executeResult;
        }
        catch (Throwable e) {
            logger.error("error:", e);
            ServiceResult executeResult;
            if (cl == ServiceResult.class) {
                executeResult = new ServiceResult();
            } else {
                executeResult = new ServiceResultT();
            }
            executeResult.addMessage("", e.toString());
            executeResult.addLogData(e.toString());
            executeResult.addLogData(SafeExecuteUtil.getStackMsg(e));
            executeResult.setIsSuccess(false);
            return executeResult;
        }
        return result;
    }
}
