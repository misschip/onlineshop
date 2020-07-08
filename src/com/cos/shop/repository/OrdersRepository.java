package com.cos.shop.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cos.shop.db.DBConn;
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
	
	
	// 특이점
	//	- 반환값이 INSERT문의 성공여부를 가리키는 -1,0,1 중의 어떤 값이 아니라
	//	  ORDERS_SEQ.NEXTVAL이 생성한 값을 반환!
	public int save(Orders order) {
		final String SQL = "INSERT INTO orders (id,customer_id,orders_date,phone,email,address,zipno,recipient_name,payment,total,status) "
						+ " VALUES (ORDERS_SEQ.NEXTVAL,?,sysdate,?,?,?,?,?,?,?,?)";
		
		try {
			conn = DBConn.getConnection();
			// prepareStatement()의 두번째 매개값으로 준 상수값은 getGeneratedKeys() 메서드를 사용하기 위한 준비
			// pstmt = conn.prepareStatement(SQL,  Statement.RETURN_GENERATED_KEYS);
			pstmt = conn.prepareStatement(SQL,  new String[]{"id"});
			
			pstmt.setInt(1, order.getCustomer_id());
			pstmt.setString(2, order.getPhone());
			pstmt.setString(3, order.getEmail());
			pstmt.setString(4, order.getAddress());
			pstmt.setString(5, order.getZipno());
			pstmt.setString(6, order.getRecipient_name());
			pstmt.setString(7, order.getPayment());
			pstmt.setInt(8, order.getTotal());
			pstmt.setString(9, order.getStatus());
			
			int result = pstmt.executeUpdate();
			// return result;
			
			// INSERT 명령이 성공적으로 수행된 경우
			if (result == 1) {
				System.out.println("OrdersReposity: save: 저장성공");
				rs = pstmt.getGeneratedKeys();
				
				/*
				ResultSetMetaData rsmd = rs.getMetaData();
				int colCount= rsmd.getColumnCount();
				String label = rsmd.getColumnLabel(1);
				String colType = rsmd.getColumnTypeName(1);
				System.out.println(TAG + "save : colCount: " + colCount);
				System.out.println(TAG + "save : column label : " + label);
				System.out.println(TAG + "save : column type : " + colType);
				*/
				
				if (rs.next()) {
					long seq_value = rs.getLong(1);
					System.out.println(TAG + "save : seq_val : " + seq_value);
					return (int)seq_value;
				}
				
			}
			
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
		final String SQL = "SELECT id,customer_id,orders_date,address,recipient_name,phone,payment,total,status "
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
						.address(rs.getString("address"))
						.recipient_name(rs.getString("recipient_name"))
						.phone(rs.getString("phone"))
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