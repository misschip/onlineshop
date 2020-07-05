<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

</head>
<body>

	<%-- 어떤 이유로든 관리자 로그인이 풀리면 다시 로그인 페이지로 보냄 --%>
	<c:if test="${empty manager}">
		<c:redirect url="/admin/login.jsp" />
	</c:if>


<div class="container">
	
		<div align="center">
			<img src="/onlineshop/images/companyLogo.png" class="img-thumbnail" alt="Cinque Terre">
		</div>
		
		<div align="center">
			<hr width="60%" />
		</div>
		
		<h5 align="center">${manager.username}(${manager.email})님 환영합니다. &nbsp;&nbsp;&nbsp;&nbsp;
			<span class="badge badge-pill badge-danger">
				<a href="/onlineshop/admin/adm?cmd=logoutProc">로그아웃</a>
			</span>
		</h5>
		<br>
		<h3 align="center">
			<span class="badge badge-pill badge-warning">
				<a href="/onlineshop/admin/adm?cmd=listCustomer">회원관리</a>
			</span>
			<span class="badge badge-pill badge-warning">
				<a href="/onlineshop/admin/adm?cmd=listManager">매니저관리</a>
			</span>
			<span class="badge badge-pill badge-warning">
				<a href="/onlineshop/admin/adm?cmd=listProduct">상품관리</a>
			</span>
			<span class="badge badge-pill badge-warning">
				<a href="/onlineshop/admin/adm?cmd=listCategory">카테고리관리</a>
			</span>
			<span class="badge badge-pill badge-warning">
				<a href="/onlineshop/admin/adm?cmd=listOrders">주문관리</a>
			</span>
			<span class="badge badge-pill badge-warning">
				<a href="">리뷰관리</a>
			</span>
		
		</h3>

</div>
