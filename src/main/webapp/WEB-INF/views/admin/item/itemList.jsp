<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>아이템 관리</h1>
<button type="button" onclick="location.href='${pageContext.request.contextPath}/admin/item/addItem'">신규 아이템 등록하기</button>
<table>
	<tr>
		<th>번호</th>
		<th>아이템이름</th>
		<th>가격</th>
		<th>등록일</th>
	</tr>
<c:if test="${not empty items }">
	<c:forEach var="item" items="${items }">
	<tr>
		<td>${item.itemNum }</td>
		<td><a href="${pageContext.request.contextPath}/admin/item/detail/${item.itemNum }">${item.itemName }</a></td>
		<td>${item.itemPrice }</td>
		<td>${item.itemRegdate }</td>
	</tr>
	</c:forEach>		
</c:if>
<c:if test="${empty items }">
	<tr>
		<td colspan="5">등록된 데이터가 없습니다.</td>
	</tr>
</c:if>
</table>
<button type="button" onclick="location.href='${pageContext.request.contextPath}/admin/home'">관리자홈으로</button>
</body>
</html>