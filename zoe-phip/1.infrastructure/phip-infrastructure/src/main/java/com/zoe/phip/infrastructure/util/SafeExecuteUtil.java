package com.zoe.phip.infrastructure.util;

import com.zoe.phip.infrastructure.function.Function;
import com.zoe.phip.infrastructure.entity.Message;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangwenbin on 2016/2/29.
 */
public final class SafeExecuteUtil<T> {

    private static final Logger logger = LoggerFactory.getLogger(SafeExecuteUtil.class);

    public static ServiceResult execute(Function<Object> invoker) {
        ServiceResult executeResult = new ServiceResult();
        List<Message> messageList=new ArrayList<Message>();
        try {
            Object result = invoker.apply();
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

    public ServiceResultT<T> executeT(Function<Object> invoker){
        ServiceResultT<T> executeResult=new ServiceResultT<T>();
        List<Message> messageList=new ArrayList<Message>();
        try {
            T result = (T)invoker.apply();
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

