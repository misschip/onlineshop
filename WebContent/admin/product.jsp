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
			<th>상품명</th>
			<th>상세</th>
			<th>가격</th>
			<th>수정/삭제</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="productDto" items="${productDtos}">
		<tr>
			<td>${productDto.product.id}</td>
			<td>${productDto.category.root_category}</td>
			<td>${productDto.category.sub_category}</td>
			<td>${productDto.product.name}</td>
			<td>${productDto.product.description}</td>
			<td>${productDto.product.price}</td>
			<td><button type="button" class="btn btn-primary">수정</button>
				<button type="button" class="btn btn-primary">삭제</button>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
    
<%@ include file="../include/footer.jsp" %>