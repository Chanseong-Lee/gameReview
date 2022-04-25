<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

td{
	border: 1px solid gray;
	padding: 5px 5px 5px 5px;
	
}
#prifileImgBox {
	border: 1px solid black;
    width: 150px;
    height: 150px; 
    border-radius: 70%;
    overflow: hidden;
    margin: auto;
}

</style>
</head>
<body>
<h1>회원 정보</h1>
<article>
<table>
	<tr>
		<td colspan="2">
			<div id="prifileImgBox">
			
			<c:if test="${profileImg == 'unknown_profile.jpg'}">
				<img src="<c:url value='/resources/images/unknown_profile/${profileImg}' />" id="thumbnailImg" width="152">
			</c:if>
			<c:if test="${profileImg != 'unknown_profile.jpg'}">
				<img src="<c:url value='/images/profile/${profileImg}' />" id="thumbnailImg" width="152">
			</c:if>
			</div>
		</td>
	</tr>
	<tr>
		<td>이메일 계정</td>
		<td>${member.mEmail}</td>
	</tr>
	<tr>
		<td>이름</td>
		<td>${member.mName}</td>
	</tr>
	<tr>
		<td>닉네임</td>
		<td>${member.mNickname}</td>
	</tr>
	<tr>
		<td>가입일</td>
		<td><fmt:formatDate value="${member.mRegdate }" pattern="yyyy-MM-dd"/></td>
	</tr>
	<tr>
		<td>보유포인트</td>
		<td>${member.mPoint}</td>
	</tr>
	<tr>
		<td>권한</td>
		<td><c:if test="${member.authLevel == 'ROLE_USER' }"><c:out value="일반사용자" /></c:if></td>
	</tr>
</table>
<br>
<button type="button" onclick="location.href='${pageContext.request.contextPath}/admin/member/memberUpdate/${member.mNum }'">회원정보 수정하기</button>
<button type="button" onclick="deleteMember()">강제탈퇴</button>
<button type="button" onclick="location.href='${pageContext.request.contextPath}/admin/member/memberList'">목록으로</button>
</article>
<script>
function deleteMember(){
	let flag = confirm('정말로 탈퇴시키시겠습니까?');
	if(flag){
		location.href='${pageContext.request.contextPath}/admin/member/memberDelete/${member.mNum }';
	}
}
</script>
</body>
</html>