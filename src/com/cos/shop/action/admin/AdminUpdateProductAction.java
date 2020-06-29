package com.cos.shop.action.admin;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.shop.action.Action;
import com.cos.shop.model.Category;
import com.cos.shop.model.Product;
import com.cos.shop.repository.CategoryRepository;
import com.cos.shop.repository.ProductRepository;
import com.cos.shop.util.MenuUtil;
import com.cos.shop.util.Script;
import com.google.gson.Gson;

public class AdminUpdateProductAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		
		if (idStr == null || idStr.equals("")) {
			Script.back("잘못된 접근입니다", response);
			return;
		}
		
		int id = Integer.parseInt(idStr);
		
		ProductRepository productRepository = ProductRepository.getInstance();
		Product product = productRepository.findById(id);
		
		System.out.println("AdminUpdateProductAction : product : " + product);
		
		request.setAttribute("product", product);
		
		System.out.println("AdminUpdateProductAction : product.getCategory_id() : " + product.getCategory_id());
		
		CategoryRepository categoryRepository = CategoryRepository.getInstance();
		Category selectedCategory = categoryRepository.findById(product.getCategory_id());
		
		request.setAttribute("selectedCategory", selectedCategory);
		
		// Map<대분류,Map<소분류,카테고리id>>
		Map<String,Map<String,Integer>> menus = MenuUtil.prepareMenuMap();
		Gson gson = new Gson();
		String menusJson = gson.toJson(menus);
		request.setAttribute("menusJson",menusJson);
		request.setAttribute("menus",menus);
		
		
		RequestDispatcher dis = request.getRequestDispatcher("updateProduct.jsp");
		dis.forward(request, response);

	}

}
