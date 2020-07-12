package com.cos.shop.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.shop.db.DBConn;
import com.cos.shop.model.Customer;
import com.cos.shop.model.Item;


public class ItemRepository {
	private static final String TAG = "ItemRepository : ";

	private static ItemRepository instance = new ItemRepository();
	private ItemRepository() {}
	public static ItemRepository getInstance() {
		return instance;
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	public List<Item> findByOrdersId(int orders_id) {
		final String SQL = "SELECT id,orders_id,product_id,quantity,unit_price "
					+ " FROM item WHERE orders_id = ?";
		List<Item> items = new ArrayList<>();
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			pstmt.setInt(1, orders_id);
			
			rs = pstmt.executeQuery();
			// while 돌려서 리스트에 넣기
			while(rs.next()) {
				Item item = Item.builder()
						.id(rs.getInt("id"))
						.orders_id(rs.getInt("orders_id"))
						.product_id(rs.getInt("product_id"))
						.quantity(rs.getInt("quantity"))
						.unit_price(rs.getInt("unit_price"))
						.build();
				
				items.add(item);
			}
			
			return items;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findByOrdersId : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null;
	}	
	
	
	
	public int save(Item item) {
		final String SQL = "INSERT INTO item (id,orders_id,product_id,quantity,unit_price) "
							+ " VALUES (ITEM_SEQ.NEXTVAL,?,?,?,?)";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1, item.getOrders_id());
			pstmt.setInt(2, item.getProduct_id());
			pstmt.setInt(3, item.getQuantity());
			pstmt.setInt(4, item.getUnit_price());
			
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
	
	
	public int update(Item item) {
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
		final String SQL = "DELETE FROM item WHERE id = ?";
		
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

	
	
	public List<Item> findAll() {
		final String SQL = "SELECT  FROM item";
		List<Item> items = new ArrayList<>();
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			
			rs = pstmt.executeQuery();
			// while 돌려서 리스트에 넣기
			while(rs.next()) {
				Item item = Item.builder()
						.id(rs.getInt("id"))
						
						.build();
				
				items.add(item);
			}
			
			return items;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findAll : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null;
	}

}
