package com.cos.shop.action.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.shop.action.Action;
import com.cos.shop.model.Product;
import com.cos.shop.repository.ProductRepository;

public class BoardDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		
		if (idStr == null || idStr.equals("")) {
			System.out.println("BoardDetailAction : idStr이 null 또는 빈 문자열임 :" + idStr);
			return;
		}
		
		int id = Integer.parseInt(idStr);
		
		ProductRepository productRepository = ProductRepository.getInstance();
		Product product = productRepository.findById(id);
		
		request.setAttribute("product", product);
		
		RequestDispatcher dis = request.getRequestDispatcher("detail.jsp");
		dis.forward(request, response);

	}

}
