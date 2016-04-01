package com.zoe.phip.service.impl.support.dubbo;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.proxy.AbstractProxyInvoker;
import com.zoe.phip.infrastructure.entity.ErrorCode;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.SystemData;
import com.zoe.phip.infrastructure.function.Function;
import com.zoe.phip.infrastructure.security.SystemCredential;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.service.impl.in.IBaseInService;
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

    private final static Object[] makeArgument(final Object[] arguments, final boolean isFirstSystemDataClass) {
        Object[] objects;
        if (arguments.length != 0) {
            List<Object> argumentList = new ArrayList<Object>();
            boolean first = true;
            for (Object argument : arguments) {
                if (first && isFirstSystemDataClass) {
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

    // 解出真实异常对象
    private static <R> R execute(Function<R> invoker) throws Exception {
        try {
            return invoker.apply();
        } catch (Exception ex) {
            throw (Exception) ex.getCause();
        }
    }

    public Class getInterfaceClass() {
        return interfaceClass;
    }

    @Override
    protected Object doInvoke(T proxy, String methodName, Class<?>[] parameterTypes, Object[] arguments) throws Throwable {

        Object instance = proxy;

        // arguments
        final boolean isFirstSystemDataClass = parameterTypes.length != 0 && parameterTypes[0] == SystemData.class;
        Object firstData = isFirstSystemDataClass ? arguments[0] : null;
        final Object[] objects = makeArgument(arguments, isFirstSystemDataClass);

        if (!methodName.equals("login")) {
            if (isFirstSystemDataClass) {
                SystemData token = (SystemData) firstData;
                ((IBaseInService) instance).setSystemData(token);
                boolean isAuth = SystemCredential.checkCredential(token.getUserId(), token.getUserName(), token.getCredential());
                if (!isAuth) {
                    ServiceResult result=new ServiceResult();
                    result.addMessage(ErrorCode.SESSION_EXPIRED,"Session过期,请重新登录!");
                    return result;
                }
            } else {
                ServiceResult result=new ServiceResult();
                result.addMessage(ErrorCode.DEFAULT,"方法的第一个参数必须为SystemData类型");
                return result;
            }
        }

        final Class<?>[] types = makeClass(parameterTypes);
        Method method = instance.getClass().getMethod(methodName, types);

        final boolean withResult = method.getAnnotation(WithResult.class) != null;

        if (method.getReturnType() == Boolean.class && !withResult) {
            return SafeExecuteUtil.execute(() ->
                    execute(() -> (Boolean) method.invoke(instance, objects))
            );
        }
        if (method.getReturnType() == int.class && !withResult) {
            return SafeExecuteUtil.execute(() ->
                    execute(() -> ((int) method.invoke(instance, objects) >= 0))
            );
        }
        return SafeExecuteUtil.execute0(() ->
                execute(() -> method.invoke(instance, objects))
        );
    }
}
