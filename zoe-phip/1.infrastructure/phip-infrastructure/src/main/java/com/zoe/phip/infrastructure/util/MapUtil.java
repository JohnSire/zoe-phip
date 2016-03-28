package com.zoe.phip.infrastructure.util;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;

/**
 * Created by zhangwenbin on 2016/3/28.
 */
public final class MapUtil {

    public static <K, V> Map<K, V> createMap(Consumer<Map<K, V>> consumer) {
        return createMap(null, consumer);
    }

    public static <K, V> Map<K, V> createMap(Class<Map<K, V>> clazz, Consumer<Map<K, V>> consumer) {
        Map<K, V> map = null;
        try {
            if (clazz != null)
                map = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (map == null)
            map = new TreeMap<>();
        consumer.accept(map);
        return map;
    }
}