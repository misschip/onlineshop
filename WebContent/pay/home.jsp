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
				<%-- 	<a href="/onlineshop/pay?cmd=payProc&total=${total}" class="btn btn-primary">결제하기</a> --%>
					<a href="/onlineshop/pay/payForm.jsp?total=${total}" class="btn btn-primary">결제하기</a>
	  			</div>
	  			
	  		</div>
	  	
	  </div>

	</div>
	
	<br/><br/><br/><br/><br/>
	
	
	<h4>배송지 정보</h4>
	<div class="row">
		<div class="col-sm-7">
			<div class="row border border-left-0 border-right-0">
				<div class="col">
					<div style="font-weight:bold">주소 : ${sessionScope.principal.address} <br/>
					         전화번호 : ${sessionScope.principal.phone} <br/>
					         이메일 : ${sessionScope.principal.email}
					</div>
				</div>
			</div>
		</div>
	</div>

</div>



<%@ include file="../include/footer.jsp" %>    