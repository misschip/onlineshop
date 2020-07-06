package com.cos.shop.action.orders;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.shop.action.Action;
import com.cos.shop.util.Script;

public class OrdersSaveOrderAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// "imp_uid=" + rsp.imp_uid + "&merchant_uid=" + rsp.merchant_uid
		// "&paid_amount=" + rsp.paid_amount + "&apply_num=" + rsp.apply_num
		
		/*
		String imp_uid = request.getParameter("imp_uid");
		String merchant_uid = request.getParameter("merchant_uid");
		String paid_amount = request.getParameter("paid_amount");
		String apply_num = request.getParameter("apply_num");
		
		if (imp_uid == null || imp_uid.equals("") ||
			merchant_uid == null || merchant_uid.equals("") ||
			paid_amount == null || paid_amount.equals("") ||
			apply_num == null || apply_num.equals("")
				) {
			Script.getMessage("결제가 처리되지 못했습니다(필수 4개 파라메터를 받지 못함)", response);
		}
		*/
		
		
		

	}

}
