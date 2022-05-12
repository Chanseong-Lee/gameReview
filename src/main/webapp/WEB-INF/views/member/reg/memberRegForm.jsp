<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원가입</title>
<script src="https://kit.fontawesome.com/c965630904.js" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css2?family=Oswald:wght@300;400;500;600;700&amp;display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css">
<style>
.project-name{
	font-family: fantasy;
	letter-spacing: 1px;
}
.nav-right{
	text-align: right;
	padding-right: 12px;
}

#main-thumbnail-img{
	position:relative;
	margin-top:60px;
	object-fit: cover;
	opacity: 0.3;
	
}
.text-in-img {
	position: absolute;
    top: 332px;
    left: 50%;
    transform: translate(-50%, -50px);
	
}
.text-in-img h2 {
	color: #ffffff;
    font-size: 48px;
    font-family: "Oswald", sans-serif;
    font-weight: 700;
}

.text-in-img p {
	color: #ffffff;
    font-size: 24px;
}

#img-box1{
	
}
.img-wrap{
	height: 443px;
	overflow: hidden;
}

.reg-section{
	margin-bottom: 100px;
}
.login_form {
	padding-left: 100px;
}
.login_form h1{
    font-size: 48px;
    font-family: "Oswald", sans-serif;
    font-weight: 700;
}
.form-control {
	width: 87%;
}
.go-login {
	padding-left: 68px;
}
.go-login h1 {
	font-size: 40px;
    font-family: "Oswald", sans-serif;
    font-weight: 700;
}
.footer{
	background: #070720;
    position: relative;
}
.errorAfterSubmit{
	color:red;
}
</style>

</head>
<body>
<!-- nav bar -->
<header class="header">
		<nav id="main-nav" class="navbar fixed-top navbar-expand-lg navbar-dark bg-primary">
		 	<div class="container">
		    	<a class="navbar-brand col-lg-2" href="${pageContext.request.contextPath }/game/main">
			    	<i style="width:30px;"  class="fas fa-gamepad "></i>
			    	<span class="project-name">GAME REVIEW</span>
		    	</a>
		    	<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
		    	</button>
		
				<div class="collapse navbar-collapse" id="navbarColor01">
			      	<ul class="navbar-nav col-lg-8 me-auto">
				        <li class="nav-item bg-secondary bg-gradient" style="width:67px; text-align: center;" >
				        	<a class="nav-link" href="${pageContext.request.contextPath }/game/main" style="height:59px; ">Home
				            	<span class="visually-hidden">(current)</span>
				        	</a>
				        </li>
				        <li class="nav-item" style="width:67px; text-align: center;">
				          <a class="nav-link" href="#slider-section">Top3</a>
				        </li>
				        <li class="nav-item" style="width:67px; text-align: center;">
				          <a class="nav-link" href="#main-section">Review</a>
				        </li>
				        <li class="nav-item" style="width:67px; text-align: center;">
				          <a class="nav-link" href="#">About</a>
				        </li>
					</ul>
		      
				<div class="col-lg-2">
		      		<div class="nav-right">
		      		<sec:authorize access="isAuthenticated()">
                    <ul class="navbar-nav justify-content-end">
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
					            <h6 class="dropdown-header">Admin</h6>
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
<section class="main-thumbnail" id="main-thumbnail-section">
	<div class="container" id="img-box1">
		<div class="row">
			<div class="img-wrap text-center">
			<img src="${pageContext.request.contextPath }/resources/images/slider/witcher3.png" class="img-fluid"  id="main-thumbnail-img">
				<div class="text-in-img text-center">
					<div id="box">
						<h2>Login</h2>
						<p>WELCOME TO GAME REVIEW.</p>
					</div>
				</div>		
			</div>
		</div>
	</div>
</section>


<!-- 회원가입 폼 -->
<section class="reg-section">
<div class="container">
	<div class="row">
		<div class="login_form col-lg-6 border-end border-primary">
			<h1>회원가입</h1>
			<form:form action="${pageContext.request.contextPath}/member/regist" commandName="mrc" method="post">
				<div class="form-floating mt-4">
					<form:input id="email" class="form-control" path="email" oninput="checkEmail()" autocomplete='off'/>
					<label for="email">Email address</label>
					<span class="errorAfterSubmit"><form:errors path="email" /></span>
					<span id="emailError"></span>
				</div>
				<div class="form-floating mt-4">
					<form:input id="name" class="form-control" path="name" oninput="checkName()" autocomplete='off'/>
					<label for="name">Name *이름은 수정이 불가능 하오니 정확히 기입하세요.</label>
					<span class="errorAfterSubmit"><form:errors path="name" /></span>
					<span id="nameError"></span>
				</div>
				<div class="form-floating mt-4">	
					<form:input id="nickname" class="form-control" path="nickname" oninput="checkNickname()" autocomplete='off'/>
					<label for="nickname">Nickname</label>
					<span class="errorAfterSubmit"><form:errors path="nickname" /></span>
					<span id="nicknameError"></span>
				</div>
				<div class="form-floating mt-4">	
					<form:password id="password" class="form-control" path="password" oninput="checkPw()" autocomplete='off'/>
					<label for="password">Password</label>
					<span class="errorAfterSubmit"><form:errors path="password" /></span>
				</div>
				<div class="form-floating mt-4">	
					<form:password id="confirmPassword" class="form-control" path="confirmPassword" oninput="checkPw()" autocomplete='off'/>
					<label for="confirmPassword">Confirm Password</label>
					<span class="errorAfterSubmit"><form:errors path="confirmPassword" /></span>
					<span id="noMatchErr"></span>
				</div>
				<div class="d-grid gap-2 col-10 mt-4 ms-2">
					<input type="submit" class="btn btn-success btn-lg" id="submit" value="회원가입">
					<input type="button" class="btn btn-secondary btn-lg" value="메인으로" onclick="location.href='${pageContext.request.contextPath}/game/main'">
				</div>
			</form:form>
		</div>
		<div class="col-lg-6">
			<div class="go-login">
				<h1>이미 계정을 갖고 계신가요?</h1>
				<div class="d-grid gap-2 col-9 mt-4 ms-2">
					<button class="btn btn-lg btn-danger" type="button" onclick="location.href='${pageContext.request.contextPath}/member/loginForm'">로그인하러 가기</button>
				</div>
			</div>
		</div>
	</div>
</div>
</section>
	
	
<footer class="footer bg-primary fixed-bottom">
	<div class="container">
		<div class="row">
			<div class="col-lg-3 p-4">
				<i style="width:30px;"  class="fas fa-gamepad "></i>
			    <span style="font-size: 1.25rem;" class="project-name">GAME REVIEW</span>
			</div>
			<div class="col-lg-6 p-4">
				<ul class="nav justify-content-center">
				        <li class="nav-item" style="width:80px; text-align: center;" >
				        	<a class="nav-link" href="${pageContext.request.contextPath }/game/main">Home</a>
				        </li>
				        <li class="nav-item" style="width:80px; text-align: center;">
				          <a class="nav-link" href="#slider-section">Top3</a>
				        </li>
				        <li class="nav-item" style="width:80px; text-align: center;">
				          <a class="nav-link" href="#main-section">Review</a>
				        </li>
				        <li class="nav-item" style="width:80px; text-align: center;">
				          <a class="nav-link" href="#">About</a>
				        </li>
					</ul>
			</div>
			<div class="col-lg-3 p-4">
				 <span>Copyright &copy; GAME REVIEW 2022</span>
			</div>
		</div>
	</div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/ajaxForEmail.js"></script>

</body>
</html>