package com.zoe.phip.module.service.entity.base;

/**
 * Created by zhangwenbin on 2016/4/18.
 */

public class Acknowledgement {

    private String id;

    private String extension;
    /**
     * 消息内容
     */
    private String text;

    /**
     * AA 表示成功 AE 表示失败
     */
    private String typeCode;

    /**
     * 响应消息ID
     */
    private String msgId;

    /**
     * 创建时间
     */
    private String createTime;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
