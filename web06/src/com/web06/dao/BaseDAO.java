package com.web06.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class BaseDAO {
	private static String USER;
	private static String PWD;
	private static String DRIVER;
	private static String URL;
	
	//驱动要首先加载，其次只加载一次，所以写在静态初始化块中
	static{
		//读取properties配置文件，给连接数据库的这些属性赋值
		initProperties();
		try {
			Class.forName(DRIVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void initProperties(){
		String cfgFile = "database.properties";
		InputStream is = BaseDAO.class.getClassLoader().getResourceAsStream(cfgFile);
		
		Properties props = new Properties();
		try {
			props.load(is);
			USER = props.getProperty("USER");
			PWD = props.getProperty("PWD");
			DRIVER = props.getProperty("DRIVER");
			URL = props.getProperty("URL");
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public Connection getConn(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PWD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public void close(Connection conn, Statement ps, ResultSet rs){
		try {
			if(rs != null){
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			if(ps != null){
				ps.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			if(conn != null){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int executeUpdate(String sql, Object...params){
		Connection conn = null;
		PreparedStatement ps = null;		
		int rowCount = -1;
		
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			
			//给问号占位符赋值
			if(params != null){
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i+1, params[i]);
				}
			}
			rowCount = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(conn, ps, null);
		}
		
		return rowCount;
	}
}

