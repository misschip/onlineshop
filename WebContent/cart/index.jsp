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
	  	<form>
	  	<c:forEach var="entry" items="${cart}" varStatus="status">
	  		<div class="row border border-left-0 border-right-0">
				<div class="col">
					<img src="${entry.key.image1}"/>
					<input type="hidden" id="pid-${status.count}" name="productid" value="${entry.key.id}">
				</div>
				<div class="col">
				 	${entry.key.name}
				</div>
				<div class="col">
				 	<span style="cursor:pointer;" id="minus" onclick="minusQuantity('quantity-${status.count}')">➖ </span>
							<input type="text" id="quantity-${status.count}" name="quantity" value="${entry.value}" size="1">
							<span style="cursor:pointer;" id="plus" onclick="plusQuantity('quantity-${status.count}')">➕</span>
				</div>
				<div class="col">
					<span id="pprice-${status.count}">${entry.key.price}</span>원
				</div>
				<div class="col">
					<span id="subSum-${status.count}">${entry.key.price * entry.value}</span>원
				</div>
				
			</div>
			
			<c:if test="${status.last}">
				<input type="hidden" id="itemCount" name="itemCount" value="${status.count}"><%-- 현재 장바구니의 상품 가지수 --%>
				<button class="btn btn-primary" onclick="saveCart();return false;">장바구니 저장하기</button>
			</c:if>
	  	</c:forEach>
	  	</form>
	  </div>
	  
	  
	  <div class="col-sm-5">
	  	
	  		<div class="card" style="width:400px;">
	  			<div class="card-body text-right">
	  				<h3 class="card-title">합 계</h3>
	  				<h5><span id="totalSum"></span>원</h5>
					<a href="#" class="btn btn-primary">결제하기</a>
	  			</div>
	  			
	  		</div>
	  	
	  </div>

	</div>

</div>


<script src="/onlineshop/js/cart.js"></script>

<%@ include file="../include/footer.jsp" %>    