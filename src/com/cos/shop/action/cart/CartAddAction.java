package com.cos.shop.action.cart;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.shop.action.Action;
import com.cos.shop.model.Product;
import com.cos.shop.repository.ProductRepository;

public class CartAddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CartAddAction : execute() 실행");
		
		String idStr = request.getParameter("id");
		String quantityStr = request.getParameter("quantity");
		
		if (idStr == null || idStr.equals("") ||
			quantityStr == null || quantityStr.equals("")) {
			
			System.out.println("CartAddAction : id : " + idStr + " / quantity : " + quantityStr);
			return;
		}
		
		int id = Integer.parseInt(idStr);
		int quantity = Integer.parseInt(quantityStr);
		
		ProductRepository productRepository = ProductRepository.getInstance();
		Product product = productRepository.findById(id);
		if (product==null) {
			System.out.println("CartAddAction : " + id + " : 해당 id로 존재하는 product가 없음");
			return;
		}
		
		HttpSession session = request.getSession();
		
		Map<Product,Integer> cart = (Map<Product,Integer>)session.getAttribute("cart");
		
		if (cart == null) {
			cart = new HashMap<>();
			cart.put(product,quantity);
			
		} else {
			
			if (cart.get(product) == null) {
				cart.put(product, quantity);
			} else {
				int count = cart.get(product);
				cart.put(product, count+quantity);
			}
			 
		}
		
		session.setAttribute("cart", cart);
		
		response.sendRedirect("/onlineshop/cart?cmd=display");

	}

}
