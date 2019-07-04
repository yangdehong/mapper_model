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

    private static Properties properties = new Properties();

    static {
        InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String loadProperties(String key) {
        return properties.getProperty(key);
    }

}
