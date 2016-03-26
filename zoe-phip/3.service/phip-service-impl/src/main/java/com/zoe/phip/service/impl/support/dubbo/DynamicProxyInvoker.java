package com.zoe.phip.service.impl.support.dubbo;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.proxy.AbstractProxyInvoker;
import com.zoe.phip.infrastructure.bean.BeanFactory;
import com.zoe.phip.infrastructure.entity.SystemData;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.service.impl.proxy.BaseInService;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiuyungen on 2016/3/26.
 */
public class DynamicProxyInvoker<T> extends AbstractProxyInvoker<T> {
    private final Class interfaceClass;

    public DynamicProxyInvoker(T proxy, Class<T> type, URL url, Class clazz) {
        super(proxy, type, url);
        this.interfaceClass = clazz;
    }

    @Override
    protected Object doInvoke(T proxy, String methodName, Class<?>[] parameterTypes, Object[] arguments) throws Throwable {

        Object instance = BeanFactory.getBean(interfaceClass.getSimpleName());

        Class<?>[] types;
        Class firstClass = null;
        if (parameterTypes.length != 0) {
            List<Class<?>> parameterTypeList = new ArrayList<Class<?>>();
            boolean first = true;
            for (Class<?> parameterType : parameterTypes) {
                if (first) {
                    firstClass = parameterType;
                    first = false;
                    continue;
                }
                parameterTypeList.add(parameterType);
            }
            types = new Class<?>[parameterTypes.length - 1];
            parameterTypeList.toArray(types);
            parameterTypeList.clear();
            parameterTypeList = null;
        } else {
            types = parameterTypes;
        }
        Method method = instance.getClass().getMethod(methodName, types);

        // arguments
        Object[] objects;
        Object firstData = null;
        if (arguments.length != 0) {
            List<Object> argumentList = new ArrayList<Object>();
            boolean first = true;
            for (Object argument : arguments) {
                if (first) {
                    firstData = argument;
                    first = false;
                    continue;
                }
                argumentList.add(argument);
            }
            objects = new Object[arguments.length - 1];
            argumentList.toArray(objects);
            argumentList.clear();
            argumentList = null;
        } else {
            objects = arguments;
        }

        if (firstClass != null && firstClass == SystemData.class)
            ((BaseInService) instance).setSystemData((SystemData) firstData);

        if (method.getReturnType() == int.class) {
            return SafeExecuteUtil.execute(() -> {
                return method.invoke(instance, objects);
            });
        }
        SafeExecuteUtil<Object> executeUtil = new SafeExecuteUtil<Object>();
        return executeUtil.executeT(() -> {
            return method.invoke(instance, objects);
        });
    }
}
