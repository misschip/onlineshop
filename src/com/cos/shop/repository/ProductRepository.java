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



public class ProductRepository {
	private static final String TAG = "ProductRepository : ";

	private static ProductRepository instance = new ProductRepository();
	private ProductRepository() {}
	public static ProductRepository getInstance() {
		return instance;
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	

	
	
	
	
	public int countByKeyword(String keyword) {
		final String SQL = "SELECT count(*) FROM product "
				+ " WHERE name LIKE ? OR description LIKE ?";

		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
	
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return rs.getInt(1);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "countByKeyword(keyword) : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		return -1;
		
	}	
	
	
	public int countByCategory(int categoryId) {
		final String SQL = "SELECT count(*) FROM product "
				+ " WHERE category_id = ?";

		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1, categoryId);
	
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return rs.getInt(1);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "countByCategory(cateId) : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		return -1;
		
	}
	
	
	public int count(String keyword) {
		final String SQL = "SELECT count(*) FROM product "
							+ " WHERE name LIKE ? OR description LIKE ?";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "count(keyword) : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return -1;
	}	
	
	
	
	public int count() {
		final String SQL = "SELECT count(*) FROM product";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "count : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return -1;
	}	
	
	
	
	// 특정 page를 위해 9개의 Product 를 반환함
	// SQL 중에 OFFSET ? ROWS FETCH NEXT 9 ROWS ONLY 부분이 중요
	public List<Product> findByKeyword(int page, String keyword) {
		final String SQL = "SELECT id,category_id,name,description,price,image1,image2,image3 "
							+ " FROM product "
							+ " WHERE name LIKE ? OR description LIKE ? "
							+ " OFFSET ? ROWS FETCH NEXT 9 ROWS ONLY";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setInt(3, 9*page);
			
			rs = pstmt.executeQuery();
			
			List<Product> products = new ArrayList<>();
			
			while (rs.next()) {
				Product product = Product.builder()
						.id(rs.getInt("id"))
						.category_id(rs.getInt("category_id"))
						.name(rs.getString("name"))
						.description(rs.getString("description"))
						.price(rs.getInt("price"))
						.image1(rs.getString("image1"))
						.image2(rs.getString("image2"))
						.image3(rs.getString("image3"))
						.build();
				
				products.add(product);
			}
			
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findByKeyword : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		return null;		
		
	}		
	
	
	
	public List<Product> findByCategoryId(int page, int categoryId) {
		final String SQL = "SELECT id,category_id,name,description,price,image1,image2,image3 "
							+ " FROM product "
							+ " WHERE category_id = ? "
							+ " OFFSET ? ROWS FETCH NEXT 9 ROWS ONLY";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1, categoryId);
			pstmt.setInt(2, 9*page);
			
			rs = pstmt.executeQuery();
			
			List<Product> products = new ArrayList<>();
			
			while (rs.next()) {
				Product product = Product.builder()
						.id(rs.getInt("id"))
						.category_id(rs.getInt("category_id"))
						.name(rs.getString("name"))
						.description(rs.getString("description"))
						.price(rs.getInt("price"))
						.image1(rs.getString("image1"))
						.image2(rs.getString("image2"))
						.image3(rs.getString("image3"))
						.build();
				
				products.add(product);
			}
			
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findByCategoryId : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		return null;		
		
	}	
	
	
	
	public Product findById(int id) {
		final String SQL = "SELECT id,category_id,name,description,price,image1,image2,image3 FROM product "
							+ "WHERE id = ?";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				Product product = Product.builder()
						.id(rs.getInt("id"))
						.category_id(rs.getInt("category_id"))
						.name(rs.getString("name"))
						.description(rs.getString("description"))
						.price(rs.getInt("price"))
						.image1(rs.getString("image1"))
						.image2(rs.getString("image2"))
						.image3(rs.getString("image3"))
						.build();
				
				return product;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findById : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		return null;		
		
	}
	
	
		
	public int save(Product product) {
		final String SQL = "INSERT INTO product (id,category_id,name,description,price) VALUES (PRODUCT_SEQ.NEXTVAL,?,?,?,?)";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1, product.getCategory_id());
			pstmt.setString(2, product.getName());
			pstmt.setString(3, product.getDescription());
			pstmt.setInt(4, product.getPrice());
			
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
	public int save(Connection conn, Product product) {
		final String SQL = "INSERT INTO product (id,category_id,name,description,price) VALUES (PRODUCT_SEQ.NEXTVAL,?,?,?,?)";
		
		try {
			// 이 메서드는 Connection 객체를 매개값으로 받아옴
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1, product.getCategory_id());
			pstmt.setString(2, product.getName());
			pstmt.setString(3, product.getDescription());
			pstmt.setInt(4, product.getPrice());
			
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
	
	// product_seq.nextval 명령이 가장 최근에 발생(반환)한 값을 알아내기
	public int getLastPid(Connection conn) {
		final String SQL = "SELECT product_seq.currval FROM DUAL";
		
		try {
			// 이 메서드는 Connection 객체를 매개값으로 받아옴
			pstmt = conn.prepareStatement(SQL);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				int pid = rs.getInt(1);
				return pid;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "getLastPid : " + e.getMessage());
		} finally {
			// DBConn.close(conn, pstmt);
		}
		
		return -1;		
		
	}
	
	
	
	public int update(Product product) {
		final String SQL = "UPDATE product SET category_id = ?, name = ?, description = ?, price = ?, "
							+ " image1 = ?, image2 = ?, image3 = ? WHERE id = ?";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1, product.getCategory_id());
			pstmt.setString(2, product.getName());
			pstmt.setString(3, product.getDescription());
			pstmt.setInt(4, product.getPrice());
			pstmt.setString(5, product.getImage1());
			pstmt.setString(6, product.getImage2());
			pstmt.setString(7, product.getImage3());
			pstmt.setInt(8, product.getId());
			
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

	

	public List<Product> findAll(int page) {
		final String SQL = "SELECT id,category_id,name,description,price,image1,image2,image3 "
							+ " FROM product "
							+ " OFFSET ? ROWS FETCH NEXT 9 ROWS ONLY";
		
		List<Product> products = new ArrayList<>();
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			pstmt.setInt(1, 9*page);
			
			rs = pstmt.executeQuery();
			// while 돌려서 리스트에 넣기
			while(rs.next()) {
				
				Product product = Product.builder()
						.id(rs.getInt("id"))
						.category_id(rs.getInt("category_id"))
						.name(rs.getString("name"))
						.description(rs.getString("description"))
						.price(rs.getInt("price"))
						.image1(rs.getString("image1"))
						// .image1(image1updated)
						.image2(rs.getString("image2"))
						.image3(rs.getString("image3"))
						.build();
				
				products.add(product);
			}
			
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findAll : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null;
	}

	



	public List<ProductResponseDto> findAllWithCategory() {
		final String SQL = "SELECT product.id,category_id,name,description,price,root_category,sub_category "
						+ " FROM product "
						+ " INNER JOIN category "
						+ " ON product.category_id = category.id ";
		List<ProductResponseDto> productDtos = new ArrayList<>();
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			
			rs = pstmt.executeQuery();
			// while 돌려서 리스트에 넣기
			while(rs.next()) {
				Product product = Product.builder()
						.id(rs.getInt("id"))
						.category_id(rs.getInt("category_id"))
						.name(rs.getString("name"))
						.description(rs.getString("description"))
						.price(rs.getInt("price"))
						.build();
				
				Category category = Category.builder()
						.root_category(rs.getString("root_category"))
						.sub_category(rs.getString("sub_category"))
						.build();
				
				ProductResponseDto productDto = new ProductResponseDto();
				productDto.setProduct(product);
				productDto.setCategory(category);
				
				productDtos.add(productDto);
				
			}
			
			return productDtos;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findAll : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null;
	}

	
}
