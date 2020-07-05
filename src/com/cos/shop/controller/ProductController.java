package com.cos.shop.controller;


import javax.servlet.annotation.WebServlet;

import com.cos.shop.action.Action;
import com.cos.shop.action.product.ProductSearchAction;
import com.cos.shop.action.product.ProductByCategoryAction;
import com.cos.shop.action.product.ProductDetailAction;
import com.cos.shop.action.product.ProductHomeAction;

// @WebServlet(urlPatterns = {"/product", "/product/"})
@WebServlet("/product")
public class ProductController extends AbstractController {

	@Override
	Action router(String cmd) {
		if (cmd.equals("home")) {
			return new ProductHomeAction();
		} else if (cmd.equals("detail")) {
			return new ProductDetailAction();
		} else if (cmd.equals("listByCate")) {
			return new ProductByCategoryAction();
		} else if (cmd.equals("search")) {
			return new ProductSearchAction();
		}
		return null;
	}

}
