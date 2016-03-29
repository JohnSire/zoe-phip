package com.zoe.phip.infrastructure.entity;

import com.zoe.phip.infrastructure.security.MenuCode;

/**
 * Created by zengjiyang on 2016/3/29.
 */

public class Menu {
    private String uri;
    private MenuCode code;
    private int order;
    private String name;
    private Menu parent;

    public Menu(String name, String uri, MenuCode code, int order,Menu parent) {
        this.parent = parent;
        this.uri = uri;
        this.code = code;
        this.order = order;
        this.name=name;
    }

    public String getUri() {
        return uri;
    }

    public MenuCode getCode() {
        return code;
    }

    public int getOrder() {
        return order;
    }


    public String getName() {
        return name;
    }

    public Menu getParent() {
        return parent;
    }
}
