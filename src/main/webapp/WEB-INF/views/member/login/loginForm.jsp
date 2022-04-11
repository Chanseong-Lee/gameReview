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
<title>로그인</title>
</head>
<body>
<h1>로 그 인</h1>
<sec:authorize access="isAuthenticated()">
<script type="text/javascript">
window.location.href='${pageContext.request.contextPath}/game/main';
</script>
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
<form action="${pageContext.request.contextPath}/member/login" method="POST" onsubmit="return loginSubmit()">
	<input type="text" id="email" name="email" placeholder="EMAIL 입력" value="${email}" oninput="notEmptyEmail()"/><br>
	<input type="password" id="password" name="password" placeholder="비밀번호 입력" oninput="notEmptyPwd()"/><br>
	<label><input type="checkbox" id="remember-me" name="remember-me" value="True"/>자동 로그인</label><br>
	<label><input type="checkbox" id="rememberId" name="rememberId" ${checked}>아이디 기억하기</label><br>
	<input type="submit" id="submit" value="로그인">&nbsp;&nbsp;
	<input type="button" value="메인으로" onclick="location.href='${pageContext.request.contextPath}/game/main'">
</form>
<span id="errorMsg" style="color:red;">${requestScope.loginFailMsg }</span><br>
<a href="javascript:popUpEmail()">아이디</a> / <a href="javascript:popUpPassword()">비밀번호</a>가 기억이 나지 않으시나요?

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/loginFormJs.js"></script>
</sec:authorize>
<script>
function popUpEmail(){
	let url = "${pageContext.request.contextPath}/member/find/email";
	let name = "이메일 찾기";
	let option = "width = 550, height = 300, top = 100, left = 200, location = no";
	window.open(url, name, option);
}

function popUpPassword(){
	let url = "${pageContext.request.contextPath}/member/find/password";
	let name = "비밀번호 찾기";
	let option = "width = 550, height = 300, top = 100, left = 200, location = no";
	window.open(url, name, option);
}
</script>
</body>
</html>