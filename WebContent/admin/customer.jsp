<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    


<%@ include file="./include/header.jsp" %>



<table class="table table-bordered table-striped table-sm">
	<thead>
		<tr>
			<th>일련번호</th>
			<th>사용자명</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>주소</th>
			<th>가입일</th>
			<th>수정/삭제</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="customer" items="${customers}">
		<tr>
			<td>${customer.id}</td>
			<td>${customer.username}</td>
			<td>${customer.phone}</td>
			<td>${customer.email}</td>
			<td>${customer.address}</td>
			<td>${customer.registerDate}</td>
			<td><button type="button" class="btn btn-primary">수정</button>
				<button type="button" class="btn btn-primary">삭제</button>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>


<%@ include file="../include/footer.jsp" %>