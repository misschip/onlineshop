package com.cos.shop.action.cart;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.shop.action.Action;
import com.cos.shop.model.Product;
import com.cos.shop.repository.ProductRepository;

public class CartRemoveAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		
		if (idStr == null || idStr.equals("")) {
			System.out.println("CartRemoveAction : id값을 받아오지 못함 : " + idStr);
			return;
		}
		
		int id = Integer.parseInt(idStr);
		
		ProductRepository productRepository = ProductRepository.getInstance();
		Product product = productRepository.findById(id);
		
		if (product == null) {
			System.out.println("해당 id로 product를 찾을 수 없습니다");
			return;
		}
		
		HttpSession session = request.getSession();
		Map<Product,Integer> cart = (Map<Product,Integer>)session.getAttribute("cart");
		cart.remove(product);
		
		session.setAttribute("cart", cart);
		
		response.sendRedirect("/onlineshop/cart?cmd=display");
	}

}
