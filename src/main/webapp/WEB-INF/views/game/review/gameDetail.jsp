<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>게임리뷰 등록</title>
<script src="https://kit.fontawesome.com/c965630904.js" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css2?family=Oswald:wght@300;400;500;600;700&amp;display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css">
<style>
.nav-right{
	text-align: right;
	padding-right: 12px;
}
.main {
	margin-top:50px;
	padding-top: 50px;
	padding-bottom: 50px;
}
.sliderImg{
	height: 470px;
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
.footer{
	background: #070720;
    position: relative;
}
.star {
	position: relative;
	font-size: 2rem;
	color: #ddd;
}

.star input {
	width: 100%;
	height: 100%;
	position: absolute;
	left: 0;
	opacity: 0;
	cursor: pointer;
}

.star span {
	width: 0;
	position: absolute;
	left: 0;
	color: yellow;
	overflow: hidden;
	pointer-events: none;
}
.fieldset {
	border:wihte solid 1px;
}
.table {
	border-style: none;
}
.table tr {
	border-style: none;
}
.table tr td {
	padding:20px;
	border-style: none;
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
<section class="main mb-5" id="main-section">
	<div class="container" style="background-color: rgba(1, 1, 1, 0.15);">
		<div class="row">
			<h1 class="py-3 px-5" style="font-family: 'Oswald', sans-serif;">${game.gName }</h1>
			<div class="col-8 px-3">
				<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
				  <div class="carousel-indicators">
				    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
				    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
				    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
				  </div>
				  <div class="carousel-inner">
				  	<c:forEach var="file" items="${files }" varStatus="status">
				  	<c:if test="${status.last == true}">
				  	<div class="carousel-item active">
				      <img src='<c:url value="/images/games/${game.gCode }/${file.gfSavedfilename }"/>' class="sliderImg d-block w-100" alt="...">
				    </div>
				  	</c:if>
				  	<c:if test="${status.last != true}">
				  	<div class="carousel-item">
				      <img src='<c:url value="/images/games/${game.gCode }/${file.gfSavedfilename }"/>' class="sliderImg d-block w-100" alt="...">
				    </div>
				    </c:if>
				  	</c:forEach>
				  </div>
				  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
				    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
				    <span class="visually-hidden">Previous</span>
				  </button>
				  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
				    <span class="carousel-control-next-icon" aria-hidden="true"></span>
				    <span class="visually-hidden">Next</span>
				  </button>
				</div>
			</div>
			<div class="col-4 px-3">
				<div class="d-block">
				<c:forEach var="file" items="${files }">
				<c:if test="${file.gfLocation == 1 }">
					<img class="img-fluid" src="<c:url value="/images/games/${game.gCode }/${file.gfSavedfilename }"/>">
				</c:if>
				</c:forEach>
				</div>
				<p class="text-white text-justify py-3">${game.gContent }</p>
				<div class="row">
					<div class="col-4">
						<h6>
						  평점
						</h6>
					</div>
					<div class="col-8">
						<small class="text-muted">${game.gAvg }/10 (${articlesCnt })</small>
					</div>
					<div class="col-4">
						<h6>
						  개발사
						</h6>
					</div>
					<div class="col-8">
						<small class="text-muted">${game.gDev }</small>
					</div>
					<div class="col-4">
						<h6>
						  출시일
						</h6>
					</div>
					<div class="col-8">
						<small class="text-muted">
						  <fmt:formatDate value="${game.gDate }" pattern="yyyy년 MM월 dd일"/><br />
						</small>
					</div>
					<div class="list_genre py-3" >
						<c:forEach var="genre" items="${genres }">
			      			<c:if test="${genre.gNum == game.gNum }">
					      		<span class="badge bg-primary">${genre.genName }</span>
			      			</c:if>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		
		<sec:authorize access="isAuthenticated()">
		<!-- 리뷰남기기 폼 -->
		<div class="row my-4">
			<div class="col-8 offset-2 py-4 mb-5" style="border:#3A3F44 1px solid;">
			<form:form commandName="artCommand" method="POST">
				<!-- CKEDITOR -->
				<h1>리뷰 남기기</h1>
				<textarea id="ck1" name="aContent">${artCommand.aContent }</textarea>
				<form:errors path="aContent"></form:errors>
				<!--  별  점 -->
				<div class="py-2">
					<span class="star"> ★★★★★ <span> ★★★★★ </span> <input
						type="range" name="aScore" oninput="drawStar(this)" value="1"
						step="1" min="0" max="10">
					</span>
				</div>
				<input type="submit" class="btn btn-success btn-sm" value="리뷰정보 등록" />
			</form:form>
			</div>
		</div>
		</sec:authorize>
		
		<div class="row my-4">
			<div class="col-8 offset-2">
				<c:forEach var="a" items="${articles }">
				<table class="table table-bordered border border-primary">
					<tr>
						<td class="fw-bold table-primary" rowspan="5" style="text-align: center; vertical-align: middle;">
							<c:if test="${a.itemFilename == 'default_icon.png' }">
								<img alt="아이콘이미지" src="<c:url value='/resources/images/user_icon/${a.itemFilename}'/>" width="15">
							</c:if>
							<c:if test="${a.itemFilename == 'default_admin_icon.png' }">
								<img alt="아이콘이미지" src="<c:url value='/resources/images/user_icon/${a.itemFilename}'/>" width="15">
							</c:if>
							<c:if test="${a.itemFilename != 'default_icon.png' && icon != 'default_admin_icon.png'}">
								<img alt="아이콘이미지" src="<c:url value='/images/icons/${a.itemFilename}'/>" width="15">
							</c:if>
							${a.mNickname}
						</td>
						<td width="560" class="fw-bold table-primary">
							<i>${a.aScore}/10</i>
						</td>
					</tr>
					<tr>
						<td width="560"><small class="text-muted"> <fmt:formatDate value="${a.aRegdate}" pattern="yyyy년 MM월 dd일"/></small></td>
					</tr>
					<tr>
						<td width="560" style="display: block; height:200px; overflow: auto;">${a.aContent}</td>
					</tr>
					<tr>
						<td style="margin-right: 1px;">
							<!-- ajax 사용 -->
							<sec:authorize access="isAuthenticated()">
							<sec:authentication property="principal.num" var="loginUserNum"/>
							<button id="bt" name="bt" type="button" class="btn btn-danger btn-sm"
								onclick="updateLike(${a.aNum}, ${a.gNum },${a.mNum }, ${loginUserNum })">공감
							</button>
							</sec:authorize>
							<small class="text-muted"><i>${a.aLike}명이 좋아합니다.</i></small>
						</td>
				</table>
				<div class="mb-5">
						<button type="button" class="btn btn-primary btn-sm" class="butt"
							onclick="location.href='/review/rep/${a.aNum}'">댓글달기(${replyCnt})</button>
				<sec:authorize access="isAuthenticated()">
					<sec:authentication property="principal.num" var="loginUserNum"/>
						<c:if test="${loginUserNum == a.mNum }">
						<button type="button" class="btn btn-info btn-sm"
							onclick="location.href='/review/artupdate/${a.gNum}/${a.aNum}'">리뷰
							수정하기</button>
						<button type="button" class="btn btn-warning btn-sm"
							onclick="location.href='${pageContext.request.contextPath }/game/artdelete/${a.aNum}'">리뷰
							삭제하기</button>
						</c:if>
				</sec:authorize>
				</div>
				</c:forEach>
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
<script	src="${pageContext.request.contextPath }/resources/ckeditor1/ckeditor.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script>
			CKEDITOR.replace('ck1',  {
					width:856,
					height:120,
					filebrowserUploadUrl : '${pageContext.request.contextPath}/imageUpload',
					skin: 'moono-dark'
					});
				
			 const drawStar = (target) => {
		    	 console.log(target.value);
		    	    document.querySelector(`.star span`).style.width = target.value * 10+"%";
		    }
		     		     
		     function updateLike(aNum1, gNum1, mNum1, loginUserNum){
		    	 var aNum = aNum1;
		    	 var gNum = gNum1;
			     var mNum = mNum1;
			     var loginUserNum = loginUserNum;
			    /*  alert(mNum); */
		    	$.ajax({
		    	  type : "POST",
		    	  url : "${pageContext.request.contextPath}/game/updateLike",
		    	  dataType : "json",
		    	  data : {'gNum' : gNum, 'mNum' : mNum, 'aNum' : aNum, 'loginUserNum' : loginUserNum},
		     	
		    	  success : function(likeCheck) {
		    		  console.log("likeCheck:" + likeCheck);
		    		  if(likeCheck == 0) {
		    			  alert("추천완료.");
		    			  location.reload();
		    		  }
		    		  else if(likeCheck == 1){
		    			  alert("추천취소");
		    			  location.reload();
		    		  }else  {
		    			  alert("통신에러");
		    		  }
		   
		    	  },
	    	  
 	  		    	}) ;
		       }	
</script>
</body>
</html>