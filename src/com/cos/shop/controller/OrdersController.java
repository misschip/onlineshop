package com.cos.shop.controller;

import javax.servlet.annotation.WebServlet;

import com.cos.shop.action.Action;
import com.cos.shop.action.orders.OrdersSaveOrderAction;

@WebServlet("/orders")
public class OrdersController extends AbstractController {

	@Override
	Action router(String cmd) {
		if (cmd.equals("saveOrder")) {
			return new OrdersSaveOrderAction();	
		}
		return null;
	}

}
