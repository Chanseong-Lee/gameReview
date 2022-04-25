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
<h1>신규 아이템 등록</h1>
<div>
	<form:form commandName="addItemCommand" method="post" enctype="multipart/form-data">
		<table id="addForm">
			<tr>
				<td>아이콘 썸네일</td>
				<td>
					<img src="" id="thumbnailImg">
				</td>
			</tr>
			<tr>
				<td>아이템 이름</td>
				<td>
					<form:input path="itemName"/>
					<form:errors path="itemName"></form:errors>
				</td>
			</tr>
			<tr>
				<td>아이콘 이미지</td>
				<td>
					<input type="file" id="itemImg" name="itemImg" accept="image/jpeg, image/png" onchange="setTumbnail()">
					<form:errors path="itemImg"></form:errors>
				</td>
			</tr>
			<tr>
				<td>가격</td>
				<td>
					<form:input path="itemPrice" placeholder="숫자만 입력" pattern="[0-9]+"/>
					<form:errors path="itemPrice"></form:errors>
				</td>
			</tr>
		</table>
		<input type="submit" id="submit" value="등록">
		<button type="button" onclick="location.href='${pageContext.request.contextPath}/admin/item/itemList'">목록으로</button>
	</form:form>
</div>

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