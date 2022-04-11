<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
해당정보의 회원이 존재합니다.<br>
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