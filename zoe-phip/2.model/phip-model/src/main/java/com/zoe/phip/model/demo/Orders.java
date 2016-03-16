/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.model.demo;

import javax.persistence.*;

/**
 * @author
 * @date 2016-03-16
 * @version 1.0
 */
public class Orders {
    /**  */
    @Column(name = "ID")
    private String id;

    /**  */
    @Column(name = "ORDERNUMBER")
    private String ordernumber;

    /**  */
    @Column(name = "ORDERPRICE")
    private java.math.BigDecimal orderprice;

    /**  */
    @Column(name = "PID")
    private String pid;


    public String getId(){
        return  this.id;
    }

    public void setId(String id){
        this.id = id;
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
