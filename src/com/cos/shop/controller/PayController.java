package com.cos.shop.controller;

import javax.servlet.annotation.WebServlet;

import com.cos.shop.action.Action;
import com.cos.shop.action.pay.PayHomeAction;
import com.cos.shop.action.pay.PayProcAction;

@WebServlet("/pay")
public class PayController extends AbstractController {

	@Override
	Action router(String cmd) {
		System.out.println(TAG + " cmd : " + cmd);
		if (cmd.equals("home")) {
			return new PayHomeAction();
		} else if (cmd.equals("payProc")) {
			return new PayProcAction();
		}
		return null;
	}

}
