package com.zoe.phip.service.impl.util;

import com.github.pagehelper.PageHelper;
import com.zoe.phip.infrastructure.entity.QueryPage;

import java.text.MessageFormat;

/**
 * Created by zengjiyang on 2016/3/25.
 */
public final class SqlHelper {
    public static void startPage(QueryPage queryPage){
        if (queryPage.getOrderBy() != null) {
            PageHelper.startPage(queryPage.getPageNum(), queryPage.getPageSize(), queryPage.getOrderBy());
        } else {
            PageHelper.startPage(queryPage.getPageNum(), queryPage.getPageSize());
        }
    }

    public static String getLikeStr(String input){
        return MessageFormat.format("%{0}%",input);
    }

    public static String getStartWithStr(String input){
        return MessageFormat.format("{0}%",input);
    }

    public static String getEndWithStr(String input){
        return MessageFormat.format("%{0}",input);
    }

}
