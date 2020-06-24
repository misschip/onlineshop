<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:if test="empty ${admin}">
	<c:redirect url="/adm?cmd=login" />
</c:if>

<c:if test="${admin}">
	<c:redirect url="/adm?cmd=home" />
</c:if>
