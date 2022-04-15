<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<form:form commandName="updateCommand" method="post" onsubmit="return false;">
<%--ajax로 데이터를 넘겨줄 경우, submit 사용 안할경우 enter키를 누를경우 강제로 form페이지가 리로드되어버림.... onsubmit의 return false;로 방지 가능 --%>
<table>
	<tr>
		<td colspan="2">
			<div id="profileImgBox">
				이미지 추가할 예정
			</div>
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
			<input type="text" id="nickname" name="nickname" value="<sec:authentication property="principal.nickname"/>"><br>
			<span id="nicknameError"></span><form:errors path="nickname"></form:errors>
		</td>
	</tr>
</table>
<div id="success"></div>
<%--<input type="submit" id="submit" value="변경사항 저장"> --%>
<button type="button" id="update_btn" onclick="updateBtn()">변경사항 저장</button>
<button type="button" onclick="location.href='${pageContext.request.contextPath}/member/update/profile'">프로필 화면으로</button>
</form:form>