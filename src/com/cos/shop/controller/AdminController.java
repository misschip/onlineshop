package com.cos.shop.controller;

import javax.servlet.annotation.WebServlet;

import com.cos.shop.action.Action;
import com.cos.shop.action.admin.AdminCategoryAction;
import com.cos.shop.action.admin.AdminCustomerAction;
import com.cos.shop.action.admin.AdminLoginProcAction;
import com.cos.shop.action.admin.AdminLogoutProcAction;
import com.cos.shop.action.admin.AdminManagerAction;
import com.cos.shop.action.admin.AdminOrdersAction;
import com.cos.shop.action.admin.AdminProductAction;
import com.cos.shop.action.admin.AdminRegisterProductAction;

@WebServlet("/admin/adm")
public class AdminController extends AbstractController {

	@Override
	Action router(String cmd) {
		System.out.println("AdminController : router() : cmd :" + cmd);
		
		if (cmd.equals("listCustomer")) {
			return new AdminCustomerAction();
		} else if (cmd.equals("listManager")) {
			return new AdminManagerAction();
		} else if (cmd.equals("listProduct")) {
			return new AdminProductAction();
		} else if (cmd.equals("listCategory")) {
			return new AdminCategoryAction();
		} else if (cmd.equals("listOrders")) {
			return new AdminOrdersAction();
		} else if (cmd.equals("loginProc")) {
			return new AdminLoginProcAction();
		} else if (cmd.equals("logoutProc")) {
			return new AdminLogoutProcAction();
		} else if (cmd.equals("registerProduct")) {
			return new AdminRegisterProductAction();
		}
		
		return null;
	}

}
