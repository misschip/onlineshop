<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%

System.out.println("index.jsp : 진입 확인");

%>

<c:out value="${sessionScope.manager}"></c:out>

<c:if test="${empty sessionScope.manager}">
	<c:redirect url="login.jsp" />
</c:if>

<c:if test="${not empty sessionScope.manager}">
	<%-- <c:redirect url="/adm?cmd=home" /> --%>
	<c:redirect url="home.jsp" />
</c:if>
