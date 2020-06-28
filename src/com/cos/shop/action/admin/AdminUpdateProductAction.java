package com.cos.shop.action.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.shop.action.Action;
import com.cos.shop.model.Product;
import com.cos.shop.repository.ProductRepository;
import com.cos.shop.util.Script;

public class AdminUpdateProductAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		
		if (idStr == null || idStr.equals("")) {
			Script.back("잘못된 접근입니다", response);
			return;
		}
		
		int id = Integer.parseInt(idStr);
		
		ProductRepository productRepository = ProductRepository.getInstance();
		Product product = productRepository.findById(id);
		
		request.setAttribute("product", product);
		
		RequestDispatcher dis = request.getRequestDispatcher("updateProduct.jsp");
		dis.forward(request, response);

	}

}
