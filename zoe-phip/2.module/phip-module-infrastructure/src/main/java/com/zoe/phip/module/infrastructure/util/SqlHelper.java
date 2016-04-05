package com.zoe.phip.module.infrastructure.util;

import com.github.pagehelper.PageHelper;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.SortOrder;

import java.text.MessageFormat;

/**
 * Created by zengjiyang on 2016/3/25.
 */
public final class SqlHelper {
    public static void startPage(QueryPage queryPage) {
        if (queryPage.getOrderBy() != null) {

            String order = queryPage.getOrderBy() + " " + (queryPage.getSortOrder() == null ? SortOrder.ASC : queryPage.getSortOrder());
            PageHelper.startPage(queryPage.getPageNum(), queryPage.getPageSize(), order);
        } else {
            //默认按照时间倒序
            PageHelper.startPage(queryPage.getPageNum(), queryPage.getPageSize(), " CREATE_AT DESC ");
        }
    }

    public static String getLikeStr(String input) {
        return MessageFormat.format("%{0}%", input);
    }

    public static String getStartWithStr(String input) {
        return MessageFormat.format("{0}%", input);
    }

    public static String getEndWithStr(String input) {
        return MessageFormat.format("%{0}", input);
    }
}
