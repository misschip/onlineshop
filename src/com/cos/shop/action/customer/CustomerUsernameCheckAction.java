package com.cos.shop.action.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.shop.action.Action;
import com.cos.shop.model.Customer;
import com.cos.shop.repository.CustomerRepository;
import com.cos.shop.util.Script;

public class CustomerUsernameCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		
		if (username == null || username.equals("")) {
			Script.outText("-1",response);
		}
		
		CustomerRepository customerRepository = CustomerRepository.getInstance();
		Customer customer = customerRepository.findByUsername(username);
		
		if (customer == null) {	// 해당 username의 사용자가 없는 경우 : username 사용가능
			Script.outText("0",response);
		} else {				// 이미 해당 username 사용자가 있는 경우
			Script.outText("1", response);
		}
		
	}

}
