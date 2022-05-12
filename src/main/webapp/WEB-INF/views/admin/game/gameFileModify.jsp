<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게임 파일 수정</title>
</head>
<body>
	<h1>${game.gName}게임파일수정</h1>

	<form:form action="${pageContext.request.contextPath }/admin/game/gameFileModify/${game.gNum}"
		commandName="modifyGameFile" name="form" method="post" enctype="multipart/form-data">
		
		<input type="hidden" value="${game.gNum}" name="gNum" />
		<input type="hidden" value="${game.gName}" name="gName" />
		<input type="hidden" value="${game.gCode}" name="gCode" />
	
		<table border="1">
			<tr>
				<th>메인 사진</th>
			</tr>
			<tr>
				<td colspan="1">
					<c:forEach var="gameFile"
						items="${gameFilesList}">
						<c:if test="${gameFile.gfLocation == 1}">
					
							<c:if test="${game.gNum == gameFile.gNum}">

								<img src="<c:url value="/images/games/${game.gCode }/${gameFile.gfSavedfilename }"/>" width="300">
							</c:if>
						</c:if>
					</c:forEach>
				</td>
			</tr>
		</table>

		<td>
			<input type="file" name="imgFile">
			<form:errors path="imgFile"/>
			<input type="submit" value="메인사진 수정" onclick='btn_click("mupdate");'>
			<input type="button" value="게임 목록" onclick="location.href='<c:url value="/list"/>'">
			<table border="1">
				<tr>
					<th>슬라이드 사진</th>
				</tr>

				<tr>
					<td colspan="1">
						<c:forEach var="gameFile" items="${gameFilesList}">
							<c:if test="${gameFile.gfLocation == 2}">
								<fieldset>
									<label for="gfNum${gameFile.gfNum }" onclick="if(navigator.appVersion.indexOf('MSIE') != -1){gfNum.click()}">
										<input type="checkbox" name="gfNum" id="gfNum${gameFile.gfNum }" value="${gameFile.gfNum }">
										 <img src="<c:url value="/images/games/${game.gCode }/${gameFile.gfSavedfilename }"/>" height="300" width="650">
									</label>
								</fieldset>
							</c:if>
						</c:forEach>
					</td>
				</tr>
			</table> 
			
			<input type="file" name="slideImgFile" placeholder="슬라이드 파일" multiple /> 
			<input type="submit" value="슬라이드사진 추가" onclick='btn_click("dupdate");'> 
			<input type="submit" value="슬라이드사진 삭제" onclick='btn_click("ddelete");'> 
			<input type="button" value="게임 목록" onclick="location.href='<c:url value="/admin/game/gameList"/>'">
	</form:form>
	
<script type="text/javascript">
	function btn_click(str) {
		if (str == "mupdate") {
			form.action = "${pageContext.request.contextPath }/admin/game/mainFileUpdate/${game.gNum}";
		} else if (str == "dupdate") {
			form.action = "${pageContext.request.contextPath }/admin/game/slideFileUpdate/${game.gNum}";
		} else if (str == "ddelete") {
			form.action = "${pageContext.request.contextPath }/admin/game/slideFileDelete/${game.gNum}";
		}
	}
</script>
</body>
</html>