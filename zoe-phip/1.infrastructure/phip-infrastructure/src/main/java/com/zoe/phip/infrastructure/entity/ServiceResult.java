package com.zoe.phip.infrastructure.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linqinghuang on 2016/1/26.
 * 单个实体对象
 */

public class ServiceResult implements Serializable {
    /**
     * 操作是否成功
     */
    @JSONField(name = "IsSuccess")
    private boolean isSuccess;

    /**
     * 消息列表
     */
    @JSONField(name = "Message")
    private List<Message> messages;

    /**
     * 日志消息
     */
    @JSONField(name = "LogData")
    private List<LogMessage> logData;

    public ServiceResult() {
        messages = new ArrayList<Message>();
        logData = new ArrayList<LogMessage>();
    }

    /**
     * 获取操作成功（失败）状态
     *
     * @return
     */
    public boolean getIsSuccess() {
        return isSuccess;
    }

    /**
     * 设置操作成功（失败）状态
     *
     * @param isSuccess
     */
    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    /**
     * 获取消息
     *
     * @return
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * 设置消息
     *
     * @param messages
     */
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<LogMessage> getLogData() {
        return logData;
    }

    public void setLogData(List<LogMessage> logMessages) {
        this.logData = logMessages;
    }


    public void addMessage(String messageId, String content) {
        Message message = new Message();
        message.setId(messageId);
        message.setContent(content);
        messages.add(message);
    }

    public void addLogData(String content) {
        LogMessage logMessage = new LogMessage();
        logMessage.setContent(content);
        logData.add(logMessage);
    }
}
