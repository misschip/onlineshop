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
					<td><a href="/onlineshop/admin/adm?cmd=updateProduct&id=${productDto.product.id}" class="btn btn-primary">수정</a>
						<a href="/onlineshop/admin/adm?cmd=deleteProduct&id=${productDto.product.id}" class="btn btn-primary">삭제</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>
    
<%@ include file="../include/footer.jsp" %>