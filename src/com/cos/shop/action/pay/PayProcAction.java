package com.cos.shop.action.pay;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.shop.action.Action;

public class PayProcAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String totalStr = request.getParameter("total");
		if (totalStr == null || totalStr.equals("")) {
			System.out.println("PayProcAction : total : " + totalStr);
		}
		
		int total = Integer.parseInt(totalStr);
		
		request.setAttribute("total", total);
		
		RequestDispatcher dis = request.getRequestDispatcher("/onlineshop/pay/payForm.jsp");
		dis.forward(request, response);
		// response.sendRedirect("/onlineshop/pay/payForm.jsp");
	}

}
