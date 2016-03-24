package com.zoe.phip.model.sm;

import com.zoe.phip.model.base.BaseEntity;

/**
 * Created by Administrator on 2016/3/23.
 */
public class MenuTreeNode extends BaseEntity{

    private String name;

    private int code;

    private int state;

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
