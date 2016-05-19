package com.zoe.phip.web.filter;

import com.zoe.phip.web.context.DataContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zengjiyang on 2016/3/22.
 */
public class DataFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(DataFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 初始化 DataContext
        DataContext.init(request, response);
        //将控制器传向下一个filter
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        DataContext.destroy();
        logger.info("filter destroy");
    }
}
