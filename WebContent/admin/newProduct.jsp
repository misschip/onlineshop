<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Map"%>
<%@page import="com.cos.shop.util.MenuUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    

<%-- 상품등록시 대분류, 소분류 카테고리는 DB로부터 읽어온 값을 동적으로 뿌린다 --%>

<%
	// Map<대분류,Map<소분류,카테고리id>>
	Map<String,Map<String,Integer>> menus = MenuUtil.prepareMenuMap();
	Gson gson = new Gson();
	String menusJson = gson.toJson(menus);
	request.setAttribute("menusJson",menusJson);
	request.setAttribute("menus",menus);
%>

<script>
	// console.log(${menusJson});
</script>



<%@ include file="./include/header.jsp" %>


<div align="center">
	<hr width="60%" />
</div>

<div class="container">
<div class="row justify-content-center">
    <div class="col-auto">   
    <h3>상품 등록</h3>



		<form method="post" action="/onlineshop/admin/adm?cmd=registerProduct" enctype="multipart/form-data">
		
		<%-- 대분류 메뉴 세팅을 <select> 태그에 onload="setSelectOption() 정도의 javascript를 걸어둬서 설정하는 것도 가능하겠지만
			여기서는 일단 서버에서 EL언어로 완료 후에 보내오는 걸로 --%>
		<div class="input-group mb-3">
		  <select name="rootCategory" class="custom-select" id="select_root_cate" onchange="setSubCategory()">
		    <option selected>대분류</option>
		    <c:forEach var="rootCate" items="${menus}">
		    	<option value="${rootCate.key}">${rootCate.key}</option>
		    </c:forEach>
		  </select>
		</div>

		<%-- 소분류는 대분류의 선택에 따라 동적으로 바뀌도록 함 --%>
		<div class="input-group mb-3">
		  <select name="subCategory" class="custom-select" id="select_sub_cate">
			<option selected>소분류</option>		    
<%--	 	<option value="1">반찬</option>	소분류의 value 값은 Category 테이블의 primary key값과 일치
		    <option value="2">과일</option>
		    <option value="3">견과류</option>
--%>
		  </select>
		 </div>

		
			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">상품명</span>
				</div>
				<input type="text" class="form-control" name="prodName">
			</div>

			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">상품가격</span>
				</div>
				<input type="text" class="form-control" name="price">
			</div>
<%--
			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">상품수량</span>
				</div>
				<input type="text" class="form-control" name="quantity">
			</div>
 --%>			
			
			<%-- 상품설명 부분은 summernote 적용하고 그림파일 4개 정도는 cos 라이브러리로 올리는게 좋을 듯 --%>
			<div class="form-group">
  				<label for="comment">상품설명</label>
  				<textarea class="form-control" rows="5" id="comment" name="description"></textarea>
			</div>
			
			
			<div class="form-group">
				<label for="fileName1"> 첨부파일 </label>
				<input type="file" name="file1" id="fileName1">
			</div>
			
			
			<button type="submit" class="btn btn-secondary">등록</button>
			
		</form>





	</div>
</div>
</div>



<script>
function setSubCategory() {
	var menusJson = ${menusJson};
	var rootVal = $("#select_root_cate").val();
	// console.log(rootVal);

	for (var key in menusJson) {
		

		if (key == rootVal) {
			var subJson = menusJson[key];
			var tagStr = "";
			$("#select_sub_cate").empty();
			
			for (var subKey in subJson) {
				tagStr = tagStr + "<option value='" + subJson[subKey] + "'>" + subKey + "</option>\n";

				console.log(subKey + " : " + subJson[subKey]);
			}

			$("#select_sub_cate").append("<option selected>소분류</option>");
			$("#select_sub_cate").append(tagStr);
		}
	}
	
}


</script>

<%@ include file="../include/footer.jsp" %>