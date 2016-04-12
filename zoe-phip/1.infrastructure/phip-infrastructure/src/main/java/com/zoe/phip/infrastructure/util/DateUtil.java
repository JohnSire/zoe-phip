package com.zoe.phip.infrastructure.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangwenbin on 2016/4/12.
 */
public final class DateUtil {

    public static Date stringToDateTime(String strDateTime) throws Exception {
        SimpleDateFormat sdf = null;
        if (strDateTime.length() == 8) {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            String reg = "(\\d{4})(\\d{2})(\\d{2})";
            return sdf.parse(strDateTime.replaceAll(reg, "$1-$2-$3"));
        } else if (strDateTime.length() == 12) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String reg = "(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{2})";
            return sdf.parse(strDateTime.replaceAll(reg, "$1-$2-$3 $4:$5"));
        } else if (strDateTime.length() == 14) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String reg = "(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})";
            return sdf.parse(strDateTime.replaceAll(reg, "$1-$2-$3 $4:$5:$6"));
        }
        if (strDateTime.contains("T")) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssz");
            String reg = "(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})";
            return sdf.parse(strDateTime.replaceAll(reg, "$1-$2-$3 $4:$5:$6"));
        }

        return new Date();
    }
}
