/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.model.demo;

import com.zoe.phip.model.base.BaseEntity;

import javax.persistence.*;

/**
 * @author
 * @date 2016-03-16
 * @version 1.0
 */
public class Person extends BaseEntity {
    /**  */
    @Id
    @Column(name = "ID")
    private String id;

    /**  */
    @Column(name = "PERSONNAME")
    private String personname;

    /**  */
    @Column(name = "PERSONADDRESS")
    private String personaddress;

    /**  */
    @Column(name = "PERSONTEL")
    private String persontel;


    public String getId(){
        return  this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getPersonname(){
        return  this.personname;
    }

    public void setPersonname(String personname){
        this.personname = personname;
    }

    public String getPersonaddress(){
        return  this.personaddress;
    }

    public void setPersonaddress(String personaddress){
        this.personaddress = personaddress;
    }

    public String getPersontel(){
        return  this.persontel;
    }

    public void setPersontel(String persontel){
        this.persontel = persontel;
    }

}
