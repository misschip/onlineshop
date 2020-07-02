<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	System.out.println("cart/index.jsp 실행");
	RequestDispatcher dis = request.getRequestDispatcher("/cart?cmd=home");
	dis.forward(request, response);
%>