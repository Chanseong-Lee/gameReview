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

<title>LCK CRITIC</title>
<script src="https://kit.fontawesome.com/c965630904.js" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css2?family=Oswald:wght@300;400;500;600;700&amp;display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css">
<style type="text/css">
.nav-right{
	text-align: right;
	padding-right: 12px;
}
.slider {
    padding-top: 50px;
}
.main {
	padding-top: 50px;
	padding-bottom: 50px;
}
.carousel-inner {
	border-radius: 0px 0px 20px 20px;
}
.carousel-caption{
	left: 50px;
    bottom: 50px;
    text-align: left;
}
.carousel-caption h2{
	color: #ffffff;
    font-size: 42px;
    font-family: "Oswald", sans-serif;
    font-weight: 700;
}
.silde-textbox {
	width : 550px;
	padding : 20px;
	background-color: rgba(1, 1, 1, 0.7);
	border-radius: 20px;
}

.sliderImg{
	height: 500px;
    object-fit: cover;
}

.display-2{
	font-weight: bold;
}
.display-3{
	font-weight: bold;
}
.project-name{
	font-family: fantasy;
	letter-spacing: 1px;
}
.slider-title{
	text-align: center;
	padding-bottom: 10px;
	margin-top: 54px;
	background-color: rgba(1, 1, 1, 0.15);
	border-radius: 20px 20px 0px 0px;
}
.main-title{
	text-align: center;
	padding-bottom: 10px;
	background-color: rgba(1, 1, 1, 0.15);
	border-radius: 20px;
}
.game-list{
	height:130px;
	padding: 10px;
	background-color: rgba(1, 1, 1, 0.15);
	border-radius: 5px;
	margin-top: 5px;
	margin-bottom: 5px;
}
.game-list:hover {
	opacity: 0.8;
	cursor: pointer;
}
.mainImg{
	height:100%;
	width:100%;
	object-fit: cover;
}
.avg-score {
	font-weight: bold;
    font-style: italic;
    padding: 0px 2px 15px 2px;
}
.avg-div{
	font-style: italic;
	text-align:center;
	color:white;
	width:75px;
	height:30px;
	border-radius: 5px;
	background-color: #6c3;
	padding: 3px;
	margin-top: 29%;
}
.go-review{
	z-index: 8;
    position: relative;
}
.footer{
	background: #070720;
    position: relative;
}
</style>
</head>
<body data-bs-spy="scroll" data-bs-target="#main-nav" data-bs-offset="0" class="scrollspy-example" tabindex="0">
<!-- nav bar -->
<header class="header">
		<nav id="main-nav" class="navbar fixed-top navbar-expand-lg navbar-dark bg-primary">
		 	<div class="container">
		    	<a class="navbar-brand col-lg-2" href="${pageContext.request.contextPath }/game/main">
			    	<i style="width:30px;"  class="fas fa-gamepad "></i>
			    	<span class="project-name">LCK CRITIC</span>
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
					            <a class="dropdown-item" href="javascript:popUpProfile()">프로필 보기</a>
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

<!-- slider -->
<section class="slider" id="slider-section">
    <div class="container"">
    	<div class="slider-title">
	    	<h1 class="display-3">Top 3</h1>
    	</div>
			<div id="carouselTopThree" class="carousel slide" data-bs-ride="carousel">
			  <div class="carousel-indicators">
			    <button type="button" data-bs-target="#carouselTopThree" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
			    <button type="button" data-bs-target="#carouselTopThree" data-bs-slide-to="1" aria-label="Slide 2"></button>
			    <button type="button" data-bs-target="#carouselTopThree" data-bs-slide-to="2" aria-label="Slide 3"></button>
			  </div>
			<div class="carousel-inner">
			    <div class="carousel-item active">
			   		<div class="row">
			      		<img src="${pageContext.request.contextPath }/resources/images/slider/eldenring.jpeg" class="sliderImg d-block w-100" alt="슬라이드이미지">
				      	<div class="carousel-caption d-none d-md-block d-lg-block">
					      	<div class="silde-textbox">
						      	<div class="slider_genre p-2">
						      		<span class="badge bg-primary">RPG</span>
						      		<span class="badge bg-primary">액션</span>
						        </div>
						        <h2>엘든링</h2>
						        <p>본격적인 다크 판타지 세계를 무대로 한 액션 RPG입니다.</p>
						        <div class="avg-score">9.5/10</div>
						      	<button type="button" class="go-review btn btn-danger">GO REVIEW</button>
				      		</div>
				      	</div>
			     	</div>
			    </div>
			    <div class="carousel-item">
			   		<div class="row">
			     	 	<img src="${pageContext.request.contextPath }/resources/images/slider/civil.jpg" class="sliderImg d-block w-100" alt="슬라이드이미지">
				      	<div class="carousel-caption d-none d-md-block">
					      	<div class="silde-textbox">
						      	<div class="slider_genre p-2">
						      		<span class="badge bg-primary">전략</span>
						      		<span class="badge bg-primary">턴제</span>
						        </div>
						        <h2>SID MEIER’S CIVILIZATION® VI</h2>
						        <p>문명 VI 에서는 새로운 방식으로 세상과 소통하고, 제국의 영토를 확장시키며, 문화를 발전시키고, 역사속 위대한 지도자들에 맞서 오래도록 지속될 나만의 문명을 건설해볼 수 있습니다. 클레오파트라를 비롯한 20명의 역사 속 지도자들 중 한명으로 플레이 해보세요.</p>
						      	<div class="avg-score">9.5/10</div>
						      	<button type="button" class="go-review btn btn-danger">GO REVIEW</button>
				      		</div>
				      	</div>
			     	</div>
			    </div>
			    <div class="carousel-item">
			   		<div class="row">
			      		<img src="${pageContext.request.contextPath }/resources/images/slider/rainbowsix.jpg" class="sliderImg d-block w-100" alt="슬라이드이미지">
				      	<div class="carousel-caption d-none d-md-block">
					      	<div class="silde-textbox">
						      	<div class="slider_genre p-2">
						      		<span class="badge bg-primary">FPS</span>
						      		<span class="badge bg-primary">액션</span>
						        </div>
						        <h2>Tom Clancy's Rainbow Six® Siege</h2>
						        <p>Tom Clancy's Rainbow Six Siege는 명성이 자자한 FPS 시리즈의 최신작으로, Ubisoft Montreal 스튜디오에서 개발하였습니다.</p>
						      	<div class="avg-score">9.5/10</div>
						      	<button type="button" class="go-review btn btn-danger">GO REVIEW</button>
				      		</div>
				      	</div>
			     	</div>
			  </div>
			  <button class="carousel-control-prev" type="button" data-bs-target="#carouselTopThree" data-bs-slide="prev">
			    <div class="slider-mover">
			    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
			    <span class="visually-hidden">Previous</span>
			  	</div>
			  </button>
			  <button class="carousel-control-next" type="button" data-bs-target="#carouselTopThree" data-bs-slide="next">
			   	<div class="slider-mover">
			    <span class="carousel-control-next-icon" aria-hidden="true"></span>
			    <span class="visually-hidden">Next</span>
			  	</div>
			  </button>
			</div>
    </div>
    </div>
</section>
<section class="main mb-5" id="main-section">
	<div class="container">
		<div class="main-title mb-4">
			<h1 class="display-2">GAME LIST</h1>
		</div>
		<div>
			<div class="row">
				<div class="col-8">
					<!-- 여기 포문 -->
					<c:forEach var="game" items="${games }">
					<div class="game-list" onclick="location.href='<c:url value='/game/gameDetail/${game.gNum }'/>'">
						<div class="row">
							<div class="col-3">
							<c:forEach var="gameFile" items="${files }">
								<c:if test="${game.gNum == gameFile.gNum && gameFile.gfLocation == 1}">
								<img class="mainImg" src='<c:url value="/images/games/${game.gCode }/${gameFile.gfSavedfilename }"/>'>
								</c:if>
							</c:forEach>	
							</div>
							<div class="col-7 p-3">
								<h4> ${game.gName }</h4>
								<div class="list_genre" >
									<c:forEach var="genre" items="${genres }">
						      			<c:if test="${genre.gNum == game.gNum }">
						      		<span class="badge bg-primary">${genre.genName }</span>
						      			</c:if>
						        	</c:forEach>
						        </div>
							</div>
							<div class="col-2">
								<div class="avg-div">${game.gAvg }/10</div>
							</div>
						</div>
					</div>
					</c:forEach>
					<!-- 여기 포문 -->
				</div>
				<div class="col-4">
					슬라이더이미지 세로로
				</div>
			</div>
		</div>
	</div>
</section>

<footer class="footer bg-primary">
	<div class="container">
		<div class="row">
			<div class="col-lg-3 p-4">
				<i style="width:30px;"  class="fas fa-gamepad "></i>
			    <span style="font-size: 1.25rem;" class="project-name">LCK CRITIC</span>
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
				 <span>Copyright &copy; LCK CRITIC 2022</span>
			</div>
		</div>
	</div>
</footer>



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
function popUpProfile(){
	let url = "${pageContext.request.contextPath}/member/update/profile";
	let name = "프로필";
	let option = "width = 550, height = 550, top = 100, left = 200, location = no";
	window.open(url, name, option);
}
</script>
</sec:authorize>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>