<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.error{
	color: red;
}
</style>
</head>
<body>
<h2>계정 찾기</h2>
가입하신 이메일이 생각이 안나시나요?<br>
고객님의 성함과 이메일을 입력해주시면 가입여부를 알려드립니다.<br>
<form:form action="findEmail" commandName="findEmail" method="post">
	<form:input id="email" path="email" placeholder="EMAIL 입력"/><br>
	<form:errors class="error" path="email"></form:errors><br>
	<form:input id="name" path="name" placeholder="이름 입력"/><br>
	<form:errors class="error" path="name"></form:errors><br>
	<input type="submit" value="찾아보기">
	<button type="button" id="close" onclick="btnClose()">닫기</button>
</form:form>
<script>
function btnClose(){
	self.close();
}

</script>
</body>
</html>