<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 성공!</title>
</head>
<body>
<div>
	<p>${nickname}(${name })님 게임리뷰에 회원가입 해주셔서 감사합니다!</p>
	<p>${email}</p>
	<p>으로 메일을 보냈습니다.</p>
	<p>메일 확인 후 인증버튼을 눌러주세요!</p>
	<a href="${pageContext.request.contextPath}/member/loginForm">로그인페이지로 이동</a>
</div>
</body>
</html>