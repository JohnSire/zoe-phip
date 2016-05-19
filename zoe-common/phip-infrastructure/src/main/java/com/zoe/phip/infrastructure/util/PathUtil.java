package com.zoe.phip.infrastructure.util;

import java.io.File;

/**
 * Created by qiuyungen on 2016/2/25.
 */
public final class PathUtil {
    public static String getAppStartupPath() {
        return System.getProperty("user.dir");
    }

    public static String getAppConfigDir() {
        return StringUtil.join(File.separator, getAppStartupPath(), "config");
    }

    public static String getAppConfigDir(Object... args) {
        return StringUtil.joinWithPrefix(getAppConfigDir(), File.separator, args);
    }

    public static String getAppConfigFileName(String name) {
        return StringUtil.join(File.separator, getAppConfigDir(), name);
    }
}
