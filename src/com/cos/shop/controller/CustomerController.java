package com.cos.shop.controller;


import javax.servlet.annotation.WebServlet;

import com.cos.shop.action.Action;
import com.cos.shop.action.customer.CustomerGetPrincipalAction;
import com.cos.shop.action.customer.CustomerJoinAction;
import com.cos.shop.action.customer.CustomerJoinProcAction;
import com.cos.shop.action.customer.CustomerLoginAction;
import com.cos.shop.action.customer.CustomerLoginProcAction;
import com.cos.shop.action.customer.CustomerLogoutAction;
import com.cos.shop.action.customer.CustomerUpdateAction;
import com.cos.shop.action.customer.CustomerUpdateProcAction;
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
		} else if (cmd.equals("joinProc")) {
			return new CustomerJoinProcAction();
		} else if (cmd.equals("join")) {
			return new CustomerJoinAction();
		} else if (cmd.equals("getPrincipal")) {
			return new CustomerGetPrincipalAction();
		} else if (cmd.equals("update")) {
			return new CustomerUpdateAction();
		} else if (cmd.equals("updateProc")) {
			return new CustomerUpdateProcAction();
		} else if (cmd.equals("logout")) {
			return new CustomerLogoutAction();
		}
		
		return null;
	}

}
