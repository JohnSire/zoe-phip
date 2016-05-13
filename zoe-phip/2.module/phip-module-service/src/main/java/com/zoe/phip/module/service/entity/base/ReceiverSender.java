package com.zoe.phip.module.service.entity.base;

/**
 * Created by zhangwenbin on 2016/4/18.
 */
public class ReceiverSender {

    private String receiverId;

    private String receiverExtension;

    private String senderId;

    private String senderExtension;


    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverExtension() {
        return receiverExtension;
    }

    public void setReceiverExtension(String receiverExtension) {
        this.receiverExtension = receiverExtension;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderExtension() {
        return senderExtension;
    }

    public void setSenderExtension(String senderExtension) {
        this.senderExtension = senderExtension;
    }


}
