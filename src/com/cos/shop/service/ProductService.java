package com.cos.shop.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.cos.shop.db.DBConn;
import com.cos.shop.model.Product;
import com.cos.shop.model.ProductImage;
import com.cos.shop.repository.ProductImageRepository;
import com.cos.shop.repository.ProductRepository;

public class ProductService {
	private static final String TAG = "ProductService : ";

	private static ProductService instance = new ProductService();
	private ProductService() {}
	public static ProductService getInstance() {
		return instance;
	}
	
	private ProductRepository productRepository = ProductRepository.getInstance();
	private ProductImageRepository productImageRepository = ProductImageRepository.getInstance();
	
	
	// Product와 ProductImage 테이블을 따로 둘 때 필요했던 메서드임
	// Product 테이블에 image 링크를 넣음으로써 필요없어진 메서드
	public int registerNewProduct(Product product, List<ProductImage> productImages) {
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			conn.setAutoCommit(false);
			
			int result = productRepository.save(conn, product);
			
			if (result != 1) {
				throw new SQLException("Product 테이블에 상품 등록 실패");
			}
			
			int pid = productRepository.getLastPid(conn);
			
			System.out.println(TAG + "pid : " + pid);
			
			for (ProductImage productImage : productImages) {
				productImage.setProduct_id(pid);
				int result2 = productImageRepository.save(conn,productImage);
				
				if (result2 != 1) {
					throw new SQLException("ProductImage 테이블에 상품이미지 등록 실패");
				}
			}
			
			conn.commit();
			
			return 1;
		} catch (SQLException e) {
			DBConn.rollback(conn);
		} finally {
			DBConn.close(conn);
		}
		
		return -1;
	}
}
