<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page session="false" %>
<html>
<head>
<meta charset="UTF-8"/>
	<title>Home</title>
</head>
<body>

<form action="/review/add">
<ul>
	<li><input type="text" value="이름" name="gName"></li>
	<li><input type="text" value="개발" name="gDev"></li>
	<li><input type="date" value="출시일" min="1950-01-01" max="2022-04-01" name="gDate"></li>
	<li><input type="text" value="가격" name="gPrice"></li>
	<li><input type="text" value="게임 설명" name="gContent"></li>
	<li><input type="file" value="이미지" ></li>
	<input type="submit"/>
</ul>
</form>

</body>
</html>
