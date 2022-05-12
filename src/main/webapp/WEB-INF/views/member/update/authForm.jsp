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
<script src="https://kit.fontawesome.com/c965630904.js" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css2?family=Oswald:wght@300;400;500;600;700&amp;display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css">
</head>
<body>
<div class="container p-4">
	<h2>비밀번호를 입력하세요.</h2>
	<form:form action="${pageContext.request.contextPath}/member/update/auth" commandName="passwordCommand" method="post">
		<div class="form-group mb-2">
	    	<label for="password" class="form-label mt-4">Password</label>
			<form:password class="form-control" id="password" path="password" placeholder="비밀번호 입력" oninput="inputPassword()"/>
			<form:errors id="error" path="password"></form:errors>
	    </div>
		
		<input class="btn btn-success btn-sm" id="submit" type="submit" value="확인">&nbsp;&nbsp;
		<button class="btn btn-danger btn-sm" type="button" id="close" onclick="btnClose()">닫기</button>
	</form:form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
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