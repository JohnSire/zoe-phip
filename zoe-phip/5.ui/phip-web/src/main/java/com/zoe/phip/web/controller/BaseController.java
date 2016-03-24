package com.zoe.phip.web.controller;

import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.web.context.DataContext;
import org.springframework.stereotype.Controller;

/**
 * Created by zengjiyang on 2016/3/24.
 */
public abstract class BaseController<T> {
    protected int getPageSize(){
        return Integer.parseInt(DataContext.getRequest().getParameter("pagesize"));
    }

    protected int getPageNum(){
        return Integer.parseInt(DataContext.getRequest().getParameter("page"));
    }

    protected QueryPage getQueryPage(){
        return new QueryPage(getPageNum(),getPageSize());
    }
}
