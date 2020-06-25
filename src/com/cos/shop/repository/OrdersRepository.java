package com.cos.shop.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.shop.db.DBConn;
import com.cos.shop.dto.OrdersResponseDto;
import com.cos.shop.model.Customer;
import com.cos.shop.model.Orders;


public class OrdersRepository {
	private static final String TAG = "OrdersRepository : ";

	private static OrdersRepository instance = new OrdersRepository();
	private OrdersRepository() {}
	public static OrdersRepository getInstance() {
		return instance;
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	public int save(Orders order) {
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
	
	
	public int update(Orders order) {
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
		final String SQL = "DELETE FROM orders WHERE id = ?";
		
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

	
	
	public List<Orders> findAll() {
		final String SQL = "SELECT id,customer_id,orders_date,shipping_address,recipient_name,recipient_phone,payment,total,status "
								+ " FROM orders";
		List<Orders> orders = new ArrayList<>();
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			
			rs = pstmt.executeQuery();
			// while 돌려서 리스트에 넣기
			while(rs.next()) {
				Orders order = Orders.builder()
						.id(rs.getInt("id"))
						.orders_date(rs.getTimestamp("orders_date"))
						.shipping_address(rs.getString("shipping_address"))
						.recipient_name(rs.getString("recipient_name"))
						.recipient_phone(rs.getString("recipient_phone"))
						.payment(rs.getString("payment"))
						.total(rs.getInt("total"))
						.status(rs.getString("status"))
						.build();
				
				orders.add(order);
			}
			
			return orders;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findAll : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null;
	}

}


/*

Orders, Customer, Item, Product 4개 테이블 JOIN
final String SQL = "SELECT "
							+ "o.id as oid,orders_date,shipping_address,recipient_name,recipient_phone,payment,total,status, " // Orders 필드들
							+ "c.id as cid,username,"										// Customer 필드들
							+ "i.id as iid,quantity,unit_price, "							// Item 필드들
							+ "p.name,p.description"										// Product 필드들
								+ "FROM orders o "							// 아래는 각 테이블 INNER JOIN
								+ "INNER JOIN item i "
										+ "ON o.id = i.orders_id "
								+ "INNER JOIN product p "
										+ "ON i.product_id = p.id "
								+ "INNER JOIN customer c "
										+ "ON o.customer_id = c.id";

*/