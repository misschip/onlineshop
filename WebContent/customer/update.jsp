<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%@ include file="../include/header.jsp" %>


<div class="container">
	<div class="col-sm-5">
		<!-- 아래 onsumbit에서 return validate()가 true인 경우에만 action 실행됨 -->
		<form action="/onlineshop/customer?cmd=updateProc" method="POST" class="was-validated">
		  
		  <input type="hidden" name="id" value="${sessionScope.principal.id}"/>
		  
		  <div class="form-group">
		    <label for="username">아이디:</label>
		    
		    <input value="${principal.username}" type="text" class="form-control" id="username" placeholder="아이디 입력" name="username" required readonly>
		    
		    
		  </div>
		  
		  <div class="form-group">
		    <label for="password">암호:</label>
		    <input type="password" class="form-control" id="password" placeholder="암호 입력" name="password" required>

		  </div>
		  
		  <div class="form-group">
		   <label for="phone">휴대전화:</label>
		   <input value="${principal.phone}" type="text" class="form-control" id="phone" placeholder="휴대전화 입력" name="phone" required>
		  </div>
		  
		   <div class="form-group">
		    <label for="email">이메일:</label>
		    <input value="${principal.email}" type="email" class="form-control" id="email" placeholder="이메일 입력" name="email" required>

		  </div>
		  
		  
		   <div class="form-group">
		    <label for="address">주소:</label>
		    <!-- 아래 button이 float-right임에 주의. inline-block이어서라고 하는데 ... -->
		    <button type="button" class="btn btn-warning float-right" onClick="goPopup()">주소검색</button>
		    <!--  위 버튼 type="button"으로 해야 눌러도 submit이 일어나지 않는다. 그래서 validation check 에러가 안남 -->
		    <!-- 만약 input type="submit" 으로 하면 클릭시 validation check로 빈 필드에 먼저 입력해야 한다는 알림이 뜨게 되는 현상 -->
		    <input value="${principal.address}" type="text" class="form-control" id="address" placeholder="주소 입력" name="address" required readonly>

		    <input value="${principal.zipNo}" type="text"  id="zipno" placeholder="우편번호" name="zipno" size="7" readonly>
		  </div>

		
		  <button type="submit" class="btn btn-primary">수 정</button>
		  
		</form>
	
	</div>
</div>


<script src="/onlineshop/js/join.js"></script>


<%@ include file="../include/footer.jsp" %>