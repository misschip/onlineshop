<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE html>
<html lang="en">
<head>
  <title>온라인 쇼핑몰</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>


</head>
<body>

<nav class="navbar navbar-expand-sm bg-light navbar-light">
  <a class="navbar-brand" href="/onlineshop/index.jsp">Home</a>
  
<%--
  <form class="form-inline" action="/action_page.php">
    <input class="form-control mr-sm-2" type="text" placeholder="Search">
    <button class="btn btn-success" type="submit">Search</button>
  </form>
 --%>
  
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <%-- 아래 justify-content-between (justify-content-end, justify-content-around) 등으로 메뉴 배치 바꿈 --%>
  <div class="justify-content-end collapse navbar-collapse" id="collapsibleNavbar">
    
    <%-- 아래 form과 ul은 collapsibleNavbar 내에 대등한 요소로 배치됨 --%>
  	<form class="form-inline" action="product?cmd=search&page=0" method="post">
  		<%-- GET :action의 일부에 포함된 query string 날아감 (HTML5 스펙) --%>
  		<%-- POST :action의 일부에 포함된 query string 보존함 --%>
    	<input class="form-control mr-sm-2" type="text" name="keyword" placeholder="Search">
    	<button class="btn btn-success" type="submit">검 색</button>
  	</form>

    
    <ul class="navbar-nav">
    
    <c:choose>
    	<c:when test="${empty sessionScope.principal}">
    	  <li class="nav-item">
	        <a class="nav-link" href="/onlineshop/cart?cmd=home">장바구니</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="/onlineshop/customer?cmd=login">로그인</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="/onlineshop/customer?cmd=join">회원가입</a>
	      </li>
	    </c:when>

      	<c:otherwise>
      		<li class="nav-item">
	        <a class="nav-link" href="/onlineshop/cart?cmd=home">장바구니</a>
	      	</li>
	      	<li class="nav-item">
	        <a class="nav-link" href="/onlineshop/orders?cmd=home">내주문정보</a>
	      	</li>
      		<li class="nav-item">
	        <a class="nav-link" href="/onlineshop/customer?cmd=update">회원정보</a>
	      	</li>
	      	<li class="nav-item">
	        <a class="nav-link" href="/onlineshop/customer?cmd=logout">로그아웃</a>
      		</li>

    	
      	</c:otherwise>
      </c:choose>
    </ul>
    
    
  </div>  
</nav>
<br>