package com.cos.shop.action.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.shop.action.Action;
import com.cos.shop.model.Manager;
import com.cos.shop.repository.ManagerRepository;

public class AdminManagerAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManagerRepository managerRepository = ManagerRepository.getInstance();
		List<Manager> managers = managerRepository.findAll();

		request.setAttribute("managers", managers);
		
		RequestDispatcher dis = request.getRequestDispatcher("manager.jsp");
		dis.forward(request, response);

	}

}
