package com.cos.shop.controller;

import javax.servlet.annotation.WebServlet;

import com.cos.shop.action.Action;
import com.cos.shop.action.board.ListProductAction;

@WebServlet("/board")
public class BoardController extends AbstractController {

	@Override
	Action router(String cmd) {
		if (cmd.equals("listProd")) {
			return new ListProductAction();
		}
		return null;
	}

}
