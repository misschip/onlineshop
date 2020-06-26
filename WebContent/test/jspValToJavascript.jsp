<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Map"%>
<%@page import="com.cos.shop.util.MenuUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    

<%-- 자바의 Map에 든 객체를 Gson으로 Json으로 변경 후
	EL언어를 사용하여 같은 페이지 상의 자바스크립트 코드로 넘겨보는 테스트임
 --%>

<%
	Map<String,Map<String,Integer>> menus = MenuUtil.prepareMenuMap();
	Gson gson = new Gson();
	String menusJson = gson.toJson(menus);
	request.setAttribute("menusJson",menusJson);
%>

<script>
	console.log(${menusJson});
</script>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>