package com.cos.shop.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.shop.db.DBConn;
import com.cos.shop.model.Category;
import com.cos.shop.model.Item;


public class CategoryRepository {
	private static final String TAG = "CategoryRepository : ";

	private static CategoryRepository instance = new CategoryRepository();
	private CategoryRepository() {}
	public static CategoryRepository getInstance() {
		return instance;
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	public int save(Category category) {
		final String SQL = "INSERT INTO category (id,root_category,sub_category) VALUES (CATEGORY_SEQ.NEXTVAL,?,?)";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, category.getRoot_category());
			pstmt.setString(2, category.getSub_category());
			
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
	
	
	public int update(Category category) {
		final String SQL = "UPDATE category SET root_category = ?, sub_category = ? WHERE id = ?";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, category.getRoot_category());
			pstmt.setString(2, category.getSub_category());
			pstmt.setInt(3, category.getId());
			
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "update : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt);
		}
		
		return -1;
	}
	
	
	public int deleteById(int id) {
		final String SQL = "DELETE FROM category WHERE id = ?";
		
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

	
	
	public List<Category> findAll() {
		final String SQL = "SELECT id,root_category,sub_category FROM category";
		List<Category> categories = new ArrayList<>();
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			
			rs = pstmt.executeQuery();
			// while 돌려서 리스트에 넣기
			while(rs.next()) {
				Category category = Category.builder()
						.id(rs.getInt("id"))
						.root_category(rs.getString("root_category"))
						.sub_category(rs.getString("sub_category"))
						.build();
				
				categories.add(category);
			}
			
			return categories;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findAll : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null;
	}
	
	
	public List<String> findRootAll() {
		final String SQL = "SELECT DISTINCT root_category FROM category";
		List<String> rootCategories = new ArrayList<>();
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			
			rs = pstmt.executeQuery();
			// while 돌려서 리스트에 넣기
			while(rs.next()) {
				
				rootCategories.add(rs.getString("root_category"));
			}
			
			return rootCategories;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findRootAll : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null;
	}

}
