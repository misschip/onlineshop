<%@page import="com.google.gson.Gson"%>
<%@ page import="com.cos.shop.model.Manager" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	Manager manager = Manager.builder()
			.id(15).username("홍길동").email("hong@nate.com")
			.phone("010-2222-3333").build();

	Gson gson = new Gson();
	String managerJson = gson.toJson(manager);
	
	request.setAttribute("manager",manager);
	request.setAttribute("managerJson",managerJson);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script>
	console.log("${manager}");		// manager.toString()의 작용
	console.log('${managerJson}');	// json
</script>

</body>
</html>