<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<form:form commandName="updateCommand" method="post" onsubmit="return false;">
<table>
	<tr>
		<th colspan="2">
			비밀번호 수정
		</th>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td>
			<input type="password" id="password" name="password" placeholder="새 비밀번호 입력"><br>
			<span id="passwordErrorMsg"></span>
		</td>
	</tr>
	<tr>
		<td>비밀번호 확인</td>
		<td>
			<input type="password" id="confirmPassword" name="confirmPassword" placeholder="비밀번호 확인"><br>
			<span id="confirmPasswordErrorMsg"></span>
		</td>
	</tr>
</table><br>
<div id="success"></div><br>
<%--<input type="submit" id="submit" value="변경사항 저장"> --%>
<button type="button" id="submit" onclick="updatePwdBtn()">변경사항 저장</button>
<button type="button" onclick="location.href='${pageContext.request.contextPath}/member/update/profile'">프로필 화면으로</button>

</form:form>