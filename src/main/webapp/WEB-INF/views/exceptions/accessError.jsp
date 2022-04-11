<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Access Denied</title>
</head>
<body>
<script type="text/javascript">
alert("접근권한이 없는 사용자 입니다.\n메인으로 이동합니다.");
window.location.href='${pageContext.request.contextPath}/game/main';
</script>
</body>
</html>