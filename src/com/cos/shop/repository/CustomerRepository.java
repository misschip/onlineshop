package com.cos.shop.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.shop.db.DBConn;
import com.cos.shop.model.Customer;
import com.cos.shop.model.Product;


public class CustomerRepository {
	private static final String TAG = "CustomerRepository : ";

	private static CustomerRepository instance = new CustomerRepository();
	private CustomerRepository() {}
	public static CustomerRepository getInstance() {
		return instance;
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	
	public Customer findByUsernameAndPassword(String username, String password) {
		final String SQL = "SELECT id,username,password,phone,email,address,registerdate FROM customer "
							+ " WHERE username = ? AND password = ?";
		
		Customer customer = null;
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				customer = Customer.builder()
						.id(rs.getInt("id"))
						.username(rs.getString("username"))
						.phone(rs.getString("phone"))
						.email(rs.getString("email"))
						.address(rs.getString("address"))
						.registerDate(rs.getTimestamp("registerdate"))
						.build();
								
				return customer;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findByUsernameAndPassword : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		return null;
	}
	
	
	public int save(Customer customer) {
		final String SQL = "";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "save : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt);
		}
		
		return -1;
	}
	
	
	public int update(Customer customer) {
		final String SQL = "";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "update : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt);
		}
		
		return -1;
	}
	
	
	public int deleteById(int id) {
		final String SQL = "DELETE FROM product WHERE id = ?";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			pstmt.setInt(1, id);
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "deleteById : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt);
		}
		return -1;
	}

	
	
	public List<Customer> findAll() {
		final String SQL = "SELECT id,username,password,phone,email,address,registerDate FROM customer";
		List<Customer> customers = new ArrayList<>();
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			
			rs = pstmt.executeQuery();
			// while 돌려서 리스트에 넣기
			while(rs.next()) {
				Customer customer = Customer.builder()
						.id(rs.getInt("id"))
						.username(rs.getString("username"))
						.phone(rs.getString("phone"))
						.email(rs.getString("email"))
						.address(rs.getString("address"))
						.registerDate(rs.getTimestamp("registerDate"))
						.build();
				
				customers.add(customer);
			}
			System.out.println("CustomerRepository.findAll : customers.size() :" + customers.size());
			return customers;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findAll : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null;
	}

}
