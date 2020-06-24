<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    


<%@ include file="./include/header.jsp" %>



<table class="table table-bordered table-striped table-sm">
	<thead>
		<tr>
			<th>일련번호</th>
			<th>대분류</th>
			<th>소분류</th>
			<th>수정/삭제</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="order" items="${orders}">
		<tr>
			<td>${order.id}</td>
			<td>${category.root_category}</td>
			<td>${category.sub_category}</td>
			<td><button type="button" class="btn btn-primary">수정</button>
				<button type="button" class="btn btn-primary">삭제</button>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>


<%@ include file="../include/footer.jsp" %>