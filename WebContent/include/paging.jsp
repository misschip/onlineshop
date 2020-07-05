<%-- home.jsp의 일부로 들어감 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<c:choose>
<c:when test="${empty param.keyword}">
	<c:set var="pagePrev" value="product?cmd=home&page=${param.page-1}"/>
	<c:set var="pageNext" value="product?cmd=home&page=${param.page+1}"/>
</c:when>
<c:otherwise>
	<c:set var="pagePrev" value="product?cmd=search&page=${param.page-1}&keyword=${param.keyword}"/>
	<c:set var="pageNext" value="product?cmd=search&page=${param.page+1}&keyword=${param.keyword}"/>
	</c:otherwise>
</c:choose> 

<!-- disabled -->
<ul class="pagination justify-content-center">
	<c:choose>
		<c:when test="${param.page == 0}">
		<li class="page-item disabled"><a class="page-link" href="${pageScope.pagePrev}">Previous</a></li>
	</c:when>
	<c:otherwise>
		<li class="page-item"><a class="page-link" href="${pageScope.pagePrev}">Previous</a></li>
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${lastPage == param.page}">
		<li class="page-item disabled"><a class="page-link" href="${pageScope.pageNext}">Next</a></li>
	</c:when>
	<c:otherwise>
		<li class="page-item"><a class="page-link" href="${pageScope.pageNext}">Next</a></li>
		</c:otherwise>
	</c:choose>
</ul>