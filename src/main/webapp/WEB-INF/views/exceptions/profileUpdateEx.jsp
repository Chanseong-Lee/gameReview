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
alert("프로필 수정에 에러가 발생했습니다.\n메인으로 이동합니다.");
window.location.href='${pageContext.request.contextPath}/game/main';
</script>
</body>
</html>