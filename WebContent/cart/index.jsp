<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    

<%@ include file="../include/header.jsp" %>

<style>
img {
  	object-fit: cover;
	width: 90px;
	height: 90px;
}
</style>

<div class="container">

	<h2>장바구니 보기</h2>
	
	<div class="row">
	
	  <div class="col-sm-7">
	  	<c:forEach var="entry" items="${cart}">
	  		<div class="row border border-left-0 border-right-0">
				<div class="col">
					<img src="${entry.key.image1}"/>
				</div>
				<div class="col">
				 	${entry.key.name}
				</div>
				<div class="col">
				 	<span style="cursor:pointer;" id="minus" onclick="minusQuantity()">➖ </span>
							<input type="text" id="quantity" name="quantity" value="${entry.value}" size="1">
							<span style="cursor:pointer;" id="plus" onclick="plusQuantity()">➕</span>
				</div>
				<div class="col">
					${entry.key.price}원
				</div>
				
				
			</div>
	  	</c:forEach>
	  	
	  </div>
	  
	  
	  <div class="col-sm-5">
	  	<div class="row">오른쪽 5번</div>
	  </div>

	</div>

</div>


<script src="/onlineshop/js/cart.js"></script>

<%@ include file="../include/footer.jsp" %>    