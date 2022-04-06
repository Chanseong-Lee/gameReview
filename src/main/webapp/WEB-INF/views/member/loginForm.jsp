<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/member/login" method="POST">
	<input type="text" name="email" placeholder="EMAIL 입력"/><br>
	<input type="password" name="password" placeholder="비밀번호 입력"/><br>
	<label><input type="checkbox" name="rememberEmail"/>아이디 기억하기</label><br>
	<input type="submit" value="로그인">&nbsp;&nbsp;
	<input type="button" value="메인으로" onclick="location.href='${pageContext.request.contextPath}/game/main'">
</form>
</body>
</html>