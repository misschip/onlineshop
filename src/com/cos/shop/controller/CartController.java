package com.cos.shop.controller;

import javax.servlet.annotation.WebServlet;

import com.cos.shop.action.Action;
import com.cos.shop.action.cart.CartAddAction;
import com.cos.shop.action.cart.CartDisplayAction;
import com.cos.shop.action.cart.CartRemoveAction;

@WebServlet("/cart")
public class CartController extends AbstractController {

	@Override
	Action router(String cmd) {
		if (cmd.equals("add")) {
			return new CartAddAction();
		} else if (cmd.equals("remove")) {
			return new CartRemoveAction();
		} else if (cmd.equals("display")) {
			return new CartDisplayAction();
		}
			
		return null;
	}

}
