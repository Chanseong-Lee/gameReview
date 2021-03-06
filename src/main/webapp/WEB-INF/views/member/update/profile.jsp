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
<script src="https://kit.fontawesome.com/c965630904.js" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css2?family=Oswald:wght@300;400;500;600;700&amp;display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css">
<style>
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
<div class="container p-4">
	<div class="text-center p-2">
		<h1>회원 프로필</h1>
	</div>
	<sec:authentication property='principal.profileImgname' var="profileImg" />
	<sec:authentication property="principal.usingIcon" var="icon"/>
	<table class="table">
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
			<td><sec:authentication property="principal.username"/></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><sec:authentication property="principal.name"/></td>
		</tr>
		<tr>
			<td>닉네임</td>
			<td>
				<c:if test="${icon == 'default_icon.png' }">
					<img alt="아이콘이미지" src="<c:url value='/resources/images/user_icon/${icon}'/>" width="15">
				</c:if>
				<c:if test="${icon == 'default_admin_icon.png' }">
					<img alt="아이콘이미지" src="<c:url value='/resources/images/user_icon/${icon}'/>" width="15">
				</c:if>
				<c:if test="${icon != 'default_icon.png' && icon != 'default_admin_icon.png'}">
					<img alt="아이콘이미지" src="<c:url value='/images/icons/${icon}'/>" width="15">
				</c:if>
				<sec:authentication property="principal.nickname"/>
			</td>
		</tr>
		<tr>
			<td>가입일</td>
			<td><sec:authentication property="principal.regdate" var="regdate"/><fmt:formatDate value="${regdate }" pattern="yyyy-MM-dd"/></td>
		</tr>
		<tr>
			<td>보유포인트</td>
			<td><sec:authentication property="principal.point"/></td>
		</tr>
	</table>
	<div class="text-center">
		<button class="btn btn-success btn-sm" type="button" onclick="popUpAuth()">회원정보 수정</button>&nbsp;&nbsp;
		<button class="btn btn-danger btn-sm" type="button" onclick="btnClose()">창닫기</button>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script type="text/javascript">
function popUpAuth(){
	let url = "${pageContext.request.contextPath}/member/update/authForm";
	let name = "업데이트 전 인증";
	let option = "width = 550, height = 300, top = 100, left = 200, location = no";
	window.open(url, name, option);
}
function btnClose(){
	opener.parent.location.reload();
	self.close();
}
</script>
</body>
</html>