package com.zoe.phip.web.controller;

import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.SortOrder;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.web.context.DataContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 * Created by zengjiyang on 2016/3/24.
 */
public abstract class BaseController {


    public HttpServletRequest getRequest() {
        return DataContext.getRequest();
    }

    //// TODO: 2016/3/31   hyf 后面再补？ 
    public HttpServletResponse getResponse() {
        return DataContext.getResponse();
    }


    public BaseController setAttr(String name, Object value) {
        getRequest().setAttribute(name, value);
        return this;
    }

    public BaseController removeAttr(String name) {
        getRequest().removeAttribute(name);
        return this;
    }

    public BaseController setAttrs(Map<String, Object> attrMap) {
        for (Map.Entry<String, Object> entry : attrMap.entrySet())
            getRequest().setAttribute(entry.getKey(), entry.getValue());
        return this;
    }


    public String getPara(String name) {
        return getRequest().getParameter(name);
    }

    public String getPara(String name, String defaultValue) {
        String result = getRequest().getParameter(name);
        return result != null && !"".equals(result) ? result : defaultValue;
    }

    public <T> T getAttr(String name) {
        return (T) getRequest().getAttribute(name);
    }

    public String getAttrForStr(String name) {
        return (String) getRequest().getAttribute(name);
    }

    public Integer getAttrForInt(String name) {
        return (Integer) getRequest().getAttribute(name);
    }


    public Integer getParaToInt(String name) {
        return toInt(getRequest().getParameter(name), null);
    }


    public Integer getParaToInt(String name, Integer defaultValue) {
        return toInt(getRequest().getParameter(name), defaultValue);
    }

    private Integer toInt(String value, Integer defaultValue) {
        if (value == null || "".equals(value.trim()))
            return defaultValue;
        if (value.startsWith("N") || value.startsWith("n"))
            return -Integer.parseInt(value.substring(1));
        return Integer.parseInt(value);
    }

    private Long toLong(String value, Long defaultValue) {
        if (value == null || "".equals(value.trim()))
            return defaultValue;
        if (value.startsWith("N") || value.startsWith("n"))
            return -Long.parseLong(value.substring(1));
        return Long.parseLong(value);
    }


    public Long getParaToLong(String name) {
        return toLong(getRequest().getParameter(name), null);
    }


    public Long getParaToLong(String name, Long defaultValue) {
        return toLong(getRequest().getParameter(name), defaultValue);
    }

    private Boolean toBoolean(String value, Boolean defaultValue) {
        if (value == null || "".equals(value.trim()))
            return defaultValue;
        value = value.trim().toLowerCase();
        if ("1".equals(value) || "true".equals(value))
            return Boolean.TRUE;
        else if ("0".equals(value) || "false".equals(value))
            return Boolean.FALSE;
        throw new RuntimeException("Can not parse the parameter \"" + value + "\" to boolean value.");
    }


    public Boolean getParaToBoolean(String name) {
        return toBoolean(getRequest().getParameter(name), null);
    }


    public Boolean getParaToBoolean(String name, Boolean defaultValue) {
        return toBoolean(getRequest().getParameter(name), defaultValue);
    }


    private Date toDate(String value, Date defaultValue) {
        if (value == null || "".equals(value.trim()))
            return defaultValue;
        try {
            return new java.text.SimpleDateFormat("yyyy-MM-dd").parse(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    public Date getParaToDate(String name) {
        return toDate(getRequest().getParameter(name), null);
    }


    public Date getParaToDate(String name, Date defaultValue) {
        return toDate(getRequest().getParameter(name), defaultValue);
    }


    /***
     *
     *
     */

    protected int getPageSize() {
        String pageSize = DataContext.getRequest().getParameter("pagesize");
        return StringUtil.isNullOrWhiteSpace(pageSize) ? 30 : Integer.valueOf(pageSize);
    }

    protected int getPageNum() {
        String pageNum = DataContext.getRequest().getParameter("page");
        return StringUtil.isNullOrWhiteSpace(pageNum) ? 1 : Integer.valueOf(pageNum);
    }

    protected String getSort(){
        return DataContext.getRequest().getParameter("sortname");
    }

    protected SortOrder getSortOrder(){
        String sortOder=DataContext.getRequest().getParameter("sortname");
        return SortOrder.forValue(sortOder);
    }

    protected QueryPage getQueryPage() {
        return new QueryPage(getPageNum(), getPageSize(),getSort(),getSortOrder());
    }

    protected String getKey() {
        return DataContext.getRequest().getParameter("keyWord");
    }
}
