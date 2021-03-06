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
import com.cos.shop.util.TextParser;

public class ProductHomeAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		
		ProductRepository productRepository = ProductRepository.getInstance();
		List<Product> products = productRepository.findAll(page);

		// 상품 설명이 길 경우에 상품 리스팅에 표출될 부분에 맞추기 위해 description 부분을 정리함
		for (Product product : products) {
			String preview = TextParser.getTextPreview(product.getDescription(), 50);
			product.setDescription(preview);
		}
		
		// request에 저장
		request.setAttribute("products", products);
		
		int total = productRepository.count();
		int lastPage = (total-1)/9;		// 한 페이지에 9개씩의 상품을 표출
		
		request.setAttribute("lastPage", lastPage);
		
		// 이전 페이지 정보
		HttpSession session = request.getSession();
		session.setAttribute("backPage", page);
		session.setAttribute("backKeyword", null);
		
		
		RequestDispatcher dis = request.getRequestDispatcher("home.jsp");
		dis.forward(request, response);
	}

}
