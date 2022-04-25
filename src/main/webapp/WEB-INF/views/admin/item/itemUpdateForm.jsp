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
<title>ADMIN</title>
</head>
<body>
<form:form commandName="addItemCommand" method="post" enctype="multipart/form-data">
<table>
	<tr>
		<td>아이템이미지</td>
		<td>
		<c:if test="${item.itemNum == 1 }">
			<img id="thumbnailImg" src="<c:url value='/resources/images/user_icon/${item.itemFilename}'/>">
		</c:if>
		<c:if test="${item.itemNum != 1 }">
			<img id="thumbnailImg" src="<c:url value='/images/icons/${item.itemFilename}'/>">
		</c:if>
		</td>
	</tr>
	<tr>
		<td>아이템이름</td>
		<td>
			<input type="text" id="itemName" name="itemName" value="${item.itemName }"><br>
			<form:errors path="itemName"></form:errors>
		</td>
	</tr>
	<tr>
		<td>아이템 이미지</td>
		<td>
			<input type="file" id="itemImg" name="itemImg" accept="image/jpeg, image/png" onchange="setTumbnail()"><br>
			<form:errors path="itemImg"></form:errors>
		</td>
	</tr>
	<tr>
		<td>아이콘가격</td>
		<td>
			<input type="text" id="itemPrice" pattern="[0-9]+" name="itemPrice" value="${item.itemPrice }"><br>
			<form:errors path="itemPrice"></form:errors>	
		</td>
	</tr>
	<tr>
		<td>아이콘등록일</td>
		<td>${item.itemRegdate }</td>
	</tr>
</table>
<input type="submit" value="수정 완료">
<button type="button" onclick="location.href='${pageContext.request.contextPath}/admin/item/detail/${item.itemNum}'">뒤로가기</button>
</form:form>

<script type="text/javascript">
function setTumbnail(){
	let fileInfo = document.getElementById("itemImg").files[0];
	
	let reader = new FileReader();
	
	if(fileInfo){
		reader.readAsDataURL(fileInfo);
	}
	
	reader.onload = function(e){
		document.getElementById("thumbnailImg").src = reader.result;
		
		var img = new Image();      
        img.src = e.target.result;
        
        img.onload = function () {
            var w = this.width;
            var h = this.height;
			console.log(w);
			console.log(h);
			if(w > 65 || h > 65){
				alert("아이콘 이미지는 최대 65X65 입니다.");
				document.getElementById("thumbnailImg").src="";
				document.getElementById("itemImg").value=null;
			}
        }
	}
}
</script>
</body>
</html>