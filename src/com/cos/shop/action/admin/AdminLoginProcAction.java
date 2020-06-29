package com.cos.shop.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.shop.action.Action;
import com.cos.shop.model.Manager;
import com.cos.shop.repository.ManagerRepository;
import com.cos.shop.util.Script;

public class AdminLoginProcAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (username == null || username.equals("") ||
			password == null || password.equals("")) {
			return;
		}
		
		ManagerRepository managerRepository = ManagerRepository.getInstance();
		Manager manager = managerRepository.findByUsernameAndPassword(username, password);
		
		if (manager != null) {
			HttpSession session = request.getSession();
			session.setAttribute("manager", manager);
			
			// setMaxAge() 설정을 하지 않으면 아이디 기억하기를 체크해 두더라도 웹브라우저가 켜져있는 동안에만 효과가 있고 브라우저가 종료되고
			// 다시 시작하면 기억하지 못한다. Cookie에 maxAge 설정 않을 시에는 웹브라우저 종료시 삭제(기본값)되기 때문!
			// cookie.setMaxAge(10 * 365 * 24 * 60 * 60) 하면 10년 후 만료
			if (request.getParameter("remember") != null) {
				Cookie cookie = new Cookie("rememberManager", manager.getUsername());
				cookie.setMaxAge(7*24*60*60); // 1주일 후 만료되는 쿠키로 설정
				response.addCookie(cookie);
				
				// 위 두줄 코드는 아래 한줄 코드와 동일한 효과. 위 방식이 정석임
				// response.addHeader("Set-Cookie", "rememberManager=admin2");
				
			} else {
				Cookie cookie = new Cookie("rememberManager", "");
				cookie.setMaxAge(0);	// 0 : 쿠키를 즉시 삭제. -1: 웹브라우저 종료시 삭제(default)
				response.addCookie(cookie);
			}
			
			Script.href("로그인 성공", "home.jsp", response);
		} else {
			Script.back("관리자 로그인 실패", response);
		}
		
	}

}
