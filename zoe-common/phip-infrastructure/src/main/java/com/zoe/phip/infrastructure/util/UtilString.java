package com.zoe.phip.infrastructure.util;

import com.zoe.phip.infrastructure.constants.BasConstant;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * by hyf
 */

public class UtilString {
    /**
     * Parses a boolean string to return a boolean value
     *
     * @param theString The string to parse
     * @return True or False depending of the contents of the string.
     */
    public static boolean toBoolean(String theString) {
        if (theString == null) {
            return false;
        }

        theString = theString.trim();

        if (theString.equalsIgnoreCase("y") ||
                theString.equalsIgnoreCase("yes") ||
                theString.equalsIgnoreCase("true") ||
                theString.equalsIgnoreCase("1")) {
            return true;
        }

        return false;
    }

    /**
     * 检查字符串是否为空
     */
    public static boolean isEmpty(String s) {
        return ((s == null) || (s.length() == 0));
    }

    public static boolean isEmptyOrNullWildcard(String s) {
        return ((s == null) || (s.length() == 0) || "%@|$".equals(s));
    }

    /**
     * 判断两个字符串是否相同
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean isEqual(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }

    /**
     * 将字符集转换成整型
     *
     * @param str 要转化的字符串
     * @return int 转化后的整数
     */
    public static int toInt(String str) {
        int lpResult = 0;
        try {
            lpResult = Integer.parseInt(str);
        } catch (NumberFormatException notint) {
        }
        return lpResult;
    }

    /**
     * 将字符集转换成整型
     *
     * @param str 要转化的字符串
     * @return int 转化后的整数
     */
    public static long toLong(String str) {
        long lpResult = 0;
        try {
            lpResult = Long.parseLong(str);
        } catch (NumberFormatException notint) {
        }
        return lpResult;
    }

    /**
     * 把boolean型转换为对应字符串
     *
     * @param bool
     * @return
     */
    public static String boolToString(Boolean bool) {
        String result;
        if (bool) {
            result = BasConstant.BOOLEAN_TRUE;
        } else {
            result = BasConstant.BOOLEAN_FALSE;
        }
        return result;
    }

    /**
     * 判断str1字符串是否以searchStr开始
     *
     * @param str       the String to check, may be null
     * @param searchStr the String to find, may be null
     * @return
     * @author huangxinyi
     */
    public static boolean isFirstIndexOf(String str1, String searchStr) {
        return StringUtils.lastIndexOf(str1, searchStr) == 0;
    }

    /**
     * 转换String操作，空串和null都转为null
     *
     * @param string
     * @return
     */
    public static String getString(String string) {
        if (string == null || "".equals(string)) {
            return null;
        } else {
            return string;
        }
    }

    /**
     * 转换String操作，空串和null都转为""
     *
     * @param string
     * @return
     */
    public static String getNotNullString(Object string) {
        if (string == null || "".equals(string)) {
            return "";
        } else {
            return string.toString();
        }
    }

    /**
     * 从目标字符串中查找指定的子字符串
     *
     * @param str       目标字符串
     * @param substr    要查找的字符串
     * @param sepatator 分隔符
     * @return
     */
    public static boolean isExist(String str, String substr, String sepatator) {
        if (str == null || str.trim().equals("")) return false;
        if (substr == null || substr.trim().equals("")) return false;
        String[] strArr = str.split(sepatator);
        int size = strArr.length;
        for (int i = 0; i < size; i++) {
            if (strArr[i].equals(substr)) return true;
        }
        return false;
    }

    /**
     * 从目标字符串中查找指定的子字符串<br>
     * 默认使用","作为分隔符
     *
     * @param str    目标字符串
     * @param substr 要查找的字符串
     * @return
     */
    public static boolean isExist(String str, String substr) {
        if (str == null || str.trim().equals("")) return false;
        if (substr == null || substr.trim().equals("")) return false;
        String[] strArr = str.split(",");
        int size = strArr.length;
        for (int i = 0; i < size; i++) {
            if (strArr[i].equals(substr)) return true;
        }
        return false;
    }

    /**
     * 获取Url最后的文件类型
     *
     * @param url
     * @return
     */
    public static String getSuffixs(String url) {
        if (url.lastIndexOf(".") != -1) {
            String suffixs = url.substring(url.lastIndexOf(".") + 1, url.length());
            return suffixs;
        } else {
            return null;
        }


    }

    public static String quote(String s) {
        int slashEIndex = s.indexOf("\\E");
        if (slashEIndex == -1)
            return "\\Q" + s + "\\E";

        StringBuffer sb = new StringBuffer(s.length() * 2);
        sb.append("\\Q");
        slashEIndex = 0;
        int current = 0;
        while ((slashEIndex = s.indexOf("\\E", current)) != -1) {
            sb.append(s.substring(current, slashEIndex));
            current = slashEIndex + 2;
            sb.append("\\E\\\\E\\Q");
        }
        sb.append(s.substring(current, s.length()));
        sb.append("\\E");
        return sb.toString();
    }

    public static StringBuffer populate(StringBuffer bf, String value, boolean isNotLast) {
        if (value == null) {
            return bf;
        }
        // 把字符串的每个单引号替换成两个单引号（注意：不是双引号），在SQL语句查询中有用
        bf.append("'").append(value.replaceAll("'", "''")).append("'");
        if (isNotLast)
            bf.append(",");
        return bf;
    }

    public static String afterOfDeletion(String str, String include) {
        return deletion(str, include, false);
    }

    public static String deletion(String str, String include, boolean place) {
        if (str == null || str.trim().equals("")) {
            return str;
        }
        if (include == null || include.equals("")) {
            return str;
        }
        if (place) {
            if (str.startsWith(include))
                return str.substring(1);
        } else {
            if (str.endsWith(include))
                return str.substring(0, str.length() - 1);
        }
        return str;
    }

    /**
     * 判断在String[]中是否存在oriString
     *
     * @param oriString
     * @param strings
     * @return
     */
    public static boolean isContain(String oriString, String[] strings) {
        for (String string : strings) {
            if (string.equals(oriString)) {
                return true;
            }
        }
        return false;
    }

    /**
     * TODO(这里用一句话描述这个方法的作用)
     *
     * @param str
     * @return
     */
    public static String deleteSplithSpace(String str) {
        return str.replaceAll("[ ]{2,}", " ");
    }


    public static Set commaDelimitedListToSet(String str) {
        Set set = new TreeSet();
        String[] tokens = commaDelimitedListToStringArray(str);
        for (int i = 0; i < tokens.length; i++) {
            set.add(tokens[i]);
        }
        return set;
    }

    public static String[] commaDelimitedListToStringArray(String str) {
        return delimitedListToStringArray(str, ",");
    }

    public static String[] delimitedListToStringArray(String str, String delimiter) {
        if (str == null) {
            return new String[0];
        }
        if (delimiter == null) {
            return new String[]{str};
        }

        List result = new ArrayList();
        int pos = 0;
        int delPos = 0;
        while ((delPos = str.indexOf(delimiter, pos)) != -1) {
            result.add(str.substring(pos, delPos));
            pos = delPos + delimiter.length();
        }
        if ((str.length() > 0) && (pos <= str.length())) {
            result.add(str.substring(pos));
        }

        return (String[]) result.toArray(new String[result.size()]);
    }

    public static boolean areNotEmpty(String[] values) {
        boolean result = true;
        if ((values == null) || (values.length == 0))
            result = false;
        else {
            for (String value : values) {
                result &= !isEmpty(value);
            }
        }
        return result;
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static String doDecode(String str, String encode) {
        if (str == null || "null".equals(str)) {
            return null;
        } else {
            String encodeString;
            try {
                encodeString = new String(str.getBytes("ISO-8859-1"), encode);
                return encodeString;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取字符串长度，中文算2位
     *
     * @param s
     * @return
     */
    public static int getWordCount(String s) {
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            int ascii = Character.codePointAt(s, i);
            if (ascii >= 0 && ascii <= 255)
                length++;
            else
                length += 2;

        }
        return length;

    }

    /**
     * 根据长度截取字符串
     *
     * @param oriString
     * @param length
     * @param suffix
     * @return
     */
    public static String getStringByLength(String oriString, int length, String suffix) {
        if (suffix == null) {
            suffix = "...";
        }
        if (oriString != null && oriString.length() > length) {
            return oriString.substring(0, length) + suffix;
        } else {
            return oriString;
        }
    }

    /**
     * 获取文件的扩展名
     *
     * @param filename
     * @return
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * 获取文件名，不包括扩展名
     *
     * @param filename
     * @return
     */
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    public static void main(String[] args) {
        System.out.print(UtilString.getStringByLength("1021中中", 2, "..."));
    }
}
