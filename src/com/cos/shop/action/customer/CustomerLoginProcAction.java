package com.cos.shop.action.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.shop.action.Action;
import com.cos.shop.model.Customer;
import com.cos.shop.repository.CustomerRepository;
import com.cos.shop.util.Script;

public class CustomerLoginProcAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. Validation 체크
		if
		(
				request.getParameter("username") == null ||
				request.getParameter("username").equals("") ||
				request.getParameter("password") == null ||
				request.getParameter("password").equals("")
				) {
			// request로부터 상대방의 ip 주소등을 로그로 남기는게 좋다고 함 (공격자)
			return;
		}
		
		
		String username = request.getParameter("username");
		String rawPassword = request.getParameter("password");
		
		CustomerRepository customerRepository = CustomerRepository.getInstance();
		Customer customer = customerRepository.findByUsernameAndPassword(username, rawPassword);
		
		if (customer != null) {
			HttpSession session = request.getSession();
			session.setAttribute("principal", customer);
			
			// 로그인 화면에서 아이디 기억하기를 선택한 경우
			if (request.getParameter("remember") != null) {
				
				// setMaxAge()로 쿠키의 존속시간(초)을 지정하지 않은 경우에는
				// 웹브라우저가 종료되면 아이디 기억하기 효과가 사라짐
				// 쿠키의 존속시간을 지정한 경우에는 웹브라우저 종료와 무관하게
				// 해당 시간동안 아이디 기억하기 효과가 적용됨
				Cookie cookie = new Cookie("remember", customer.getUsername());
				cookie.setHttpOnly(true);
				response.addCookie(cookie);
			}
			
			Script.href("로그인 성공", "/onlineshop/product?cmd=home&page=0", response);
		} else {
			Script.back("로그인  실패", response);
		}

	}

}
