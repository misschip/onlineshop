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
	

	public Customer findById(int id) {
		final String SQL = "SELECT id,username,password,phone,email,address,zipNo,registerdate FROM customer "
							+ " WHERE id = ?";
		
		Customer customer = null;
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1, id);

			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				customer = Customer.builder()
						.id(rs.getInt("id"))
						.username(rs.getString("username"))
						.phone(rs.getString("phone"))
						.email(rs.getString("email"))
						.address(rs.getString("address"))
						.zipNo(rs.getInt("zipNo"))
						.registerDate(rs.getTimestamp("registerdate"))
						.build();
								
				return customer;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findById : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		return null;
	}	
	
	
	
	public Customer findByUsername(String username) {
		final String SQL = "SELECT id,username,password,phone,email,address,registerdate FROM customer "
							+ " WHERE username = ?";
		
		Customer customer = null;
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, username);
			
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
			System.out.println(TAG + "findByUsername : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		return null;
	}
	
	
	
	public Customer findByUsernameAndPassword(String username, String password) {
		final String SQL = "SELECT id,username,password,phone,email,address,zipNo,registerdate FROM customer "
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
						.zipNo(rs.getInt("zipNo"))
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
		final String SQL = "INSERT INTO customer (id,username,password,phone,email,address,registerdate,zipno) "
							+ " VALUES (customer_seq.nextval,?,?,?,?,?,sysdate,?)";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, customer.getUsername());
			pstmt.setString(2, customer.getPassword());
			pstmt.setString(3, customer.getPhone());
			pstmt.setString(4, customer.getEmail());
			pstmt.setString(5, customer.getAddress());
			pstmt.setInt(6, customer.getZipNo());
			
			int result = pstmt.executeUpdate();
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "save : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt);
		}
		
		return -1;
	}
	
	
	public int update(Customer customer) {
		final String SQL = "UPDATE customer SET password = ?, phone = ?, email = ?, address = ?, zipno = ? "
							+ " WHERE id = ?";
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, customer.getPassword());
			pstmt.setString(2, customer.getPhone());
			pstmt.setString(3, customer.getEmail());
			pstmt.setString(4, customer.getAddress());
			pstmt.setInt(5, customer.getZipNo());
			pstmt.setInt(6, customer.getId());
			
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
