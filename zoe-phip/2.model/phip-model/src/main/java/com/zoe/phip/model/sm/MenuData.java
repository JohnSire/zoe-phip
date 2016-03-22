/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.model.sm;

import com.zoe.phip.model.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author
 * @version 1.0
 * @date 2016-03-21
 */
@Table(name = "SYS_MENU_DATA")
public class MenuData extends BaseEntity {


    /**
     * 名称
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 编码
     */
    @Column(name = "CODE")
    private int code;

    /**
     * 地址
     */
    @Column(name = "ADDRESS")
    private String address;

    /**
     * 上级菜单
     */
    @Column(name = "FK_PARENT_MENU_ID")
    private String fkParentMenuId;


    /**
     * 状态
     */
    @Column(name = "STATE")
    private int state;


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFkParentMenuId() {
        return this.fkParentMenuId;
    }

    public void setFkParentMenuId(String fkParentMenuId) {
        this.fkParentMenuId = fkParentMenuId;
    }


    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

}
