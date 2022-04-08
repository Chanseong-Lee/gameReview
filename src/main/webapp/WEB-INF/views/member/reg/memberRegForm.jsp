<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
td {
	height: 50px;
}
.errorAfterSubmit{
	color:red;
}
</style>

</head>
<body>
	<h1>회원가입</h1>
	<form:form action="regist" commandName="mrc" method="post">
		<table>
			<tr>
				<td>이메일</td>
				<td><form:input id="email" path="email" oninput="checkEmail()" />&nbsp;<input
					type="button" value="중복확인" onclick="checkEmail()"><br>
					<span class="errorAfterSubmit"><form:errors path="email" /></span>
					<span id="emailError"></span>
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td><form:input id="name" path="name" oninput="checkName()"/><br>
				 	<span class="errorAfterSubmit"><form:errors path="name" /></span>
				 	<span id="nameError"></span>
				 </td>
			</tr>
			<tr>
				<td>닉네임</td>
				<td>
					<form:input id="nickname" path="nickname" oninput="checkNickname()"/><br> 
					<span class="errorAfterSubmit"><form:errors path="nickname" /></span>
					<span id="nicknameError"></span>
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><form:password id="password" path="password" oninput="checkPw()"/><br> 
				<span class="errorAfterSubmit"><form:errors path="password" /></span></td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td><form:password id="confirmPassword" path="confirmPassword" oninput="checkPw()"/><br> 
					<span class="errorAfterSubmit"><form:errors path="confirmPassword" /></span>
					<span id="noMatchErr"></span>
				</td>
			</tr>
		</table>
		<input type="submit" id="submit" value="회원가입">&nbsp;&nbsp;
		<input type="button" value="메인으로" onclick="location.href='${pageContext.request.contextPath}/game/main'">
	</form:form>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/ajaxForEmail.js"></script>
</body>
</html>