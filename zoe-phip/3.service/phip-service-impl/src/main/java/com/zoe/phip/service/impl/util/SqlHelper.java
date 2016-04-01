package com.zoe.phip.service.impl.util;

import com.github.pagehelper.PageHelper;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.util.StringUtil;

import java.text.MessageFormat;
import java.util.Map;

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

    /**
     * 设置排序
     * @param map
     * @param queryPage
     */
    public static void setOrder(Map<String, Object> map, QueryPage queryPage){
        if(!StringUtil.isNullOrWhiteSpace(queryPage.getOrderBy())){
            map.put("order",queryPage.getOrderBy());
            map.put("sortOrder","ASC");
        }
    }

}
