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
	<%-- 
	 이메일 및 패스워드에 이상이 없고, 모든 내용이 입력되었을 때만 submit을 활성화 하도록 구현 
	 전부 이상없이 완료해야 submit이 활성화 되므로 데이터 유효성 검증은 js에서 진행
	 이메일 중복체크는 ajax를 이용하여 비동기로 처리
	 혹시 모를 우회기법 사용시 방어할 수 있도록 유효성 검증 로직을 서버에서도 처리.
	 --%>
	 <%--아이디는 영문자만 가능. 4자이상 20자 미만 --%>
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
		<input type="submit" id="submit" value="회원가입">
	</form:form>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/ajaxForEmail.js"></script>
</body>
</html>