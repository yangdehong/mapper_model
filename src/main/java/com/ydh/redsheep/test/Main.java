package com.ydh.redsheep.test;

import com.ydh.redsheep.util.ApplicationContent;
import com.ydh.redsheep.util.JdbcUtil;
import com.ydh.redsheep.util.Underline2CamelUtil;
import org.apache.commons.lang3.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : yangdehong
 * @date : 2019-06-28 14:48
 */
public class Main {

    public static void main(String[] args) {

        Main main = new Main();
        List<String> list = main.getAllTables(ApplicationContent.tableName);

        list.forEach(tableName -> {
            String bigTableName = Underline2CamelUtil.underline2Camel(tableName, false);
            String smallTableName = Underline2CamelUtil.underline2Camel(tableName);
            ProductFile productFile = new ProductFile(tableName, bigTableName, smallTableName);
            productFile.start();
        });
    }

    /**
     * 获取所有的表
     *
     * @param tableName
     */
    public List<String> getAllTables(String tableName) {
        List<String> list = new ArrayList<>();
        if (StringUtils.isBlank(tableName)) {
            tableName = "%%";
        }
        // SQL语句
        String allTableSql = "SELECT DISTINCT TABLE_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME LIKE ? AND TABLE_SCHEMA = (select database())";

        List<String> params = new ArrayList<>();
        params.add(tableName);
        // 创建DBHelper对象
        JdbcUtil db = new JdbcUtil(allTableSql, params);
        ResultSet ret = null;
        try {
            // 执行语句，得到结果集
            ret = db.pst.executeQuery();

            while (ret.next()) {
                String tableRealName = ret.getString(1);
                list.add(tableRealName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ret.close();
                db.close();// 关闭连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}
