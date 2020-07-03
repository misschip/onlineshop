package com.cos.shop.controller;

import javax.servlet.annotation.WebServlet;

import com.cos.shop.action.Action;
import com.cos.shop.action.cart.CartAddAction;
import com.cos.shop.action.cart.CartHomeAction;

import com.cos.shop.action.cart.CartRemoveAction;
import com.cos.shop.action.cart.CartSaveAction;

@WebServlet("/cart")
public class CartController extends AbstractController {

	@Override
	Action router(String cmd) {
		System.out.println(TAG + "router() : cmd : " + cmd);
		
		if (cmd.equals("add")) {
			return new CartAddAction();
		} else if (cmd.equals("remove")) {
			return new CartRemoveAction();
		} else if (cmd.equals("display")) {
			return new CartHomeAction();
		} else if (cmd.equals("save")) {
			return new CartSaveAction();
		} else if (cmd.equals("home")) {
			return new CartHomeAction();
		}
		
		return null;
	}
}
