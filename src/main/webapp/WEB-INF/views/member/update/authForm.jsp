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
<title>인증</title>
</head>
<body>
<h2>비밀번호를 입력하세요.</h2>
<form:form action="${pageContext.request.contextPath}/member/update/auth" commandName="passwordCommand" method="post">
	<form:password id="password" path="password" placeholder="비밀번호 입력" oninput="inputPassword()"/><br>
	<form:errors id="error" path="password"></form:errors>
	<input id="submit" type="submit" value="확인">&nbsp;&nbsp;
	<button type="button" id="close" onclick="btnClose()">닫기</button>
</form:form>

<script type="text/javascript">
window.addEventListener('load', function () {
    submitDisable(true);
})

function btnClose(){
	self.close();
}

let pwdChecker = 0;

function submitDisable(bool) {
    document.querySelector('#submit').disabled = bool;
}

function submitActivator(){
    if(pwdChecker == 1){
        submitDisable(false);
    }else{
    	submitDisable(true);
    }
}

function inputPassword(){
	let pwd = document.querySelector("#password").value;
	if(pwd.trim().length != 0){
		pwdChecker = 1;
		submitActivator()
	}else{
		pwdChecker = 0;
		submitActivator();
	}
}


</script>
</body>
</html>