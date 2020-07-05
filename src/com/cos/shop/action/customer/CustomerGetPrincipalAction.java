package com.cos.shop.action.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.shop.action.Action;
import com.cos.shop.model.Customer;
import com.cos.shop.util.Script;
import com.google.gson.Gson;

public class CustomerGetPrincipalAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Customer c = (Customer)session.getAttribute("principal");
		
		// Customer c는,
		// 로그인 되어있는 경우 로그인 된 그 사용자(Customer) 객체,
		// 로그인 안되어 있는 경우 null을 가짐
		// toJson()은 매개값이 null일 경우 JsonNull로 변환하므로
		// 아래에서 null 체크를 따로 할 필요는 없음
		Gson gson = new Gson();
		String customerJson = gson.toJson(c);
		
		System.out.println("CustomerGetPrincipalAction : customerJson : " + customerJson);
		
		Script.outJson(customerJson , response);
		// Script.outText(customerJson, response);
	}

}
