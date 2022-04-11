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
<title>Insert title here</title>
</head>
<body>
고객님의 이메일로 임시 비밀번호가 전송되었습니다.<br>
${memberEmail }를 확인해주세요.
<button type="button" onclick="closeAndFocus()">닫기</button>
<script type="text/javascript">
function closeAndFocus(){
	let parentEmailInput = opener.document.querySelector("#email");
	parentEmailInput.focus();
	self.close();
}
</script>
</body>
</html>