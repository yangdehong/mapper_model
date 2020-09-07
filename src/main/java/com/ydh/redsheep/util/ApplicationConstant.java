package com.ydh.redsheep.util;

/**
*
* @author : yangdehong
* @date : 2019-07-04 14:34
*/
public class ApplicationConstant {

    public static final String filePath = PropertiesUtil.loadParamsProperties("mybatis.file.path");
    public static final String controllerPath = PropertiesUtil.loadParamsProperties("mybatis.controller.path");
    public static final String servicePath = PropertiesUtil.loadParamsProperties("mybatis.service.path");
    public static final String mapperPath = PropertiesUtil.loadParamsProperties("mybatis.mapper.path");
    public static final String modelPath = PropertiesUtil.loadParamsProperties("mybatis.model.path");
    public static final String modelPageSearchPath = PropertiesUtil.loadParamsProperties("mybatis.model.page.search.path");

    public static final String modelSuffix = PropertiesUtil.loadParamsProperties("model.suffix");
    public static final String modelPageSearchSuffix = PropertiesUtil.loadParamsProperties("model.page.search.suffix");

    public static final String packages = PropertiesUtil.loadParamsProperties("mybatis.packages.path");

    public static final String author = PropertiesUtil.loadParamsProperties("author");

    public static final String tableName = PropertiesUtil.loadParamsProperties("table_name");

    public static final boolean isTkMybatis = Boolean.valueOf(PropertiesUtil.loadParamsProperties("isTkMybatis"));

}
