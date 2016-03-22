package com.zoe.phip.infrastructure.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
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
}
