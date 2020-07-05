package com.cos.shop.action.customer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.shop.action.Action;
import com.cos.shop.model.Customer;
import com.cos.shop.repository.CustomerRepository;

import com.cos.shop.util.Script;



public class CustomerJoinProcAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 유효성 검사
		if
		(
				request.getParameter("username") == null ||
				request.getParameter("username").equals("") ||
				request.getParameter("password") == null ||
				request.getParameter("password").equals("") ||
				request.getParameter("phone") == null ||
				request.getParameter("phone").equals("") ||
				request.getParameter("email") == null ||
				request.getParameter("email").equals("") ||
				request.getParameter("address") == null ||
				request.getParameter("address").equals("") ||
				request.getParameter("zipno") == null ||
				request.getParameter("zipno").equals("")
				) {
			// request로부터 상대방의 ip 주소등을 로그로 남기는게 좋다고 함 (공격자)
			return;
		}
		
		// 1. 파라메터 받기 (x-www-form-urlencoded 라는 MIME 타입. key=value)
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// String rawPassword = request.getParameter("password");		
		// String password = SHA256.encodeSha256(rawPassword);
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String zipNo = request.getParameter("zipno");
		
		
		// 2. User 오브젝트 변환
		Customer customer = Customer.builder()
				.username(username)
				.password(password)
				.phone(phone)
				.email(email)
				.address(address)
				.zipNo(Integer.parseInt(zipNo))
				.build();
		
		// 3. DB 연결 - CustomerRepository의 save() 호출
		CustomerRepository customerRepository = CustomerRepository.getInstance();
		int  result = customerRepository.save(customer);
		
		// 4. index.jsp 페이지로 이동
		if (result == 1) {
			
//			RequestDispatcher dis = request.getRequestDispatcher("user/login.jsp");
//			dis.forward(request, response);
			// request.getRequestDispatcher("/onlineshop/customer?cmd=login")로 할 필요는 없음
			// uri 사용하면 폴더 구조 노출되지 않으므로 굳이 RequestDispatcher 사용할 필요가 없음
			
			// 무조건 Controller를 타는 주소로 보낼 것! cmd=xxx
			// 방법 1		- 잘 안 쓰이는 방식	- 이 방식으로도 uri 사용하므로 직접 폴더 구조가 노출되지는 않음
			// response.sendRedirect("/onlineshop/customer?cmd=login");
			// 방법 2		- 방법1보다 좋은 점. 메세지창을 띄울 수 있음
			Script.href("회원가입에 성공했습니다", "/onlineshop/customer?cmd=login", response);
			
		} else {
			Script.back("회원가입에 실패하였습니다", response);
		}
	}
}
