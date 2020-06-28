package com.cos.shop.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConn {
	public static Connection getConnection() {
		try {
			// 아래 4줄 코드 출처는 http://tomcat.apache.org/tomcat-8.5-doc/jndi-datasource-examples-howto.html#Oracle_8i,_9i_&_10g
			// db의 아이디와 패스워드 등 정보는 context.xml 볼 것!
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
			Connection conn = ds.getConnection();
			
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DBConn : 데이터베이스 연결 실패");
			System.out.println("DBConn : Message : " + e.getMessage());
		}
		
		return null;
	}
	

	
	public static void close(Connection conn) {
		try {
			conn.close();
		} catch (Exception e) {
			System.out.println("DB종료시 오류가 발생 : "+e.getMessage());
		}
	}
	
	
	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			conn.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println("DB종료시 오류가 발생 : "+e.getMessage());
		}
	}
	
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			conn.close();
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			System.out.println("DB종료시 오류가 발생 : "+e.getMessage());
		}
	}
	
	
	public static void rollback(Connection conn) {
		if (conn != null) {
			try {
				conn.rollback();
			} catch (Exception e) {
				System.out.println("DB rollback 중 오류 발생 : "+e.getMessage());
			}
		}
	}

}
