package com.cos.shop.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.shop.db.DBConn;
import com.cos.shop.model.PayResult;

public class PayResultRepository {
	private static final String TAG = "PayResultRepository : ";

	private static PayResultRepository instance = new PayResultRepository();
	private PayResultRepository() {}
	public static PayResultRepository getInstance() {
		return instance;
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	public int save(PayResult payResult) {
		final String SQL = "INSERT INTO payresult (id,orders_id,imp_uid,merchant_uid,paid_amount,apply_num) "
							+ " VALUES (PAYRESULT_SEQ.NEXTVAL,?,?,?,?,?)";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1, payResult.getOrders_id());
			pstmt.setString(2, payResult.getImp_uid());
			pstmt.setString(3, payResult.getMerchant_uid());
			pstmt.setInt(4, payResult.getPaid_amount());
			pstmt.setString(5, payResult.getApply_num());

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
	
	
	public int update(PayResult result) {
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

	
	
	public List<PayResult> findAll() {
		final String SQL = "SELECT  FROM orders";
		List<PayResult> results = new ArrayList<>();
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			
			rs = pstmt.executeQuery();
			// while 돌려서 리스트에 넣기
			while(rs.next()) {
				PayResult result = PayResult.builder()
						.id(rs.getInt("id"))
						
						.build();
				
				results.add(result);
			}
			
			return results;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findAll : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null;
	}

}
