package com.zoe.phip.infrastructure.aop;

import com.zoe.phip.infrastructure.util.StringUtil;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Created by 文彬 on 2016/3/16.
 */
public class LoggerInterceptor {
    private Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);

    public void doAround(JoinPoint point) throws Throwable {
        //参数
        Object[] params = point.getArgs();
        String methodName = point.getSignature().getName();
        if (!StringUtil.isNullOrWhiteSpace(methodName)) {
            if (!(methodName.startsWith("set") || methodName.startsWith("get") || methodName.startsWith("query"))) {
                Class clazz = point.getTarget().getClass();
                Method method = clazz.getMethod(methodName);
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
    }
}
