package com.zoe.phip.infrastructure.parser;

import com.zoe.phip.infrastructure.util.StringUtil;
import org.springframework.stereotype.Component;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by zhangwenbin on 2016/4/15.
 */

@Component("BeetlParserImpl")
public class BeetlParserImpl implements Parser {

    private GroupTemplate groupTemplate;

    private TreeMap<String, Class<?>> helperMap = new TreeMap<String, Class<?>>();

    @Override
    public void register(String name, Class<?> helperClass) {
        if (helperMap.containsKey(name)) return;
        helperMap.put(name, helperClass);
    }

    @Override
    public void initialize() {
        Configuration cfg = null;
        try {
            cfg = Configuration.defaultConfiguration();
            StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
            groupTemplate = new GroupTemplate(resourceLoader, cfg);
            groupTemplate.registerFunctionPackage("StringUtil", StringUtil.class);
            //groupTemplate.registerFunctionPackage("MybatisUtil", MybatisUtil.class);
            helperMap.forEach((k, v) -> {
                groupTemplate.registerFunctionPackage(k, v);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String parse(String template, Map<String, Object> variableMap) {
        if (groupTemplate == null) {
            initialize();
        }
        Template t = groupTemplate.getTemplate(template);
        t.binding(variableMap);
        return t.render();
    }

    @Override
    public String parseByResource(String resourceName, Map<String, Object> variableMap) {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName);
        String template = StringUtil.inputStream2String(stream);
        return parse(template, variableMap);
    }
}
