package com.cos.shop.action.cart;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.shop.action.Action;
import com.cos.shop.model.Product;
import com.cos.shop.repository.ProductRepository;
import com.cos.shop.util.Script;
import com.google.gson.Gson;

public class CartSaveAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader br = request.getReader();
		StringBuffer sb = new StringBuffer();
		String input = null;
		while((input = br.readLine()) != null) {
			sb.append(input);
		}
		
		Gson gson = new Gson();
		
		// Map<Integer,Integer> : Map<Product id,카트에 선택된 개수>
		Map<String,String> carts = gson.fromJson(sb.toString(), Map.class);
		// System.out.println("CartSaveAction : carts.size() :" + carts.size());
		// System.out.println("CartSaveAction : carts.keySet() :" + carts.keySet());
		// Map<Integer,Integer> 타입이어야 하는데 일단 Gson으로는 Map<String,String>으로 받아오는데 성공함
		
		Set<Map.Entry<String,String>> entrySet = carts.entrySet();
		Iterator<Map.Entry<String,String>> iterator = entrySet.iterator();
		
		ProductRepository productRepository = ProductRepository.getInstance();
		Map<Product,Integer> cart = new HashMap<>();
		
		while(iterator.hasNext()) {
			Map.Entry<String,String> entry = iterator.next();
			int pid = Integer.parseInt(entry.getKey());
			int count = Integer.parseInt(entry.getValue());
			
			Product product = productRepository.findById(pid);
			cart.put(product, count);
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("cart", cart);
		
		Script.outText("1", response);
	}

}
