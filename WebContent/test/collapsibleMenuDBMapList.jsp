<%@page import="com.cos.shop.util.MenuUtil"%>
<%@page import="com.cos.shop.model.Category"%>
<%@page import="com.cos.shop.repository.CategoryRepository"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<%
	

	
	// List<Category> allCategories = MenuUtil.getAllCategories();
	
	// List<String> rootMenus = MenuUtil.getRootMenus();
	
	Map<String,Map<String,Integer>> menuMap = MenuUtil.prepareMenuMap();
	
	
	
	/*
	Map<String,List<String>> menuMap = new HashMap<>();
	
	List<String> menuList1 = Arrays.asList(new String[]{"가구","문구"});
	List<String> menuList2 = Arrays.asList(new String[]{"여성의류","남성의류","아동복"});
	List<String> menuList3 = Arrays.asList(new String[]{"과일","통조림"});
	List<String> menuList4 = Arrays.asList(new String[]{"노트북","데스크톱"});
	
	menuMap.put("생활용품", menuList1);
	menuMap.put("의류", menuList2);
	menuMap.put("식품/농산물", menuList3);
	menuMap.put("컴퓨터", menuList4);
	*/
	
	request.setAttribute("menus",menuMap);
	
	//------ menuMap 세팅완료
	//------ menuMap의 메뉴들을 아래 화면에서 표출시켜 봄
	
%>
    

<%@ include file="../include/header.jsp" %>
    
<div class="row">
  <div class="col-sm-3">
  	<div class="card-header rounded-0" data-toggle="collapse" data-target="#list-tab" style="cursor: pointer; background-color: navy; color:white; font-size:large">카테고리</div>
    <div class="list-group collapse rounded-0 show" id="list-tab" role="tablist">
    
    <c:forEach var="entry" items="${menus}" varStatus="status">
    	<a class="list-group-item list-group-item-action" data-toggle="list" href="#list-${status.count}" role="tab">${entry.key}</a>
    </c:forEach>
    
     </div>
  </div>
  
  <div class="col-sm-2">
    <div class="tab-content" id="nav-tabContent">
    <c:forEach var="entry" items="${menus}" varStatus="status">
      <div class="tab-pane fade" id="list-${status.count}" role="tabpanel">
      			<ul class="list-group">
      				<c:forEach var="subMenu" items="${entry.value}">
	      				<li class="list-group-item d-flex justify-content-between align-items-center">
	      				<a href="/onlineshop/board?cmd=listProd&cate=${subMenu.value}">${subMenu.key}</a>
	      				<span class="badge badge-primary badge-pill">3</span> <%-- 해당 카테고리 내의 상품 건수 표출하는 이 부분은 구현해야 --%>
	      				</li>
      				</c:forEach>
      			</ul>
      </div>
    </c:forEach>
    </div>
   </div>
   
   <div class="col-sm-7">
   		<%-- 메뉴 오른쪽 화면 표출 공간 --%>
   </div>
</div>