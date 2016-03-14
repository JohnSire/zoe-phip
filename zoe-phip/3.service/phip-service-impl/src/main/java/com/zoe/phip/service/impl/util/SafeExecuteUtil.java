package com.zoe.phip.service.impl.util;

import com.zoe.phip.model.base.Message;
import com.zoe.phip.model.base.ServiceResult;
import com.zoe.phip.model.base.ServiceResultT;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by zhangwenbin on 2016/2/29.
 */
public final class SafeExecuteUtil<T> {
    public static ServiceResult execute(Logger logger, Supplier<Object> invoker) {
        ServiceResult executeResult = new ServiceResult();
        List<Message> messageList=new ArrayList<Message>();
        try {
            Object result = invoker.get();
            executeResult.setIsSuccess(result != null);
        } catch (Exception e) {
            //错误消息
            Message message=new Message();
            message.setId("1001");
            message.setContent(e.getMessage());
            messageList.add(message);
            executeResult.setIsSuccess(false);
            e.printStackTrace();
            logger.error("方法执行报错:",e);
        }
        executeResult.setMessages(messageList);
        return executeResult;
    }

    public ServiceResultT<T> executeT(Logger logger, Supplier<Object> invoker){
        ServiceResultT<T> executeResult=new ServiceResultT<T>();
        List<Message> messageList=new ArrayList<Message>();
        try {
            T result = (T)invoker.get();
            executeResult.setResult(result);
            executeResult.setIsSuccess(result != null);
        }catch (Exception e){
            executeResult.setIsSuccess(false);
            Message message=new Message();
            //todo 如何定义错误ID
            message.setId("1001");
            message.setContent(e.getMessage());
            messageList.add(message);
            executeResult.setIsSuccess(false);
            e.printStackTrace();
            logger.error("方法执行报错:",e);
        }
        executeResult.setMessages(messageList);
        return executeResult;
    }

}

