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
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

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
		
		// 
		Type token = TypeToken.getParameterized(Map.class, Integer.class,Integer.class).getType();
		Map<Integer,Integer> carts = gson.fromJson(sb.toString(), token);
		
		System.out.println("CartSaveAction : carts.size() :" + carts.size());
		System.out.println(carts.toString());
			

		Set<Map.Entry<Integer,Integer>> entrySet = carts.entrySet();
		Iterator<Map.Entry<Integer,Integer>> iterator = entrySet.iterator();
		
		ProductRepository productRepository = ProductRepository.getInstance();
		Map<Product,Integer> cart = new HashMap<>();
		
		while(iterator.hasNext()) {
			Map.Entry<Integer,Integer> entry = iterator.next();
			int pid = entry.getKey();
			int count = entry.getValue();
			
			Product product = productRepository.findById(pid);
			cart.put(product, count);
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("cart", cart);
		
		Script.outText("1", response);
	}

}
