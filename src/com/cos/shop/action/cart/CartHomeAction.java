package com.cos.shop.action.cart;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.shop.action.Action;
import com.cos.shop.model.Product;

public class CartHomeAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("CartHomeAction : execute() 실행");
		RequestDispatcher dis = request.getRequestDispatcher("cart/home.jsp");
		dis.forward(request, response);
		
	}
}
