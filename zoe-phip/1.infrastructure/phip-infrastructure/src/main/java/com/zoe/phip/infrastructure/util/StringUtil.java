package com.zoe.phip.infrastructure.util;

import com.alibaba.fastjson.JSON;
import javafx.scene.chart.Chart;

import java.io.*;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

/**
 * Created by 张文彬 on 2016/2/22.
 */
public final class StringUtil {


    public static boolean isNullOrWhiteSpace(String str) {
        return str == null || str.isEmpty() || str.trim().isEmpty();
    }

    public static String concat(String... args) {
        StringBuilder builder = new StringBuilder(256);
        for (int i = 0; i < args.length; i++) {
            builder.append(args[i]);
        }
        return builder.toString();
    }

    public static String join(String joiner, Object... keys) {
        return joinWithPrefix(null, joiner, keys);
    }

    public static String join(String joiner, List<Object> keys) {
        return joinWithPrefix(null, joiner, keys.toArray());
    }

    public static String joinWithPrefix(String prefix, String joiner, Object... keys) {
        StringBuilder builder = new StringBuilder(256);
        if (prefix != null && !prefix.isEmpty()) {
            builder.append(prefix);
            if (keys.length > 0) {
                builder.append(joiner);
            }
        }
        for (int index = 0; index < keys.length; index++) {

            if (keys[index] instanceof List) {
                List list = (List) keys[index];
                int j = 0;
                for (Object item : list) {
                    builder.append(item);
                    if (j != list.size() - 1) {
                        builder.append(joiner);
                    }
                    j++;
                }
            } else {
                builder.append(keys[index]);
                if (index != keys.length - 1) {
                    builder.append(joiner);
                }
            }
        }
        return builder.toString();
    }

    public static String inputStream2String(InputStream is) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            buffer.append(line).append(System.getProperty("line.separator"));
        }
        return buffer.toString();
    }

    public static String toMd516(String text) {
        return toMD5(text).substring(8, 24).toUpperCase();
    }

    public static String toMD5(String text) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = text.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static <T> String toJsonString(T data) {
        return JSON.toJSONString(data);
    }

    public static <R> R parseJsonObject(String jsonString, Class<R> clazz) {
        return JSON.parseObject(jsonString, clazz);
    }

    public static <R> R parseJsonObject(byte[] jsonBuffer, Class<R> clazz) {
        return JSON.parseObject(jsonBuffer, clazz);
    }

    public static <R> List<R> parseJsonArray(String jsonString, Class<R> clazz) {
        return JSON.parseArray(jsonString, clazz);
    }

    public static String fromBase64String(String text) {
        if (isNullOrWhiteSpace(text))
            return text;
        return new String(Base64.getDecoder().decode(text));
    }

    /**
     * 把文件转换成对象
     *
     * @param fileName 文件名
     * @param clazz    对象
     * @param <R>
     * @return
     */
    public static <R> R parseFileJsonObject(String fileName, Class<R> clazz) {
        R result = null;

        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            inputStream = new FileInputStream(fileName);
            byteArrayOutputStream = new ByteArrayOutputStream(1000);
            byte[] b = new byte[8 * 1024];
            int n;
            while ((n = inputStream.read(b)) != -1) {
                byteArrayOutputStream.write(b, 0, n);
            }
            inputStream.close();
            byteArrayOutputStream.close();
            return JSON.parseObject(byteArrayOutputStream.toByteArray(), clazz);
        } catch (Exception ex) {

        }
        return result;
    }

    public static void parseObjectFile(Object object, String fileName) {

        try {
            File file = new File(fileName);
            if (file.exists())
                file.delete();
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            String content = toJsonString(object);
            byte[] bytes=content.getBytes(Charset.defaultCharset());
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static String exceptionToString(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String result = sw.toString();
        pw.close();
        try {
            sw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
