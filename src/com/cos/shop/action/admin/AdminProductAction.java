package com.cos.shop.action.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.shop.action.Action;
import com.cos.shop.dto.ProductResponseDto;
import com.cos.shop.repository.ProductRepository;

public class AdminProductAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductRepository productRepository = ProductRepository.getInstance();
		List<ProductResponseDto> productDtos = productRepository.findAll();

		request.setAttribute("productDtos", productDtos);
		
		RequestDispatcher dis = request.getRequestDispatcher("product.jsp");
		dis.forward(request, response);

	}

}
