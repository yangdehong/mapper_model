package com.ydh.redsheep.util;

/**
*
* @author : yangdehong
* @date : 2019-07-04 14:34
*/
public class ApplicationConstant {

    public static final String filePath = PropertiesUtil.loadProperties("mybatis.file.path");
    public static final String controllerPath = PropertiesUtil.loadProperties("mybatis.controller.path");
    public static final String servicePath = PropertiesUtil.loadProperties("mybatis.service.path");
    public static final String mapperPath = PropertiesUtil.loadProperties("mybatis.mapper.path");
    public static final String modelPath = PropertiesUtil.loadProperties("mybatis.model.path");

    public static final String modelSuffix = PropertiesUtil.loadProperties("model.suffix");

    public static final String packages = PropertiesUtil.loadProperties("mybatis.packages.path");

    public static final String author = PropertiesUtil.loadProperties("author");

    public static final String tableName = PropertiesUtil.loadProperties("table_name");

    public static final boolean isTkMybatis = Boolean.valueOf(PropertiesUtil.loadProperties("isTkMybatis"));

}
