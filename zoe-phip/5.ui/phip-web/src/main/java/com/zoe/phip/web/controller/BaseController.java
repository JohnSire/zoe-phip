package com.zoe.phip.web.controller;

import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.web.context.DataContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 * Created by zengjiyang on 2016/3/24.
 * updated by hyf on 2016/4/1
 */
public abstract class BaseController {

    protected int getPageSize() {
        String pageSize = DataContext.getRequest().getParameter("pagesize");
        return StringUtil.isNullOrWhiteSpace(pageSize) ? 30 : Integer.valueOf(pageSize);
    }

    protected int getPageNum() {
        String pageNum = DataContext.getRequest().getParameter("page");
        return StringUtil.isNullOrWhiteSpace(pageNum) ? 1 : Integer.valueOf(pageNum);
    }

    protected QueryPage getQueryPage() {
        return new QueryPage(getPageNum(), getPageSize());
    }

    protected String getKey() {
        return DataContext.getRequest().getParameter("keyWord");
    }


    /***********************************/


    public HttpServletRequest getRequest() {
        return DataContext.getRequest();
    }

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


    public HttpSession getSession() {
        return getRequest().getSession();
    }


    public HttpSession getSession(boolean create) {
        return getRequest().getSession(create);
    }


    public <T> T getSessionAttr(String key) {
        HttpSession session = getRequest().getSession(false);
        return session != null ? (T) session.getAttribute(key) : null;
    }


    public BaseController setSessionAttr(String key, Object value) {
        getRequest().getSession().setAttribute(key, value);
        return this;
    }


    public BaseController removeSessionAttr(String key) {
        HttpSession session = getRequest().getSession(false);
        if (session != null)
            session.removeAttribute(key);
        return this;
    }


    public String getCookie(String name, String defaultValue) {
        Cookie cookie = getCookieObject(name);
        return cookie != null ? cookie.getValue() : defaultValue;
    }


    public String getCookie(String name) {
        return getCookie(name, null);
    }


    public Integer getCookieToInt(String name) {
        String result = getCookie(name);
        return result != null ? Integer.parseInt(result) : null;
    }


    public Integer getCookieToInt(String name, Integer defaultValue) {
        String result = getCookie(name);
        return result != null ? Integer.parseInt(result) : defaultValue;
    }


    public Long getCookieToLong(String name) {
        String result = getCookie(name);
        return result != null ? Long.parseLong(result) : null;
    }


    public Long getCookieToLong(String name, Long defaultValue) {
        String result = getCookie(name);
        return result != null ? Long.parseLong(result) : defaultValue;
    }


    public Cookie getCookieObject(String name) {
        Cookie[] cookies = getRequest().getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies)
                if (cookie.getName().equals(name))
                    return cookie;
        return null;
    }


    public Cookie[] getCookieObjects() {
        Cookie[] result = getRequest().getCookies();
        return result != null ? result : new Cookie[0];
    }


    public BaseController setCookie(Cookie cookie) {
        getResponse().addCookie(cookie);
        return this;
    }


    public BaseController setCookie(String name, String value, int maxAgeInSeconds, String path) {
        setCookie(name, value, maxAgeInSeconds, path, null);
        return this;
    }


    public BaseController setCookie(String name, String value, int maxAgeInSeconds, String path, String domain) {
        Cookie cookie = new Cookie(name, value);
        if (domain != null)
            cookie.setDomain(domain);
        cookie.setMaxAge(maxAgeInSeconds);
        cookie.setPath(path);
        getResponse().addCookie(cookie);
        return this;
    }


    public BaseController setCookie(String name, String value, int maxAgeInSeconds) {
        setCookie(name, value, maxAgeInSeconds, "/", null);
        return this;
    }


    public BaseController removeCookie(String name) {
        setCookie(name, null, 0, "/", null);
        return this;
    }

    public BaseController removeCookie(String name, String path) {
        setCookie(name, null, 0, path, null);
        return this;
    }


    public BaseController removeCookie(String name, String path, String domain) {
        setCookie(name, null, 0, path, domain);
        return this;
    }

}
