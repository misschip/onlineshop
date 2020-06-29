<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
<div class="container">
	<div class="row">
	  <div class="col-sm-4">
	  	
	  </div>
	  <div class="col-sm-4">
	  </div>
	  <div class="col-sm-4">
	  </div>
	</div>

	<c:set var="size" value="${products.size()}"/>
	
	<c:forEach var="product" items="${products}" varStatus="status">
		<c:if test="${status.index % 3 == 0}">
			<div class="row">
		</c:if>
		
		<%-- 카드 레이아웃 1개 시작 --%>
		<div class="card" style="width:400px">
  			<img class="card-img-top" src="${product.image1}" alt="${product.name}">
  			<div class="card-body">
    			<h4 class="card-title">${product.name}</h4>
    			<p class="card-text">${product.description}</p>
    			<%-- <p class="card-text">${product.price}원</p> --%>
    			<%-- <a href="#" class="btn btn-primary">See Profile</a>  --%>
  			</div>
		</div>
		<%-- 카드 레이아웃 1개 끝 --%>
		
		
		<c:if test="${status.count % 3 == 0}">
			</div>
		</c:if>
		<%-- 아래 한줄 코드는 상품수가 3개 단위로 딱 떨어지지 않을 때 맨 마지막에 </div> 태그를 붙이는 기능 --%>
		<c:if test="${size%3!=0 && size-count<=3 && size%3 == status.count}"></div></c:if>
		
		
	</c:forEach>

</div>