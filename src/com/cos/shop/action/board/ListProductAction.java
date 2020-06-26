package com.cos.shop.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.shop.action.Action;
import com.cos.shop.model.Product;
import com.cos.shop.repository.ProductRepository;

public class ListProductAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductRepository productRepository = ProductRepository.getInstance();
		List<Product> products = productRepository.findAll();

	}

}
