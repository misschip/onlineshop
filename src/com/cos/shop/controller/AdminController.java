package com.cos.shop.controller;

import javax.servlet.annotation.WebServlet;

import com.cos.shop.action.Action;
import com.cos.shop.action.admin.ListCustomerAction;
import com.cos.shop.action.admin.ListManagerAction;
import com.cos.shop.action.admin.ListProductAction;

@WebServlet("/admin/adm")
public class AdminController extends AbstractController {

	@Override
	Action router(String cmd) {
		System.out.println("AdminController : router() : cmd :" + cmd);
		
		if (cmd.equals("listCustomer")) {
			return new ListCustomerAction();
		} else if (cmd.equals("listManager")) {
			return new ListManagerAction();
		} else if (cmd.equals("listProduct")) {
			return new ListProductAction();
		} // else if (cmd.equals("listProduct"))
		
		return null;
	}

}
