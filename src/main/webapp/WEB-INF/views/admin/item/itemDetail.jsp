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
<title>ADMIN</title>
</head>
<body>
<h1>아이템 관리</h1>
<table>
	<tr>
		<td>아이템이미지</td>
		<td>
		<c:if test="${item.itemNum == 1 }">
			<img alt="아이콘이미지" src="<c:url value='/resources/images/user_icon/${item.itemFilename}'/>">
		</c:if>
		<c:if test="${item.itemNum != 1 }">
			<img alt="아이콘이미지" src="<c:url value='/images/icons/${item.itemFilename}'/>">
		</c:if>
		</td>
	</tr>
	<tr>
		<td>아이템이름</td>
		<td>${item.itemName }</td>
	</tr>
	<tr>
		<td>아이콘가격</td>
		<td>${item.itemPrice }</td>
	</tr>
	<tr>
		<td>아이콘등록일</td>
		<td>${item.itemRegdate }</td>
	</tr>
</table>
<button type="button" onclick="location.href='${pageContext.request.contextPath}/admin/item/itemUpdate/${item.itemNum }'">수정하기</button>
<button type="button" onclick="location.href='${pageContext.request.contextPath}/admin/item/itemList'">목록으로</button>
<button type="button" onclick="location.href='${pageContext.request.contextPath}/admin/item/itemDelete/${item.itemNum }'">삭제하기</button>
</body>
</html>