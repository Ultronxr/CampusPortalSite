package com.fc.cps.dao.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import com.fc.cps.dao.jdbc.JDBCUtil;

public class JDBCBase {

	/**
	 * 增删改数据库的函数
	 * @param: sqlStr sql语句
	 * @param: values 加入sql语句对应?的位置的内容
	 * @return: 是否成功
	 */
	public static boolean addOrDelOrQuery(String sqlStr, Object[] values) {
		PreparedStatement stmt = null;
		Connection conn = JDBCUtil.getConnection();
		try {
			stmt = conn.prepareStatement(sqlStr);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					stmt.setObject(i + 1, values[i]);
				}
			}
			stmt.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConnection(null, stmt, conn);
		}
		return false;
	}
}
