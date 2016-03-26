package com.zoe.phip.infrastructure.aop;

import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.entity.SystemData;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.StringUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Created by 文彬 on 2016/3/16.
 */
public class LoggerInterceptor {


    private Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);

    public void doBefore(JoinPoint joinPoint) {
        //如果方法的第一个参数不是SystemData，则报异常
        String methodName = joinPoint.getSignature().getName();
        if(!methodName.equals("login")){
            Class[] cls = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
            if(cls.length==0){
                throw new RuntimeException("该方法没有SystemData类型的参数!");
            }
            if(!cls[0].getName().equals(SystemData.class.getName())){
                throw new RuntimeException("方法的第一个参数必须为SystemData类型");
            }
        }
        // TODO: 2016/3/17 相关权限验证

        Object[] params = joinPoint.getArgs();

        if (!StringUtil.isNullOrWhiteSpace(methodName)) {

        }
    }

    public void doAfter(JoinPoint joinPoint) {
        // TODO: 2016/3/17
    }

    public void doAfterReturn(JoinPoint joinPoint, String result) {
        // TODO: 2016/3/17
    }

    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //参数
        Object[] params = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();
        if (!StringUtil.isNullOrWhiteSpace(methodName)) {
            if (!(methodName.startsWith("set") || methodName.startsWith("get") || methodName.startsWith("query"))) {
                Class clazz = joinPoint.getTarget().getClass();
                Method method = clazz.getMethod(methodName, ((MethodSignature) joinPoint.getSignature()).getParameterTypes());
                if (method != null) {
                    boolean hasAnnotation = method.isAnnotationPresent(Action.class);
                    if (hasAnnotation) {
                        Action action = method.getAnnotation(Action.class);
                        String operationType = action.operationType();
                        String description = action.description();
                        // TODO: 2016/3/16 拦截后，做什么？
                        //if (logger.isDebugEnabled()) {
                        //logger.debug(operationType + ":" + description);
                        //}
                    }
                }
            }
        }
        Object result = joinPoint.proceed();
        if (result instanceof ServiceResultT) {
//            ((ServiceResultT) result).setIsSuccess(true);
        } else if (result instanceof ServiceResult) {
//            ((ServiceResult) result).setIsSuccess(true);
        } else {
            throw new Exception("方法的返回类型只能为ServiceResult或ServiceResultT");
        }

        return result;
    }

    public void doAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        // TODO: 2016/3/17 异常处理
        if (logger.isErrorEnabled())
            logger.error("异常处理消息", ex);
    }
}
