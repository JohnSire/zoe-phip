package com.zoe.phip.web.controller;

import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.web.context.DataContext;
import org.springframework.stereotype.Controller;

/**
 * Created by zengjiyang on 2016/3/24.
 */
public abstract class BaseController {
    protected int getPageSize() {
        String pageSize = DataContext.getRequest().getParameter("pagesize");
        return StringUtil.isNullOrWhiteSpace(pageSize) ? Integer.MAX_VALUE : Integer.valueOf(pageSize);
    }

    protected int getPageNum() {
        String pageNum = DataContext.getRequest().getParameter("page");
        return StringUtil.isNullOrWhiteSpace(pageNum) ? Integer.MAX_VALUE : Integer.valueOf(pageNum);
    }

    protected QueryPage getQueryPage() {
        return new QueryPage(getPageNum(), getPageSize());
    }
}
