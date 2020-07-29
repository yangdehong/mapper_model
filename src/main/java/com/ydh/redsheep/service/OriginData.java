package com.ydh.redsheep.service;

import com.ydh.redsheep.entity.ColumnBO;
import com.ydh.redsheep.util.DataTypeTransformUtil;
import com.ydh.redsheep.util.JdbcUtil;
import com.ydh.redsheep.util.Underline2CamelUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
* 从数据库获取信息
* @author : yangdehong
* @date : 2020-07-29 11:04
*/
public class OriginData {

    public static final String allColumnSql = "SELECT COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT FROM INFORMATION_SCHEMA. COLUMNS WHERE table_name = ? AND table_schema = (select database())";

    public static List<ColumnBO> productColumnList(String tableName) {
        List<ColumnBO> columnList = new ArrayList<>();
        List<String> params = new ArrayList<>();
        params.add(tableName);
        // 创建DBHelper对象
        JdbcUtil db = new JdbcUtil(allColumnSql, params);
        ResultSet ret = null;
        try {
            // 执行语句，得到结果集
            ret = db.pst.executeQuery();
            while (ret.next()) {
                String columnName = ret.getString(1);
                String dataType = ret.getString(2);
                String columnComment = ret.getString(3);
                columnList.add(new ColumnBO(columnName, Underline2CamelUtil.underline2Camel(columnName), dataType,
                        DataTypeTransformUtil.dataTypeTransform("mysql", dataType), columnComment));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnList;
    }

}
