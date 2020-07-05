package com.cos.shop.action.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.shop.action.Action;
import com.cos.shop.model.Customer;
import com.cos.shop.repository.CustomerRepository;
import com.cos.shop.util.Script;

public class CustomerUpdateProcAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("principal") == null) {
			Script.getMessage("잘못된 접근입니다.", response);
			return;
		}
		
		String idStr = request.getParameter("id");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String zipnoStr = request.getParameter("zipno");


		if
		(
		
				idStr == null || idStr.equals("") ||
				password == null || password.equals("") ||
				email == null || email.equals("") ||
				phone == null || phone.equals("") ||
				address == null || address.equals("") ||
				zipnoStr == null || zipnoStr.equals("")
		) {
			Script.back("입력되지 않은 필드가 있습니다.", response);
			return;
		}
		

		int id = Integer.parseInt(idStr);
		int zipno = Integer.parseInt(zipnoStr);

		Customer customer = Customer.builder()
				.id(id)
				.password(password)
				.email(email)
				.phone(phone)
				.address(address)
				.zipNo(zipno)
				.build();
		
		CustomerRepository customerRepository = CustomerRepository.getInstance();
		int result = customerRepository.update(customer);

		// update가 성공적으로 이루어지면 세션이 재등록한다.
		if(result == 1) {
			Customer principal = customerRepository.findById(id);
			System.out.println("UsersUpdateProc : username : "+principal.getUsername());
			session.setAttribute("principal", principal);

			Script.href("회원수정 성공", "/onlineshop/index.jsp", response);
		}else {
			Script.back("회원수정 실패", response);
		}		

	}

}
