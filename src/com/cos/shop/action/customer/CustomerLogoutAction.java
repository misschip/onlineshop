package com.cos.shop.action.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.shop.util.Script;
import com.cos.shop.action.Action;

public class CustomerLogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();	// 세션을 무효화
		// session.removeAttribute("principal");	// invalidate() 대신 이것만 하는 방법도 가능은 하지만 잘 안씀

		Script.href("로그아웃 성공", "index.jsp", response);

	}

}
