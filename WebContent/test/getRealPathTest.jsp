<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%

System.out.println("index.jsp : getRealPath('index.jsp') : " + request.getServletContext().getRealPath("index.jsp"));
System.out.println("index.jsp : getRealPath('login.jsp') : " + request.getServletContext().getRealPath("login.jsp"));
System.out.println("index.jsp : getRealPath('home.jsp') : " + request.getServletContext().getRealPath("home.jsp"));
System.out.println("index.jsp : getRealPath('header.jsp') : " + request.getServletContext().getRealPath("header.jsp"));


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>