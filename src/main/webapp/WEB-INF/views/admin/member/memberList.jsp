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
<style type="text/css">
table{
	border: 2px solid gray;
	border-collapse: collapse;
}

td, th{
	border: 1px solid gray;
	padding: 5px 5px 5px 5px;
	
}
#searchForm{
	margin-bottom: 10px;
}
.errors{
	color: red;
	font-style: italic;
}
a:link {
	color:hotpink;
	text-decoration: none;
	text-shadow: 0 0 24px;
} 
a:visited { 
	color:pink;
	text-decoration: none; 
	text-shadow: 0 0 24px; 
}
</style>
</head>
<body>
<h1>회원관리(지금까지 가입한 회원 총 ${count }명)</h1>
<article>
	<form:form action="${pageContext.request.contextPath}/admin/member/memberList/search" commandName="search" method="get">
	<div id="searchForm">
		검색 키워드 입력 : 
		<form:select path="searchType">
			<form:option value="email">이메일</form:option>
			<form:option value="name">이름</form:option>
			<form:option value="nickname">닉네임</form:option>
		</form:select>
		<form:input path="searchValue" placeholder="이메일 or 이름 or 닉네임"/>
		<input type="submit" value="검색">
		<form:errors path="searchValue" cssClass="errors"/>
	</div>
	</form:form>
	<div id="memberListDiv">
		<table>
			<tr>
				<th>번호</th>
				<th>이메일</th>
				<th>이름</th>
				<th>닉네임</th>
				<th>가입일</th>
				<th>이메일인증여부</th>
				<th>보유포인트</th>
				<th>권한</th>
			</tr>
		<c:if test="${not empty members }">
			<c:forEach var="member" items="${members }">
			<tr>
				<td>${member.mNum }</td>
				<td>
					<a href='<c:url value="/admin/member/detail/${member.mNum }"/>'>${member.mEmail }</a>
				</td>
				<td>${member.mName }</td>
				<td>${member.mNickname }</td>
				<td>${member.mRegdate }</td>
				<td>${member.mIsvalid }</td>
				<td>${member.mPoint }</td>
				<td>
					<c:if test="${member.authLevel == 'ROLE_USER'}"><c:out value="일반사용자" /></c:if>
					<c:if test="${member.authLevel == 'ROLE_GUEST'}"><c:out value="무인증사용자" /></c:if>
				</td>
			</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty members }">
			<tr>
				<td colspan="7">등록된 사용자가 없습니다.</td>
			</tr>
		</c:if>
		</table>
	</div>
</article>

<br><a href="${pageContext.request.contextPath}/admin/home">관리자페이지로</a>
</body>
</html>