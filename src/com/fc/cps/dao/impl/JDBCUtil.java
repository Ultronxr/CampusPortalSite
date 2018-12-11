package com.fc.cps.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil {
	
	private Connection con = null;
	private static String jdbcDriver = null;
	private static String url = null;
	private static String username = null;
	private static String password = null;
	
	
	static {
		loadProperties();
		try {
			Class.forName(jdbcDriver);
		} catch (Exception e) {
		    System.out.println("配置文件加载失败！");
			e.printStackTrace();
		}
	}
	
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			 con = DriverManager.getConnection(url, username, password);//��ȡ����  
		} catch (Exception e) {
		    System.out.println("数据库连接失败！");
			e.printStackTrace();
		}
		return con;
	}
	
	
	public static ResultSet getResultSet(Connection con, String ps) {
		ResultSet res = null;
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(ps);
			res = pst.executeQuery();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return res;
		
	}
	
	
	
	public static void closeConnection(ResultSet rs, PreparedStatement stmt, Connection con) {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (stmt != null)
				stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void loadProperties() {
		InputStream inputStream  =  JDBCUtil.class.getClassLoader().getResourceAsStream( "jdbc.properties" );    
		Properties p = new Properties();
		try{
		   p.load(inputStream);
		}catch(IOException e1){
		   System.out.println("驱动加载失败！");   
		}
		
		jdbcDriver = p.getProperty("driverClassName");
		url = p.getProperty("url");
		username = p.getProperty("username");
		password = p.getProperty("password");
		
	}
	
	public static void main(String[] args) {
		Connection connection = JDBCUtil.getConnection();
		JDBCUtil.closeConnection(null, null, connection);
	}
}

