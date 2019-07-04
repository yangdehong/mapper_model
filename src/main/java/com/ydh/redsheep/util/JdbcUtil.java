package com.ydh.redsheep.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC链接工具类
 * @author : yangdehong
 * @date : 2018/9/5 14:19
 */
public class JdbcUtil {
	
	public Connection conn = null;
	public PreparedStatement pst = null;

	public JdbcUtil(String sql, List<String> params) {
		try {
			// 指定连接类型
			Class.forName(PropertiesUtil.loadProperties("db.drive"));
			String url = PropertiesUtil.loadProperties("db.url");
			String username = PropertiesUtil.loadProperties("db.username");
			String password = PropertiesUtil.loadProperties("db.password");
			// 获取连接
			conn = DriverManager.getConnection(url, username, password);
			// 准备执行语句
			pst = conn.prepareStatement(sql);
			if (params!=null) {
				for (int i=0; i<params.size(); i++) {
					pst.setString(i+1, params.get(i));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			this.conn.close();
			this.pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// SQL语句
		String allTableSql = "SELECT DISTINCT TABLE_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME LIKE ? AND TABLE_SCHEMA = (select database())";
		List<String> params = new ArrayList<>();
		params.add("%%");

		JdbcUtil db = new JdbcUtil(allTableSql, params);
		ResultSet ret = null;
		try {
			// 执行语句，得到结果集
			ret = db.pst.executeQuery();

			while (ret.next()) {
				System.out.println(ret.getString(1));
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
	}
}
