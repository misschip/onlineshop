<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<style>

/* 상품 리스팅 화면에서 이미지가 자동 크롭되도록 함 */
/* 참고 : https://stackoverflow.com/questions/12991351/css-force-image-resize-and-keep-aspect-ratio */
img {
  	object-fit: cover;
	width: 100%;
	height: 250px;
}

</style>    
    
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
		
		
		<%-- bootstrap의 card 클래스에는 class="card-deck" 또는 class="card-columns" 라는 클래스가 이미 있지만
			 화면 크기에 따라 너무 다이나믹하게 카드 크기가 바뀌어서 이 경우에는 잘 안 맞는 듯!
		 --%>
		
		<%-- 카드 레이아웃 카드 한 블록 시작 --%>
		<div class="card" style="width:350px; margin:10px;">
  			<img class="card-img-top" src="${product.image1}" alt="${product.name}">
  			<div class="card-body">
    			<h4 class="card-title">${product.name}</h4>
    			<p class="card-text">${product.description}</p>
    			<%-- <p class="card-text">${product.price}원</p> --%>
    			<p class="card-text" align="right"><b>${product.price}원</b></p>
    			<a href="board?cmd=detail&id=${product.id}" class=" stretched-link"></a>
  			</div>
		</div>
		<%-- 카드 레이아웃 카드 한 블록 끝 --%>
		
		
		<c:if test="${status.count % 3 == 0}">
			</div>
		</c:if>
		<%-- 아래 한줄 코드는 상품수가 3개 단위로 딱 떨어지지 않을 때 맨 마지막에 </div> 태그를 붙이는 기능 --%>
		<c:if test="${size%3!=0 && size-count<=3 && size%3 == status.count}"></div></c:if>
		
		
	</c:forEach>

</div>