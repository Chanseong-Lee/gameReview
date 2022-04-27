<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
</head>
<body>
<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal.usingIcon" var="icon"/>
<c:if test="${icon == 'default_icon.png' }">
	<img alt="아이콘이미지" src="<c:url value='/resources/images/user_icon/${icon}'/>" width="15">
</c:if>
<c:if test="${icon == 'default_admin_icon.png' }">
	<img alt="아이콘이미지" src="<c:url value='/resources/images/user_icon/${icon}'/>" width="15">
</c:if>
<c:if test="${icon != 'default_icon.png' && icon != 'default_admin_icon.png'}">
	<img alt="아이콘이미지" src="<c:url value='/images/icons/${icon}'/>" width="15">
</c:if>
<sec:authentication property="principal.nickname"/>님 안녕하세요.<br><br>

<a href="${pageContext.request.contextPath}/member/update/profile">프로필보기</a><br>
<button type="button" onclick="popUpInven()">인벤토리</button><br>
<button type="button" onclick="popUpShop()">포인트 상점</button><br>
<a href="${pageContext.request.contextPath}/member/logout">로그아웃</a>
<sec:authorize access="hasRole('ROLE_ADMIN')">
<br><a href="${pageContext.request.contextPath}/admin/home">관리자페이지로</a>
</sec:authorize>
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
<a href="${pageContext.request.contextPath}/member/loginForm">로그인하기</a><br>
<a href="${pageContext.request.contextPath}/member/regist">회원가입하기</a>
</sec:authorize>
<br>
여기서부터 게임 리스트 출력

<sec:authorize access="isAuthenticated()">
<script type="text/javascript">
function popUpInven(){
	let url = "${pageContext.request.contextPath}/member/inventory";
	let name = "인벤토리";
	let option = "width = 550, height = 300, top = 100, left = 200, location = no";
	window.open(url, name, option);
}
function popUpShop(){
	let url = "${pageContext.request.contextPath}/shop/";
	let name = "포인트상점";
	let option = "width = 550, height = 300, top = 100, left = 200, location = no";
	window.open(url, name, option);
}
</script>
</sec:authorize>
</body>
</html>