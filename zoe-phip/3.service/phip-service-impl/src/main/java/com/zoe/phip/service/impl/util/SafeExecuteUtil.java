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
public final class SafeExecuteUtil {
    public static ServiceResult execute(Logger logger, Supplier<Object> invoker) {
        ServiceResult executeResult = new ServiceResult();
        try {
            Object result = invoker.get();
            executeResult.setIsSuccess(result != null);
//            executeResult.setResult(result);
        } catch (Exception e) {
/*            DbError error = new DbError();
            error.setNumber(ex.getStackTrace()[ex.getStackTrace().length - 1].getLineNumber());
            executeResult.setDbError(error);
            executeResult.setErrorMessage(ex.getMessage());*/
            //错误消息
            List<Message> messageList=new ArrayList<Message>();
            Message message=new Message();
            message.setId("1001");
            message.setContent(e.getMessage());
            messageList.add(message);
            executeResult.setIsSuccess(false);
            executeResult.setMessages(messageList);
            e.printStackTrace();
            logger.error("方法执行报错:",e);
        }
        return executeResult;
    }

}
