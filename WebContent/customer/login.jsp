<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="../include/header.jsp" %>


<div class="container">

	<div class="row">
	  <div class="col-sm-4">		
			<form action="/onlineshop/customer?cmd=loginProc" method="POST" class="was-validated">
			  <div class="form-group">
			    <label for="username">Username:</label>
			
			 	<%-- 아래 ${cookie}는 Expression Language의 내장 객체임 --%>
			    <input type="text" value="${cookie.remember.value}" class="form-control" id="username" placeholder="Enter username" name="username" required>
			    <div class="valid-feedback">Valid.</div>
			    <div class="invalid-feedback">Please fill out this field.</div>
			  </div>
			  <div class="form-group">
			    <label for="pwd">Password:</label>
			    <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" required>
			    <div class="valid-feedback">Valid.</div>
			    <div class="invalid-feedback">Please fill out this field.</div>
			  </div>
			  
			  <div class="form-group form-check">
			    <label class="form-check-label">
			    <!-- 아래 c:choose의 기능 -->
			    <!-- 앞선 로그인시 아이디 기억하기를 체크했었을 경우에 remember 쿠키가 있을 것이므로
			    	그 값의 유무에 따라 이번 로그인 화면 표출시에도 아이디 기억하기를 미리 체크된 상태로 보여주도록 함 -->
			    <c:choose>
			    	<c:when test="${empty cookie.remember}">
			      		<input class="form-check-input" type="checkbox" name="remember"> 아이디 기억하기
			      	</c:when>
			      	<c:otherwise>
			      		<input class="form-check-input" type="checkbox" name="remember" checked> 아이디 기억하기
			      	</c:otherwise>
			     </c:choose>
			     
			    </label>
			  </div>
			  <button type="submit" class="btn btn-primary">로그인</button>
			
			</form>
		</div>
	</div>

</div>


<%@ include file="../include/footer.jsp" %>