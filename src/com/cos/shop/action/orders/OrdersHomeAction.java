package com.cos.shop.action.orders;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.shop.action.Action;
import com.cos.shop.dto.OrdersResponseDto;
import com.cos.shop.model.Customer;
import com.cos.shop.service.OrdersService;
import com.cos.shop.util.Script;

public class OrdersHomeAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("principal") == null) {
			Script.getMessage("잘못된 접근입니다.", response);
			return;
		}

		Customer customer = (Customer) session.getAttribute("principal");
		int customer_id = customer.getId();
		
		OrdersService ordersService = OrdersService.getInstance();
		List<OrdersResponseDto> ordersDtos = ordersService.ordersDtosByCustomer(customer_id);
		
		request.setAttribute("ordersDtos", ordersDtos);
		
		RequestDispatcher dis = request.getRequestDispatcher("orders/home.jsp");
		dis.forward(request, response);
	}

}
