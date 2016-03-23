package com.zoe.phip.service.impl.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zengjiyang on 2016/3/14.
 */
@Aspect
public class OperationBehavior {

    private static final Logger logger = LoggerFactory.getLogger(OperationBehavior.class);

    @Pointcut("execution(* com.zoe.phip.service.in.*.*(..))")
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

    //声明环绕通知  这里可以作统一的错误处理
    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        //todo 是否记录方法执行时间
        Class cl = ((MethodSignature) pjp.getSignature()).getReturnType();
        logger.info("进入方法:" + pjp.getSignature().getName());
        Object result = pjp.proceed();
        logger.info("方法:" + pjp.getSignature().getName() + "执行完成！");
        return result;
    }
}
