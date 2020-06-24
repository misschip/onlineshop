<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<div class="container">
	
		<div align="center">
			<img src="/onlineshop/images/companyLogo.png" class="img-thumbnail" alt="Cinque Terre">
		</div>
		
		<div align="center">
			<hr width="60%" />
		</div>
		
		<h5 align="center">${admin.username}(${admin.email})님 환영합니다. &nbsp;&nbsp;&nbsp;&nbsp;
			<span class="badge badge-pill badge-danger">
				<a href="">로그아웃</a>
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
