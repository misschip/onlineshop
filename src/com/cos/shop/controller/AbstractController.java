package com.cos.shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.shop.action.Action;




public abstract class AbstractController extends HttpServlet {
	// private static final String TAG = "AbstractController : ";
	protected String TAG;
	
	public void init() {
		TAG = this.getClass().getName() + " : ";
		//System.out.println("AbstractController init() : " + TAG);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String cmd = request.getParameter("cmd");
		
		System.out.println(TAG + "doProcess : " + cmd);
		Action action = router(cmd); 
		action.execute(request, response);
	}
	
	abstract Action router(String cmd);
}
