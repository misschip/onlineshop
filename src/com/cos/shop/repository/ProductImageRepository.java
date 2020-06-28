package com.cos.shop.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.shop.db.DBConn;
import com.cos.shop.dto.ProductResponseDto;
import com.cos.shop.model.Category;
import com.cos.shop.model.Product;
import com.cos.shop.model.ProductImage;


public class ProductImageRepository {
	private static final String TAG = "ProductImageRepository : ";

	private static ProductImageRepository instance = new ProductImageRepository();
	private ProductImageRepository() {}
	public static ProductImageRepository getInstance() {
		return instance;
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	public int save(ProductImage productImage) {
		final String SQL = "INSERT INTO productimage (id,product_id,addr) VALUES (PRODUCTIMAGE_SEQ.NEXTVAL,?,?)";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1, productImage.getProduct_id());
			pstmt.setString(2, productImage.getAddr());

			
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
	
	
	// 테이블에 Product와 ProductImage 넣는 작업을 하나의 Connection (세션)으로 처리하기 위해
	// Connection을 매개값으로 받아서 쓰는 save()를 작성
	// 이 메서드는 Connection을 닫으면 안된다!
	public int save(Connection conn, ProductImage productImage) {
		final String SQL = "INSERT INTO productimage (id,product_id,addr) VALUES (PRODUCTIMAGE_SEQ.NEXTVAL,?,?)";
		
		try {

			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1, productImage.getProduct_id());
			pstmt.setString(2, productImage.getAddr());

			
			int result = pstmt.executeUpdate();
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "save : " + e.getMessage());
		} finally {
			// DBConn.close(conn, pstmt);
		}
		
		return -1;
	}
	
	
	public int update(ProductImage productImage) {
		final String SQL = "UPDATE productimage SET product_id = ?, addr = ? WHERE id = ?";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1, productImage.getProduct_id());
			pstmt.setString(2, productImage.getAddr());
			pstmt.setInt(5, productImage.getId());
			
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
		final String SQL = "DELETE FROM productimage WHERE id = ?";
		
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

	

	public List<ProductImage> findAll() {
		final String SQL = "SELECT id,product_id,addr FROM productimage";
		List<ProductImage> productImages = new ArrayList<>();
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			
			rs = pstmt.executeQuery();
			// while 돌려서 리스트에 넣기
			while(rs.next()) {
				ProductImage productImage = ProductImage.builder()
						.id(rs.getInt("id"))
						.product_id(rs.getInt("product_id"))
						.addr(rs.getString("addr"))
						.build();
				
				productImages.add(productImage);
			}
			
			return productImages;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findAll : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null;
	}

}
