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

	<h2>주문 하기</h2>
	
	<div class="row">
	
	  <div class="col-sm-7">
	  	
	  	<c:set var="total" value="${0}" />
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
					${entry.key.price} &nbsp;&nbsp;&nbsp; x &nbsp;&nbsp;&nbsp; ${entry.value} &nbsp;&nbsp;&nbsp; =
				</div>
				
				<div class="col">
					<span style="font-size: 18px;font-weight:bold;">${entry.key.price * entry.value}</span> 원
				</div>
				<%-- 
				<div class="col">
					<span id="subSum-${status.count}">${entry.key.price * entry.value}</span>원
				</div>
				--%>
			</div>
			
			<c:if test="${status.last}">
				<input type="hidden" id="itemCount" name="itemCount" value="${status.count}"><%-- 현재 장바구니의 상품 가지수 --%>
			</c:if>
			
			<%-- 상품별 부분합을 누적해서 총 합계 금액을 구함 --%>
			<c:set var="total" value="${total + (entry.key.price * entry.value)}" />
	  	</c:forEach>

	  </div>
	  
	  
	  <div class="col-sm-5">
	  	
	  		<div class="card" style="width:400px;">
	  			<div class="card-body text-right">
	  				<h3 class="card-title">합 계</h3>
	  				<hr />
	  				<h5><span id="totalSum"><c:out value="${total}" /></span> 원</h5>
	  				<%-- form 태그 외부에 놓인 button이 form으로 action을 날림 --%>
					<button type="submit" form="payForm" class="btn btn-primary">결제하기</button>
					<%-- <a href="/onlineshop/pay/payForm.jsp?total=${total}" class="btn btn-primary">결제하기</a> --%>
	  			</div>
	  			
	  		</div>
	  	
	  </div>

	</div>
	
	<br/><br/><br/><br/><br/>
	
	
	<h4>배송지 정보</h4>
	<div class="row">
		<div class="col-sm-7">
			<div class="row border border-left-0 border-right-0">
				
				<form id="payForm" action="/onlineshop/pay/payForm.jsp" style="width:60%" method="POST">
						<input type="checkbox" checked="checked" id="checkAddr" onchange="shippingAddr();"> 
					    <label>&nbsp;주소지와 동일</label><br/><br/>
					    
						<label>이름:</label>
					    <input type="text" class="form-control" value="${principal.username}" id="username">
					    	 
					    <label>배송주소:</label>
					    <button type="button" class="btn btn-warning float-right" onClick="goPopup()">주소검색</button>
					    <input type="text" class="form-control" value="${principal.address}" id="address">

						<label>우편번호:</label>
					    <input type="text" class="form-control" value="${principal.zipNo}" id="zipno">

					    <label>전화번호:</label>
					    <input type="text" class="form-control" value="${principal.phone}" id="phone">


					    <label>이메일:</label>
					    <input type="text" class="form-control" value="${principal.email}" id="email">
					
						<input type="hidden" value="${total}" id="totalPrice" name="totalPrice"> <%-- 결제 총액 --%>
									<%-- 장바구니에 모든 구매품과 수량이 들어있고 이 정보는 세션에 이미 존재하므로 --%>
				</form> 			<%-- 여기서는 총액 정도만 넘겨 주고 다음 단계에서 총액 비교함으로써 간단한 검증을 하면 될 듯 --%>
			</div>
		</div>
	</div>
		
	


</div>


<script src="/onlineshop/js/pay.js"></script>
<script src="/onlineshop/js/join.js"></script>

<%@ include file="../include/footer.jsp" %>    