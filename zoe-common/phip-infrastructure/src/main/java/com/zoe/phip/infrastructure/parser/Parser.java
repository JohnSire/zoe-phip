package com.zoe.phip.infrastructure.parser;

import java.util.Map;

/**
 * Created by zhangwenbin on 2016/4/15.
 */
public interface Parser {

    void register(String name, Class<?> helperClass);

    void initialize();

    /**
     * 转换字符串模板
     *
     * @param template
     * @param variableMap
     * @return
     * @throws Exception
     */
    String parse(String template, Map<String, Object> variableMap);

    String parseByResource(String resourceName, Map<String, Object> variableMap);

}
