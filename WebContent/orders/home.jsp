<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    

<%@ include file="../include/header.jsp" %>

<div class="container">


	<div class="row">	
	
	<c:forEach var="ordersDto" items="${ordersDtos}">
	<%-- 바깥쪽 --%>
		<div class="media border p-3">
		 
		  <div class="media-body">
		    <h4>주문번호 : ${ordersDto.orders.id} &nbsp;&nbsp;&nbsp;<small><i>주문일자 : ${ordersDto.orders.orders_date}</i></small></h4>
		    <%-- 이쯤에 총액도 표출해야 --%>
		    <p>배송지 : ${ordersDto.orders.address}</p>
		    <h4 style="text-align:right;"> 총액 : ${ordersDto.orders.total} 원</h4>
		    <h5 style="text-align:right;">상태 : ${ordersDto.orders.status}</h5>
		    
		    <c:forEach var="itemDto" items="${ordersDto.itemDtos}">
		    <%-- 안쪽 --%>
		    <hr />
		    <div class="media p-3">
		      <img src="${itemDto.product.image1}" alt="${itemDto.product.name}" class="mr-3 mt-3 rounded" style="width:100px;">
		      <div class="media-body">
		        <h4><a href="/onlineshop/product?cmd=detail&id=${itemDto.product.id}">${itemDto.product.name}<small><i></i></small></a></h4>
		        <p>${itemDto.product.description}</p>
		        <h5 style="text-align:right;font-style:italic">${itemDto.item.unit_price} 원&nbsp;&nbsp;&nbsp; x &nbsp;&nbsp;&nbsp;수량 : ${itemDto.item.quantity} &nbsp;&nbsp;&nbsp; = &nbsp;&nbsp;&nbsp; ${itemDto.item.unit_price * itemDto.item.quantity} 원</h5>
		      </div>
		    </div> 
		    <%-- 안쪽 끝 --%>
		    </c:forEach>
		    
		  </div>
		</div>

	<%-- 바깥쪽 끝 --%>
	</c:forEach>
	
	</div>
	
	

</div>

<%@ include file="../include/footer.jsp" %>