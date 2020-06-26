package com.cos.shop.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
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
			
			Script.href("로그인 성공", "home.jsp", response);
		} else {
			Script.back("관리자 로그인 실패", response);
		}

	}

}
