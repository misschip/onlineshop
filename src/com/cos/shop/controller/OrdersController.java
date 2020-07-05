package com.cos.shop.controller;

import javax.servlet.annotation.WebServlet;

import com.cos.shop.action.Action;

@WebServlet("/orders")
public class OrdersController extends AbstractController {

	@Override
	Action router(String cmd) {
		
		return null;
	}

}
