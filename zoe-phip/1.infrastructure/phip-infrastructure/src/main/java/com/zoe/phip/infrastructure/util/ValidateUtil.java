package com.zoe.phip.infrastructure.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * Created by zengjiyang on 2016/3/11.
 */
public class ValidateUtil {

    public static boolean isEmpty(Object object) {
        if (object == null)
            return true;

        if (object instanceof String)
            return ((String) object).trim().length() == 0;

        if (object.getClass().isArray())
            return Array.getLength(object) == 0;

        if (object instanceof Collection)
            return ((Collection<?>) object).isEmpty();

        if (object instanceof Map)
            return ((Map<?, ?>) object).isEmpty();

        return false;
    }

    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }
}
