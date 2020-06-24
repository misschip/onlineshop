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
					<th>사용자명</th>
					<th>전화번호</th>
					<th>이메일</th>
					<th>수정/삭제</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="manager" items="${managers}">
				<tr>
					<td>${manager.id}</td>
					<td>${manager.username}</td>
					<td>${manager.phone}</td>
					<td>${manager.email}</td>
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