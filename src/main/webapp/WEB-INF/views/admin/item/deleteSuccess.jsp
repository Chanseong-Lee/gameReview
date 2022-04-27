<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
alert("아이콘삭제 성공!\n목록으로 이동합니다.");
window.location.href='${pageContext.request.contextPath}/admin/item/itemList';
</script>
</body>
</html>