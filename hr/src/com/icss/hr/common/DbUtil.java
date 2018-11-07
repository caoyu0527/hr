package com.icss.hr.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sun.org.apache.bcel.internal.generic.NEW;

import oracle.jdbc.driver.OracleDriver;

/**
 * 数据库工具类
 * @author Administrator
 *
 */
public class DbUtil {
	
	//创建数据源对象
		private static ComboPooledDataSource dataSource;
	
		
	//创建线程本地变量
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
	
	static {
		
		try {
			dataSource = new ComboPooledDataSource();
			
			//连接参数
			dataSource.setDriverClass("oracle.jdbc.driver.OracleDriver");//注册驱动
			dataSource.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");//jdbc连接url
			dataSource.setUser("myhr");//用户名
			dataSource.setPassword("myhr");//密码
			
			//连接池设置
			dataSource.setInitialPoolSize(10);//初始连接池
			dataSource.setMaxPoolSize(20);//最大连接数
			dataSource.setMinPoolSize(10);//最少保留的连接数
			dataSource.setMaxIdleTime(60);//空闲连接如果60秒没有被使用，就销毁
			
		} catch (Exception e) {			
			e.printStackTrace();
		}			
	}
	
	/**
	 * 返回数据库连接对象 
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		
		//先返回线程本地变量中的连接对象
		Connection conn = threadLocal.get();
		
		//如果连接对象不存在，或者连接对象已关闭，从连接池返回一个新连接,然后存储到线程本地变量
		if(conn == null || conn.isClosed()) {
			conn = dataSource.getConnection();
			threadLocal.set(conn);
		}
		
		//如果连接对象在当前请求线程已存在，直接返回原来的连接对象
		return conn;
	}
	
	/**
	 * 关闭连接
	 */
	public static void close() {
		
		//先返回线程本地变量中的连接对象
		Connection conn = threadLocal.get();
		
		//如果连接对象存在，并且连接对象没有关闭
		
			try {
				if(conn != null && !conn.isClosed()) {
				conn.close();
				
				threadLocal.remove();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	 

