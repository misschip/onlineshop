<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Map"%>
<%@page import="com.cos.shop.util.MenuUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    


<%
	Map<String,Map<String,Integer>> menus = MenuUtil.prepareMenuMap();
	Gson gson = new Gson();
	String menusJson = gson.toJson(menus);
	request.setAttribute("menusJson",menusJson);
%>

<script>
	console.log(${menusJson});
</script>

<%@ include file="./include/header.jsp" %>


<div align="center">
	<hr width="60%" />
</div>

<div class="container">
<div class="row justify-content-center">
    <div class="col-auto">   
    <h3>상품 등록</h3>



		<form>
		
		<div class="input-group mb-3">
		  <select name="rootCategory" class="custom-select">
		    <option selected>대분류</option>
		    <option value="volvo">식품/생필품</option>
		    <option value="fiat">의류/잡화</option>
		    <option value="audi">생활용품</option>
		  </select>
		</div>

		<div class="input-group mb-3">
		  <select name="subCategory" class="custom-select">
		    <option selected>소분류</option>
		    <option value="volvo">반찬</option>
		    <option value="fiat">과일</option>
		    <option value="audi">견과류</option>
		  </select>
		 </div>
		  		
	<%-- 	
			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">대분류</span>
				</div>
				<input type="text" class="form-control">
				&nbsp;&nbsp;&nbsp;
				<div class="input-group-prepend">
					<span class="input-group-text">소분류</span>
				</div>
				<input type="text" class="form-control">
			</div>		
		
	--%>
		
			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">상품명</span>
				</div>
				<input type="text" class="form-control">
			</div>

			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">상품가격</span>
				</div>
				<input type="text" class="form-control">
			</div>

			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">상품수량</span>
				</div>
				<input type="text" class="form-control">
			</div>
			
			
			<%-- 상품설명 부분은 summernote 적용하고 그림파일 4개 정도는 cos 라이브러리로 올리는게 좋을 듯 --%>
			<div class="form-group">
  				<label for="comment">상품설명</label>
  				<textarea class="form-control" rows="5" id="comment"></textarea>
			</div>
			
		</form>





	</div>
</div>
</div>


<%@ include file="../include/footer.jsp" %>