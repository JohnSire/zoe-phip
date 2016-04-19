package com.zoe.phip.register.util;

import com.zoe.phip.infrastructure.parser.Parser;
import com.zoe.phip.infrastructure.util.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by zhangwenbin on 2016/4/19.
 */
public final class RegisterUtil {

    @Autowired
    private static Parser parser;

    public static String registerMessage(String template, Object o) {
        Map map = MapUtil.createMap(m -> m.put("Model", o));
        String result = parser.parseByResource(template, map);
        map.clear();
        map = null;
        return result;
    }
}
