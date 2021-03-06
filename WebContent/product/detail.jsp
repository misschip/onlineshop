<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>

img {
  	object-fit: cover;
	width: 100%;
	height: 450px;
}

</style>

<%@ include file="../include/header.jsp" %>



	<div class="container">

		<div class="row">
		
			<div class="col">
				<div class="card" style="width:500px; margin:10px;">
		  			<img class="card-img-top" src="${product.image1}" alt="${product.name}">
				</div>
			</div>
			
			
			
			<div class="col">
				<hr width="100%"/>
				<h2 align="center">${product.name}</h2>
				<p>무료배송</p>
				
				<form action="/onlineshop/cart">
					<input type="hidden" name="cmd" value="add">
					<input type="hidden" name="id" value="${product.id}">
					
					<div class="form-group">
						<div class="bg light" align="center">
							<span style="cursor:pointer;" id="minus" onclick="minusQuantity()">➖ </span>
							<input type="text" id="quantity" name="quantity" value="1" size="1">
							<span style="cursor:pointer;" id="plus" onclick="plusQuantity()">➕</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<span id="pprice">${product.price}</span>원
						</div>
					</div>
					
					<div class="form-group" align="center">
						<h3>총액 : <span id="totalPrice">${product.price}</span>원</h3>
					</div>
					
					<div class="form-group" align="center">
						<button type="submit" class="btn btn-primary">장바구니 담기</button>
						<button type="submit" class="btn btn-primary">구매하기</button>
					</div>
				</form>
				
			</div>
		</div>
	
	
	</div>
	
	<script src="/onlineshop/js/detail.js"></script>
	
<%@ include file="../include/footer.jsp" %>