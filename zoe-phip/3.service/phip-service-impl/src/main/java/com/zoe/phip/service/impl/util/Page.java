package com.zoe.phip.service.impl.util;

import com.github.pagehelper.PageHelper;
import com.zoe.phip.infrastructure.entity.QueryPage;

/**
 * Created by zengjiyang on 2016/3/25.
 */
public final class Page {
    public static void startPage(QueryPage queryPage){
        if (queryPage.getOrderBy() != null) {
            PageHelper.startPage(queryPage.getPageNum(), queryPage.getPageSize(), queryPage.getOrderBy());
        } else {
            PageHelper.startPage(queryPage.getPageNum(), queryPage.getPageSize());
        }
    }
}
