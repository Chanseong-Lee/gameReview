<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LCK CRITIC ADMIN</title>
</head>
<body>
<script>
alert("게임삭제 성공!\n목록으로 이동합니다.");
window.location.href='${pageContext.request.contextPath}/admin/game/gameList';
</script>
</body>
</html>