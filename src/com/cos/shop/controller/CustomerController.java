package com.cos.shop.controller;


import javax.servlet.annotation.WebServlet;

import com.cos.shop.action.Action;
import com.cos.shop.action.customer.CustomerLoginAction;
import com.cos.shop.action.customer.CustomerLoginProcAction;
import com.cos.shop.action.customer.CustomerUsernameCheckAction;

@WebServlet("/customer")
public class CustomerController extends AbstractController {

	@Override
	Action router(String cmd) {
		System.out.println(TAG + "router() : cmd :" + cmd);

		if (cmd.equals("login")) {
			return new CustomerLoginAction();
		} else if (cmd.equals("loginProc")) {
			return new CustomerLoginProcAction();
		} else if (cmd.equals("usernameCheck")) {
			return new CustomerUsernameCheckAction();
		} 
		
		return null;
	}

}
