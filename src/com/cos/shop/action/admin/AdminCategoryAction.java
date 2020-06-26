package com.cos.shop.action.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.shop.action.Action;
import com.cos.shop.model.Category;
import com.cos.shop.repository.CategoryRepository;

public class AdminCategoryAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryRepository categoryRepository = CategoryRepository.getInstance();
		List<Category> categories = categoryRepository.findAll();

		request.setAttribute("categories", categories);
		
		RequestDispatcher dis = request.getRequestDispatcher("category.jsp");
		dis.forward(request, response);

	}

}
