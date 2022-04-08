<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인증 성공</title>
</head>
<body>
<script type="text/javascript">
	var email = '${email}';
	alert(email + '님의 이메일 인증이 완료되었습니다!\n이제 로그인이 가능합니다.\n확인버튼을 누르시면 로그인 페이지로 이동합니다.');
	self.location="${pageContext.request.contextPath}/member/loginForm";
</script>
</body>
</html>