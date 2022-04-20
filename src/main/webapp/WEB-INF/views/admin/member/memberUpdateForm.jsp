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
#uploadBtn{
 	padding: 3px 20px;
  	background-color:#FF6600;
  	border-radius: 4px;
  	color: white;
  	cursor: pointer;
}

#backToBasicProfileImgBtn{
  	padding: 3px 10px;
  	background-color:#FF6600;
  	border-radius: 4px;
  	color: white;
  	cursor: pointer;
	
}
</style>
</head>
<body>
<h1>회원 정보</h1>
<article>
<form:form commandName="updateCommand" method="post" enctype="multipart/form-data">
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
			<br>
			<label for="profileImg" id="uploadBtn">업로드</label>
			<input type="file" name="profileImg" id="profileImg" accept="image/jpeg, image/png ,image/gif" onChange="setTumbnail()" style="display: none;">
			<a id="backToBasicProfileImgBtn" onclick="basicImg()"><img id="backToBasicProfileImg" alt="프로필 사진 삭제" src="${pageContext.request.contextPath }/resources/images/profile_delete.png" width="17"> 기본 프로필사진으로 </a>
			<br><form:errors path="profileImg"></form:errors>
		</td>
	</tr>
	<tr>
		<td>이메일 계정</td>
		<td>${member.mEmail}</td>
	</tr>
	<tr>
		<td>이름</td>
		<td>
			<input type="text" id="name" name="name" value="${member.mName }"><br>
			<span id="nameError"></span><form:errors path="name"></form:errors>
		</td>
	</tr>
	<tr>
		<td>닉네임</td>
		<td>
			<input type="text" id="nickname" name="nickname" value="${member.mNickname }"><br>
			<span id="nicknameError"></span><form:errors path="nickname"></form:errors>
		</td>
	</tr>
	<tr>
		<td>가입일</td>
		<td><fmt:formatDate value="${member.mRegdate }" pattern="yyyy-MM-dd"/></td>
	</tr>
	<tr>
		<td>보유포인트</td>
		<td>
			<input type="number" id="point" name="point" value="${member.mPoint}"><br>
			<span id="pointError"></span><form:errors path="point"></form:errors>
		</td>
	</tr>
	<tr>
		<td>권한</td>
		<td><c:if test="${member.authLevel == 'ROLE_USER' }"><c:out value="일반사용자" /></c:if></td>
	</tr>
</table>
<input type="hidden" id="backToBasicImg" name="backToBasicImg" value="false">
<br>
<input type="submit" onclick="location.href='${pageContext.request.contextPath}/admin/member/memberList'" value="수정완료">&nbsp;&nbsp;
<button type="button" onclick="location.href='${pageContext.request.contextPath}/admin/member/detail/${member.mNum }'">뒤로가기</button>
</form:form>

</article>

<script>
let backToBasicImg = document.querySelector("#backToBasicImg").value;
let nicknameChecker = 0;
function setTumbnail(){
	let fileInfo = document.getElementById("profileImg").files[0];
	let reader = new FileReader();
		
	reader.onload = function(){
		document.getElementById("thumbnailImg").src = reader.result;
	}
	if(fileInfo){
		reader.readAsDataURL(fileInfo);
		backToBasicImg = false;
	}
	
}

function isNickname(asValue) {
	var regExp = /^[a-zA-Z0-9ㄱ-힣][a-zA-Z0-9ㄱ-힣]*$/;
	//한글 영어 숫자
	return regExp.test(asValue);
}

function basicImg(){
	console.log("backToBasicImg : " + backToBasicImg)
	backToBasicImg = confirm("기본 프로필 사진으로 돌아가시겠습니까?");
	console.log("backToBasicImg : " + backToBasicImg)
	document.querySelector("#backToBasicImg").value = true;
	if(backToBasicImg){
		document.getElementById("thumbnailImg").src = "${pageContext.request.contextPath}/resources/images/unknown_profile/unknown_profile.jpg";
		document.querySelector("#profileImg").value = null;
	}
}
</script>
</body>
</html>