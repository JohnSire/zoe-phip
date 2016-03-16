/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.model.demo;

import com.zoe.phip.model.base.BaseEntity;

import javax.persistence.*;

/**
 * @author
 * @date 2016-03-15
 * @version 1.0
 */
public class Orders {
    /**  */
    @Column(name = "ORDERID")
    private String orderid;

    /**  */
    @Column(name = "ORDERNUMBER")
    private String ordernumber;

    /**  */
    @Column(name = "ORDERPRICE")
    private java.math.BigDecimal orderprice;

    /**  */
    @Column(name = "PID")
    private String pid;


    public String getOrderid(){
        return  this.orderid;
    }

    public void setOrderid(String orderid){
        this.orderid = orderid;
    }

    public String getOrdernumber(){
        return  this.ordernumber;
    }

    public void setOrdernumber(String ordernumber){
        this.ordernumber = ordernumber;
    }

    public java.math.BigDecimal getOrderprice(){
        return  this.orderprice;
    }

    public void setOrderprice(java.math.BigDecimal orderprice){
        this.orderprice = orderprice;
    }

    public String getPid(){
        return  this.pid;
    }

    public void setPid(String pid){
        this.pid = pid;
    }

}
