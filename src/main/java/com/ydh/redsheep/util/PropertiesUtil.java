package com.ydh.redsheep.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * properties工具类
 * @author : yangdehong
 * @date : 2018/9/5 14:20
 */
public class PropertiesUtil {

    private static Properties jdbcProperties = new Properties();
    private static Properties paramsProperties = new Properties();
    private static Properties enumsProperties = new Properties();

    static {
        InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            jdbcProperties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream in2 = PropertiesUtil.class.getClassLoader().getResourceAsStream("params.properties");
        try {
            paramsProperties.load(in2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream in3 = PropertiesUtil.class.getClassLoader().getResourceAsStream("enums.properties");
        try {
            enumsProperties.load(in3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String loadJdbcProperties(String key) {
        return jdbcProperties.getProperty(key);
    }

    public static String loadParamsProperties(String key) {
        return paramsProperties.getProperty(key);
    }

    public static String loadEnumProperties(String key) {
        return enumsProperties.getProperty(key);
    }


}
