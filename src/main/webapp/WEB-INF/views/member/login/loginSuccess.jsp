<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 성공</title>
</head>
<body>

<sec:authorize access="isAuthenticated()">
	로그인성공했음<br>
	<sec:authentication property="name" />님 안녕하세요 ^^<br>
	실제이름은 <sec:authentication property="principal.name" />님 안녕하세요 ^^<br>
	로그인 실패하면 여기 위에는 나오지 않음
	<a href="${pageContext.request.contextPath}/member/logout">로그아웃</a>
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
	로그인 실패함<br>
	<a href="${pageContext.request.contextPath}/member/loginForm">로그인하러가기</a>
</sec:authorize>
</body>
</html>