package com.zoe.phip.infrastructure.util;

import java.io.*;
import java.util.Properties;

/**
 * Created by qiuyungen on 2016/2/23.
 */
public final class PropertyUtil {
    public static Properties getProperties(String propertyName) {
        String resourceName = propertyName + ".properties";
        return loadProperties(resourceName);
    }

    public static Properties getPropertiesWithString(String content) {
        Properties properties = new Properties();
        try {
            properties.load(new ByteArrayInputStream(content.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    private static Properties loadProperties(String resource) {
        Properties properties = new Properties();

        File file = new File(PathUtil.getAppConfigFileName(resource));
        InputStream stream = null;
        try {
            if (file.exists())
                stream = new FileInputStream(file);
            else {
                stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
            }
            if (stream != null)
                // 加载配置文件
                properties.load(stream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (stream != null)
                    stream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return properties;
    }
}