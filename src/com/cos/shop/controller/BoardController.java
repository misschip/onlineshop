package com.cos.shop.controller;


import javax.servlet.annotation.WebServlet;

import com.cos.shop.action.Action;
import com.cos.shop.action.board.ListProductAction;
import com.cos.shop.action.board.BoardHomeAction;

@WebServlet("/board")
public class BoardController extends AbstractController {

	@Override
	Action router(String cmd) {
		if (cmd.equals("listProd")) {
			return new ListProductAction();
		} else if (cmd.equals("home")) {
			return new BoardHomeAction();
		}
		return null;
	}

}
