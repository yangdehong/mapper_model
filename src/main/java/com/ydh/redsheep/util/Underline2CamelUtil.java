package com.ydh.redsheep.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 驼峰下划线转换
 * @author : yangdehong
 * @date : 2018/9/5 14:20
 */
public class Underline2CamelUtil {

    /**
     * 正则表达式
     */
    public static Pattern PATTERN =  Pattern.compile("([A-Za-z\\d]+)(_)?");

    /**
     * 下划线转驼峰法  小驼峰
     *
     * @param line       源字符串
     * @return 转换后的字符串
     */
    public static String underline2Camel(String line) {
        return underline2Camel(line, true);
    }

    /**
     * 下划线转驼峰法
     *
     * @param line       源字符串
     * @param smallCamel 大小驼峰,是否为小驼峰
     * @return 转换后的字符串
     */
    public static String underline2Camel(String line, boolean smallCamel) {
        if (line == null || "".equals(line)) {
            return "";
        }
        Matcher matcher = PATTERN.matcher(line);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(smallCamel && matcher.start() == 0 ? Character.toLowerCase(word.charAt(0)) : Character.toUpperCase(word.charAt(0)));
            int index = word.lastIndexOf('_');
            if (index > 0) {
                sb.append(word.substring(1, index).toLowerCase());
            } else {
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }

//    /**
//     * 驼峰法转下划线
//     *
//     * @param line 源字符串
//     * @return 转换后的字符串
//     */
//    public static String camel2Underline(String line) {
//        if (line == null || "".equals(line)) {
//            return "";
//        }
//        line = String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
//        StringBuffer sb = new StringBuffer();
//        Matcher matcher = PATTERN.matcher(line);
//        while (matcher.find()) {
//            String word = matcher.group();
//            sb.append(word.toUpperCase());
//            sb.append(matcher.end() == line.length() ? "" : "_");
//        }
//        return sb.toString();
//    }

    public static void main(String[] args) {
        String line = "com_ydh_dog";
        String camel = underline2Camel(line, false);
        System.out.println(camel);
//        System.out.println(camel2Underline(camel));
    }

}
