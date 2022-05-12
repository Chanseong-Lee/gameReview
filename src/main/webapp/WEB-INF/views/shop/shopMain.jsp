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
<script src="https://kit.fontawesome.com/c965630904.js" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css2?family=Oswald:wght@300;400;500;600;700&amp;display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css">
<style type="text/css">
.item{
	width : 300px;
}
.imgContainer{
	text-align: center;
	width : 80px;
}
</style>
</head>
<body>
<div class="container">
	<div class="text-center">
		<h1>아이템 상점</h1>
		<div id="memberPoint">
			<p>보유포인트 : <sec:authentication property="principal.point"/> </p>
		</div>
	</div>
	<div class="text-center" id="itemList">
		<c:forEach var="item" items="${items }">
		<div class="card text-white bg-primary mx-auto mb-3" style="max-width: 20rem;">
			<div class="card-header">${item.itemName }</div>
			<div class="card-body">
				<table class="item">
					<tr>
						<td rowspan="3" class="imgContainer">
						<c:if test="${item.itemNum == 1 }">
							<img alt="아이콘이미지" src="<c:url value='/resources/images/user_icon/${item.itemFilename}'/>" width="30">
						</c:if>
						<c:if test="${item.itemNum == 6 }">
							<img alt="아이콘이미지" src="<c:url value='/resources/images/user_icon/${item.itemFilename}'/>" width="30">
						</c:if>
						<c:if test="${item.itemNum != 1 && item.itemNum != 6}">
							<img alt="아이콘이미지" src="<c:url value='/images/icons/${item.itemFilename}'/>" width="30">
						</c:if>
						</td>
					</tr>
					<tr>
						<td>가격</td>
						<td>${item.itemPrice }</td>
					</tr>
					<tr>
						<td>
						<c:if test="${item.isSold == 'Y' }">
							<span style="color:red;">구매함</span>
						</c:if>
						<c:if test="${item.isSold == 'N' }">
							<span style="color:green;">구매가능</span>
						</c:if>
						</td>
						<td>
						<c:if test="${item.isSold == 'Y' }">
							<button class="btn btn-success btn-sm" type="button" onclick="purchase()" disabled="disabled">구매하기</button>
						</c:if>
						<c:if test="${item.isSold == 'N' }">
							<button class="btn btn-success btn-sm" type="button" onclick="purchase(${item.itemNum})">구매하기</button>
						</c:if>
						</td>
					</tr>
				</table>
			</div>
		</div>
		</c:forEach>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script>
function purchase(num){
	let flag = confirm("구매하시겠습니까?");
	if(flag){
		window.location.href="${pageContext.request.contextPath}/shop/purchase/"+num;
	}
}
</script>
</body>
</html>