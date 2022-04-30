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
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>메인페이지</title>
<script src="https://kit.fontawesome.com/c965630904.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css">
<style type="text/css">
.nav-right{
	text-align: right;
}
</style>
</head>
<body>
<header class="header">
		<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		 	<div class="container">
		    	<a class="navbar-brand col-lg-2" href="${pageContext.request.contextPath }/game/main">
		    	<i style="width:30px;"  class="fas fa-gamepad "></i>
		    	<span>GAME REVIEW</span></a>
		    	<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
		    	</button>
		
				<div class="collapse navbar-collapse" id="navbarColor01">
			      	<ul class="navbar-nav col-lg-8 me-auto">
				        <li class="nav-item">
				        	<a class="nav-link active" href="${pageContext.request.contextPath }/game/main">Home
				            	<span class="visually-hidden">(current)</span>
				        	</a>
				        </li>
				        <li class="nav-item">
				          <a class="nav-link" href="#">Features</a>
				        </li>
				        <li class="nav-item">
				          <a class="nav-link" href="#">Pricing</a>
				        </li>
				        <li class="nav-item">
				          <a class="nav-link" href="#">About</a>
				        </li>
				        <li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Dropdown</a>
				      		<div class="dropdown-menu">
					            <a class="dropdown-item" href="#">Action</a>
					            <a class="dropdown-item" href="#">Another action</a>
					            <a class="dropdown-item" href="#">Something else here</a>
					            <div class="dropdown-divider"></div>
					            <a class="dropdown-item" href="#">Separated link</a>
				          </div>
				        </li>
					</ul>
		      
				<div class="col-lg-2">
		      		<div class="nav-right">
		      		<sec:authorize access="isAuthenticated()">
                    <ul class="navbar-nav ml-auto">
                       <li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
							<sec:authentication property='principal.profileImgname' var="profileImg" />
							<c:if test="${profileImg == 'unknown_profile.jpg'}">
								<img class="img-profile rounded-circle" src="<c:url value='/resources/images/unknown_profile/${profileImg}' />" id="profileImgTopBar" width="30">
							</c:if>
							<c:if test="${profileImg != 'unknown_profile.jpg'}">
								<img class="img-profile rounded-circle" src="<c:url value='/images/profile/${profileImg}' />" id="profileImgTopBar" width="30">
							</c:if>
								<span class="align-middle"><sec:authentication property="principal.nickname"/></span>
							
							</a>
				      		<div class="dropdown-menu">
					            <a class="dropdown-item" href="${pageContext.request.contextPath}/member/update/profile">프로필 보기</a>
					            <a class="dropdown-item" href="javascript:popUpInven()">인벤토리</a>
					            <a class="dropdown-item" href="javascript:popUpShop()">아이콘 상점</a>
					            <sec:authorize access="hasRole('ROLE_ADMIN')">
					            <div class="dropdown-divider"></div>
					            <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/home">관리자 페이지</a>
					            </sec:authorize>
					            <hr class="dropdown-divider">
					            <a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#logoutModal">
					             <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2"></i>
					             Logout
					            </a>
				          	</div>
			        	</li>
                    </ul>
		      		</sec:authorize>
		      
			   		<sec:authorize access="!isAuthenticated()">
			        <button class="btn btn-sm btn-success my-2 my-sm-0" type="button" onclick="location.href='${pageContext.request.contextPath}/member/loginForm'">로그인</button>
			        <button class="btn btn-sm btn-secondary my-2 my-sm-0" type="button" onclick="location.href='${pageContext.request.contextPath}/member/regist'">회원가입</button>
			        </sec:authorize>
		      	</div>
		      </div>
		    </div>
		  </div>
		</nav>
</header>

<section class="slider">
    <div class="container">
    	슬라이더
    </div>
</section>
<section class="main">
	<div class="container">
		여기서부터 게임 리스트 출력
	</div>
</section>

<%-- 
<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal.usingIcon" var="icon"/>
<c:if test="${icon == 'default_icon.png' }">
	<img alt="아이콘이미지" src="<c:url value='/resources/images/user_icon/${icon}'/>" width="15">
</c:if>
<c:if test="${icon == 'default_admin_icon.png' }">
	<img alt="아이콘이미지" src="<c:url value='/resources/images/user_icon/${icon}'/>" width="15">
</c:if>
<c:if test="${icon != 'default_icon.png' && icon != 'default_admin_icon.png'}">
	<img alt="아이콘이미지" src="<c:url value='/images/icons/${icon}'/>" width="15">
</c:if>
<sec:authentication property="principal.nickname"/>님 안녕하세요.<br><br>

<a href="${pageContext.request.contextPath}/member/update/profile">프로필보기</a><br>
<button type="button" onclick="popUpInven()">인벤토리</button><br>
<button type="button" onclick="popUpShop()">포인트 상점</button><br>
<a href="${pageContext.request.contextPath}/member/logout">로그아웃</a>
<sec:authorize access="hasRole('ROLE_ADMIN')">
<br><a href="${pageContext.request.contextPath}/admin/home">관리자페이지로</a>
</sec:authorize>
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
<a href="${pageContext.request.contextPath}/member/loginForm">로그인하기</a><br>
<a href="${pageContext.request.contextPath}/member/regist">회원가입하기</a>
</sec:authorize>
--%>
<br>


<!-- Modal -->
<div class="modal fade" id="logoutModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">로그아웃</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        정말 로그아웃 하시겠습니까?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
        <button type="button" class="btn btn-danger" onclick="location.href='${pageContext.request.contextPath}/member/logout'">Logout</button>
      </div>
    </div>
  </div>
</div>
<sec:authorize access="isAuthenticated()">
<script type="text/javascript">
function popUpInven(){
	let url = "${pageContext.request.contextPath}/member/inventory";
	let name = "인벤토리";
	let option = "width = 550, height = 300, top = 100, left = 200, location = no";
	window.open(url, name, option);
}
function popUpShop(){
	let url = "${pageContext.request.contextPath}/shop/";
	let name = "포인트상점";
	let option = "width = 550, height = 300, top = 100, left = 200, location = no";
	window.open(url, name, option);
}
</script>
</sec:authorize>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>