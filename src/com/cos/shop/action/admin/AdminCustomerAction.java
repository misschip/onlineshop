package com.cos.shop.action.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.shop.action.Action;
import com.cos.shop.model.Customer;
import com.cos.shop.repository.CustomerRepository;

public class AdminCustomerAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerRepository customerRepository = CustomerRepository.getInstance();
		List<Customer> customers = customerRepository.findAll();

		request.setAttribute("customers", customers);
		
		RequestDispatcher dis = request.getRequestDispatcher("customer.jsp");
		dis.forward(request, response);
	}

}
