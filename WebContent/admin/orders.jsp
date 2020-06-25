<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    


<%@ include file="./include/header.jsp" %>


<div class="row justify-content-center">
    <div class="col-auto">   

		<table class="table table-bordered table-striped table-sm table-responsive text-center">
			<thead>
				<tr>
					<th>일련번호</th>
					<th>주문일</th>
					<th>배송주소</th>
					<th>수신자명</th>
					<th>수신자 전화번호</th>
					<th>결제방법</th>
					<th>총액</th>
					<th>주문상태</th>
					<th>수정/삭제</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="order" items="${orders}">
				<tr>
					<td>${order.id}</td>
					<td>${order.orders_date}</td>
					<td>${order.shipping_address}</td>
					<td>${order.recipient_name}</td>
					<td>${order.recipient_phone}</td>
					<td>${order.payment}</td>
					<td>${order.total}</td>
					<td>${order.status}</td>
					<td><button type="button" class="btn btn-primary">수정</button>
						<button type="button" class="btn btn-primary">삭제</button>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<%@ include file="../include/footer.jsp" %>