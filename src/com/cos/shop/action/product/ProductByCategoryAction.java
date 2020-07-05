package com.cos.shop.action.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.shop.action.Action;
import com.cos.shop.model.Product;
import com.cos.shop.repository.ProductRepository;

public class ProductByCategoryAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String categoryIdStr = request.getParameter("cate");
		int page = Integer.parseInt(request.getParameter("page"));
		
		System.out.println("ProductByCategoryAction : categoryId : " + categoryIdStr);
		
		if (categoryIdStr == null || categoryIdStr.equals("")) {
			System.out.println("categoryId 값을 받아오지 못했습니다.");
			return;
		}
		
		int categoryId = Integer.parseInt(categoryIdStr);
		
		ProductRepository productRepository = ProductRepository.getInstance();
		List<Product> products = productRepository.findByCategoryId(page, categoryId);
		
		System.out.println("ProductByCategoryAction : products.size() : " + products.size());
		
		request.setAttribute("products", products);
		
		
		int total = productRepository.countByCategory(categoryId);
		int lastPage = (total-1)/9;		// 한 페이지에 9개씩의 상품을 표출
		
		request.setAttribute("lastPage", lastPage);
		
		// 이전 페이지 정보
		HttpSession session = request.getSession();
		session.setAttribute("backPage", page);
		session.setAttribute("backKeyword", null);
		
		
		RequestDispatcher dis = request.getRequestDispatcher("product/list.jsp");
		dis.forward(request, response);
	}

}
