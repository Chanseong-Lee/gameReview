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
회원정보 수정<br>
<a href="${pageContext.request.contextPath}/member/logout">로그아웃</a>
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
<a href="${pageContext.request.contextPath}/member/loginForm">로그인하기</a><br>
<a href="${pageContext.request.contextPath}/member/regist">회원가입하기</a>
</sec:authorize>
</body>
</html>