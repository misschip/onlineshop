package com.cos.shop.controller;

import javax.servlet.annotation.WebServlet;

import com.cos.shop.action.Action;
import com.cos.shop.action.orders.OrdersHomeAction;
import com.cos.shop.action.orders.OrdersSaveOrderAction;
import com.cos.shop.action.orders.OrdersSaveResultAction;

@WebServlet("/orders")
public class OrdersController extends AbstractController {

	@Override
	Action router(String cmd) {
		if (cmd.equals("saveOrder")) {
			return new OrdersSaveOrderAction();	
		} else if (cmd.equals("saveResult")) {
			return new OrdersSaveResultAction();	
		} else if (cmd.equals("home")) {
			return new OrdersHomeAction();	
		}
		
		return null;
	}

}
