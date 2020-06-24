package com.cos.shop.action.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.shop.action.Action;
import com.cos.shop.model.Orders;
import com.cos.shop.repository.OrdersRepository;

public class ListOrdersAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrdersRepository ordersRepository = OrdersRepository.getInstance();
		List<Orders> orders = ordersRepository.findAll();

		request.setAttribute("orders", orders);
		
		RequestDispatcher dis = request.getRequestDispatcher("orders.jsp");
		dis.forward(request, response);

	}

}
