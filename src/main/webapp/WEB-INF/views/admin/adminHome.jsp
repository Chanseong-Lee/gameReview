<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>admin home</h1><br>
<div>
<ol>
	<li><a href="${pageContext.request.contextPath}/member/logout">로그아웃</a></li>
	<li><a href="${pageContext.request.contextPath}/admin/game/gameList">게임관리</a></li>
	<li><a href="${pageContext.request.contextPath}/admin/member/memberList">회원관리</a></li>
	<li><a href="${pageContext.request.contextPath}/admin/item/itemList">아이템관리</a></li>
	<li><a href="${pageContext.request.contextPath}/game/main">사용자페이지로</a></li>
</ol>
</div>

</body>
</html>