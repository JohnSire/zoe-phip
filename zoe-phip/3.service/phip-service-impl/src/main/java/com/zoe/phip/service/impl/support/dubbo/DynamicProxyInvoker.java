package com.zoe.phip.service.impl.support.dubbo;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.proxy.AbstractProxyInvoker;
import com.zoe.phip.infrastructure.entity.SystemData;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.service.impl.in.BaseInService;
import com.zoe.phip.service.impl.support.annotation.WithResult;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiuyungen on 2016/3/26.
 */
public class DynamicProxyInvoker<T> extends AbstractProxyInvoker<T> {
    private final Class interfaceClass;

    public DynamicProxyInvoker(T proxy, Class<T> type, URL url) {
        super(proxy, type, url);
        this.interfaceClass = proxy.getClass().getAnnotation(Service.class).interfaceClass();
    }

    private final static Class<?>[] makeClass(final Class<?>[] parameterTypes) {
        Class<?>[] types;
        if (parameterTypes.length != 0) {
            List<Class<?>> parameterTypeList = new ArrayList<Class<?>>();
            boolean first = true;
            int offset = 0;
            for (Class<?> parameterType : parameterTypes) {
                if (first && parameterType == SystemData.class) {
                    offset = 1;
                    first = false;
                    continue;
                }
                parameterTypeList.add(parameterType);
            }
            types = new Class<?>[parameterTypes.length - offset];
            parameterTypeList.toArray(types);
            parameterTypeList.clear();
            parameterTypeList = null;
        } else {
            types = parameterTypes;
        }
        return types;
    }

    private final static Object[] makeArgument(final Object[] arguments, final boolean isFirstSystemDataClass, Object firstData) {
        Object[] objects;
        if (arguments.length != 0) {
            List<Object> argumentList = new ArrayList<Object>();
            boolean first = true;
            for (Object argument : arguments) {
                if (first && isFirstSystemDataClass) {
                    firstData = argument;
                    first = false;
                    continue;
                }
                argumentList.add(argument);
            }
            objects = new Object[arguments.length - (isFirstSystemDataClass ? 1 : 0)];
            argumentList.toArray(objects);
            argumentList.clear();
            argumentList = null;
        } else {
            objects = arguments;
        }
        return objects;
    }

    public Class getInterfaceClass() {
        return interfaceClass;
    }

    @Override
    protected Object doInvoke(T proxy, String methodName, Class<?>[] parameterTypes, Object[] arguments) throws Throwable {

        Object instance = proxy;

        final Class<?>[] types = makeClass(parameterTypes);
        Method method = instance.getClass().getMethod(methodName, types);

        final boolean withResult = method.getAnnotation(WithResult.class) != null;

        // arguments
        final boolean isFirstSystemDataClass = parameterTypes.length != 0 && parameterTypes[0] == SystemData.class;
        Object firstData = null;
        final Object[] objects = makeArgument(arguments, isFirstSystemDataClass, firstData);
        if (isFirstSystemDataClass)
            ((BaseInService) instance).setSystemData((SystemData) firstData);

        if (method.getReturnType() == int.class && !withResult) {
            return SafeExecuteUtil.execute(() -> method.invoke(instance, objects));
        }
        return SafeExecuteUtil.execute0(() -> method.invoke(instance, objects));
    }
}
