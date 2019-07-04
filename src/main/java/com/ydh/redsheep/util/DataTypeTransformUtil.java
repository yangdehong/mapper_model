package com.ydh.redsheep.util;

/**
 * 数据类型转换
 * @author : yangdehong
 * @date : 2018/9/5 13:53
 */
public class DataTypeTransformUtil {

    /**
     * 数据库类型
     */
    public static final String DATABASE_MYSQL = "mysql";
    public static final String DATABASE_ORACLE = "oracle";

    /**
     * 字段类型
     */
    public static final String COLUMN_LONG = "bigint";
    public static final String COLUMN_INT = "int";
    public static final String COLUMN_SMALLINT = "smallint";
    public static final String COLUMN_TINYINT = "tinyint";
    public static final String COLUMN_DOUBLE = "double";
    public static final String COLUMN_DECIMAL = "decimal";
    public static final String COLUMN_VARCHAR = "varchar";
    public static final String COLUMN_BLOB = "blob";
    public static final String COLUMN_TEXT = "text";
    public static final String COLUMN_LONGTEXT = "longtext";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_DATETIME = "datetime";

    /**
     * @Description: 数据类型转换
     * @Param:
     * @return:
     * @Author: 杨德宏
     * @Date: 2018/7/23
     */
    public static String dataTypeTransform(String dbType, String dataType){
        String type = "";

        if (DATABASE_MYSQL.equals(dbType.toLowerCase())) {
            type = mysqlDataTypeTransform(dataType);
        } else if (DATABASE_ORACLE.equals(dbType.toLowerCase())) {

        } else {
            // TODO 异常
        }

        return type;
    }

    /**
     * @Description: mysql 转换
     * @Param:
     * @return:
     * @Author: 杨德宏
     * @Date: 2018/7/23
     */
    public static String mysqlDataTypeTransform(String dataType){
        String type = "";
        if (COLUMN_INT.equals(dataType) || COLUMN_TINYINT.equals(dataType) || COLUMN_SMALLINT.equals(dataType)) {
            type = "Integer";
        } else if (COLUMN_LONG.equals(dataType)) {
            type = "Long";
        } else if (COLUMN_DOUBLE.equals(dataType)) {
            type = "Double";
        } else if (COLUMN_DECIMAL.equals(dataType)) {
            type = "BigDecimal";
        } else if (COLUMN_VARCHAR.equals(dataType) || COLUMN_BLOB.equals(dataType) || COLUMN_TEXT.equals(dataType) || COLUMN_LONGTEXT.equals(dataType)) {
            type = "String";
        } else if (COLUMN_TIMESTAMP.equals(dataType) || COLUMN_DATE.equals(dataType) || COLUMN_DATETIME.equals(dataType)) {
            type = "Date";
        }

        return type;
    }

}
