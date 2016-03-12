package com.zoe.phip.model.demo;

import com.zoe.phip.model.base.BaseEntity;

import javax.persistence.Table;

@Table(name = "DEMO_DEPT")
public class Dept extends BaseEntity {

    private String name;

    private Integer code;

    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}