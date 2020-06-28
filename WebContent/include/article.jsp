<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
<div class="container">
	<div class="row">
	  <div class="col-sm-4">
	  	
	  </div>
	  <div class="col-sm-4">
	  </div>
	  <div class="col-sm-4">
	  </div>
	</div>
	<c:set var="size" value="${products.size()}"/>
	<c:forEach var="product" items="${products}" varStatus="status">
		<c:if test="${status.index % 3 == 0}">
			<div class="row">
		</c:if>
					<%-- 레이아웃 작업을 위해 임시로 border 속성 줌 --%>
				<div class="col-sm-4 border">
	  				${product.name}<br>
	  				${product.description }<br>
	  				${product.price}
	  			</div>
	  			
	  	<c:if test="${status.count % 3 == 0}">
			</div>
		</c:if>
		<%-- 아래 한줄 코드는 상품수가 3개 단위로 딱 떨어지지 않을 때 맨 마지막에 </div> 태그를 붙이는 기능 --%>
		<c:if test="${size%3!=0 && size-count<=3 && size%3 == status.count}"></div></c:if>
	</c:forEach>

</div>