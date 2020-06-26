<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Map"%>
<%@page import="com.cos.shop.util.MenuUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    

<%-- 자바의 Map에 든 객체를 Gson으로 Json으로 변경 후
	EL언어를 사용하여 같은 페이지 상의 자바스크립트 코드로 넘겨보는 테스트임
	=> 동일 페이지 내이지만 객체를 request에 저장하고 <script> 태그 부분 안에서 EL언어로 불러오는 과정이 필요함
	
	추가 : EL 언어로 Map 안의 Map의 key/value를 출력해 보는 테스트
 --%>

<%
	Map<String,Map<String,Integer>> menus = MenuUtil.prepareMenuMap();
	Gson gson = new Gson();
	String menusJson = gson.toJson(menus);
	
	request.setAttribute("menusJson",menusJson);
	request.setAttribute("menus", menus);
	
	out.println(menusJson);
%>

<script>
	// console.log(${menusJson});
	// 또는
	var jsonStr = ${menusJson};
	console.log(jsonStr);
</script>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<hr/>
	<c:forEach var="rootCategory" items="${menus}">
		<h5>${rootCategory.key}</h5>
	</c:forEach>
	
	<hr/>
	
	<c:forEach var="rootCategory" items="${menus}">
		<h5>${rootCategory}</h5>
	</c:forEach>
	
	<hr/>
	
	<c:forEach var="rootCategory" items="${menus}">
		<h5>${rootCategory.value}</h5>
	</c:forEach>
	
	<hr/>
	
	<c:forEach var="rootCategory" items="${menus}">
		<c:forEach var="subCategory" items="${rootCategory.value}">
			<h5>${subCategory.key} &nbsp;: &nbsp; ${subCategory.value}</h5>
		</c:forEach>
	</c:forEach>
</body>
</html>