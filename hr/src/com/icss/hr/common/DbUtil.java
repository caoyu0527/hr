package com.icss.hr.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sun.org.apache.bcel.internal.generic.NEW;

import oracle.jdbc.driver.OracleDriver;

/**
 * ���ݿ⹤����
 * @author Administrator
 *
 */
public class DbUtil {
	
	//��������Դ����
		private static ComboPooledDataSource dataSource;
	
		
	//�����̱߳��ر���
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
	
	static {
		
		try {
			dataSource = new ComboPooledDataSource();
			
			//���Ӳ���
			dataSource.setDriverClass("oracle.jdbc.driver.OracleDriver");//ע������
			dataSource.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");//jdbc����url
			dataSource.setUser("myhr");//�û���
			dataSource.setPassword("myhr");//����
			
			//���ӳ�����
			dataSource.setInitialPoolSize(10);//��ʼ���ӳ�
			dataSource.setMaxPoolSize(20);//���������
			dataSource.setMinPoolSize(10);//���ٱ�����������
			dataSource.setMaxIdleTime(60);//�����������60��û�б�ʹ�ã�������
			
		} catch (Exception e) {			
			e.printStackTrace();
		}			
	}
	
	/**
	 * �������ݿ����Ӷ��� 
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		
		//�ȷ����̱߳��ر����е����Ӷ���
		Connection conn = threadLocal.get();
		
		//������Ӷ��󲻴��ڣ��������Ӷ����ѹرգ������ӳط���һ��������,Ȼ��洢���̱߳��ر���
		if(conn == null || conn.isClosed()) {
			conn = dataSource.getConnection();
			threadLocal.set(conn);
		}
		
		//������Ӷ����ڵ�ǰ�����߳��Ѵ��ڣ�ֱ�ӷ���ԭ�������Ӷ���
		return conn;
	}
	
	/**
	 * �ر�����
	 */
	public static void close() {
		
		//�ȷ����̱߳��ر����е����Ӷ���
		Connection conn = threadLocal.get();
		
		//������Ӷ�����ڣ��������Ӷ���û�йر�
		
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
	 
