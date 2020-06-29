package com.cos.shop.controller;


import javax.servlet.annotation.WebServlet;

import com.cos.shop.action.Action;
import com.cos.shop.action.board.BoardDetailAction;
import com.cos.shop.action.board.BoardHomeAction;
import com.cos.shop.action.board.ListProductAction;

@WebServlet("/board")
public class BoardController extends AbstractController {

	@Override
	Action router(String cmd) {
		if (cmd.equals("listProd")) {
			return new ListProductAction();
		} else if (cmd.equals("home")) {
			return new BoardHomeAction();
		} else if (cmd.equals("detail")) {
			return new BoardDetailAction();
		}
		return null;
	}

}
