package com.cos.shop.controller;

import javax.servlet.annotation.WebServlet;

import com.cos.shop.action.Action;
import com.cos.shop.action.admin.ListCategoryAction;
import com.cos.shop.action.admin.ListCustomerAction;
import com.cos.shop.action.admin.ListManagerAction;
import com.cos.shop.action.admin.ListOrdersAction;
import com.cos.shop.action.admin.ListProductAction;
import com.cos.shop.action.admin.LoginProcAction;
import com.cos.shop.action.admin.LogoutProcAction;

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
		} else if (cmd.equals("listCategory")) {
			return new ListCategoryAction();
		} else if (cmd.equals("listOrders")) {
			return new ListOrdersAction();
		} else if (cmd.equals("loginProc")) {
			return new LoginProcAction();
		} else if (cmd.equals("logoutProc")) {
			return new LogoutProcAction();
		}
		
		return null;
	}

}
