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
import com.cos.shop.util.Script;

public class ProductSearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		
		if (keyword == null || keyword.equals("")	) {
				Script.back("검색 키워드가 없습니다", response);
				return;
			}
		
		System.out.println("ProductSearchAction : keyword : " + keyword);
		
		int page = Integer.parseInt(request.getParameter("page"));
		
		
		ProductRepository productRepository = ProductRepository.getInstance();
		List<Product> products = productRepository.findByKeyword(page, keyword);
		
		request.setAttribute("products", products);
		
		
		int total = productRepository.countByKeyword(keyword);
		int lastPage = (total-1)/9;		// 한 페이지에 9개씩의 상품을 표출
		
		request.setAttribute("lastPage", lastPage);
		
		// 이전 페이지 정보
		HttpSession session = request.getSession();
		session.setAttribute("backPage", page);
		session.setAttribute("backKeyword", keyword);
		
		
		RequestDispatcher dis = request.getRequestDispatcher("product/list.jsp");
		dis.forward(request, response);

	}

}
