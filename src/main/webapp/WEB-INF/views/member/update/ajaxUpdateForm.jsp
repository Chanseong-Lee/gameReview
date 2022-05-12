<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<form:form commandName="updateCommand" method="post" enctype="multipart/form-data" onsubmit="return false;">
<%--ajax로 데이터를 넘겨줄 경우, submit 사용 안할경우 enter키를 누를경우 강제로 form페이지가 리로드되어버림.... onsubmit의 return false;로 방지 가능 --%>
<sec:authentication property='principal.profileImgname' var="profileImg" />
<c:set var="stringImg" value="unknown_profile.jpg" />
<table class="table">
	<tr>
		<td class="text-center" colspan="2">
			<div id="profileImgBox">
			<c:if test="${profileImg == 'unknown_profile.jpg'}">
				<img src="<c:url value='/resources/images/unknown_profile/${profileImg}' />" id="thumbnailImg" width="152">
			</c:if>
			<c:if test="${profileImg != 'unknown_profile.jpg'}">
				<img src="<c:url value='/images/profile/${profileImg}' />" id="thumbnailImg" width="152">
			</c:if>
			</div>
			<br>
			<label for="profileImg" id="uploadBtn">업로드</label>&nbsp;&nbsp;
			<input type="file" name="profileImg" id="profileImg" accept="image/jpeg, image/png ,image/gif" onChange="setTumbnail()" style="display: none;">
			<a id="backToBasicProfileImgBtn" onclick="basicImg()"><img id="backToBasicProfileImg" alt="프로필 사진 삭제" src="${pageContext.request.contextPath }/resources/images/profile_delete.png" width="17"> 기본 프로필사진으로 </a>
		</td>
	</tr>
	<tr>
		<td>이메일 계정</td>
		<td>
			<sec:authentication property="principal.username"/>
		</td>
	</tr>
	<tr>
		<td>이름</td>
		<td>
			<sec:authentication property="principal.name"/>
		</td>
	</tr>
	<tr>
		<td>닉네임</td>
		<td>
			<input class="form-control-sm" type="text" id="nickname" name="nickname" value="<sec:authentication property="principal.nickname"/>"><br>
			<span id="nicknameError"></span><form:errors path="nickname"></form:errors>
		</td>
	</tr>
</table>
<div id="success"></div>
<%--<input type="submit" id="submit" value="변경사항 저장"> --%>
<div class="text-center">
	<button class="btn btn-success btn-sm" type="button" id="update_btn" onclick="updateBtn()">변경사항 저장</button>&nbsp;&nbsp;
	<button class="btn btn-warning btn-sm" type="reset" onclick="resetThumbnail()">되돌리기</button>&nbsp;&nbsp;
	<button class="btn btn-danger btn-sm" type="button" onclick="location.href='${pageContext.request.contextPath}/member/update/profile'">프로필 화면으로</button>
</div>
</form:form>