package com.zoe.phip.register.model.base;

import com.zoe.phip.module.service.entity.MasterEntity;

import javax.persistence.Transient;

/**
 * 要输出XML的都可以继承这个类
 * Created by zengjiyang on 2016/4/18.
 */
public class RegisterEntity extends MasterEntity {

    @Transient
    private Acknowledgement acknowledgement;

    @Transient
    private ReceiverSender receiverSender;

    public Acknowledgement getAcknowledgement() {
        return acknowledgement;
    }

    public void setAcknowledgement(Acknowledgement acknowledgement) {
        this.acknowledgement = acknowledgement;
    }

    public ReceiverSender getReceiverSender() {
        return receiverSender;
    }

    public void setReceiverSender(ReceiverSender receiverSender) {
        this.receiverSender = receiverSender;
    }
}
