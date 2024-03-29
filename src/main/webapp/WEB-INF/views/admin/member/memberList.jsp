<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>LCK CRITIC ADMIN</title>

    <!-- Custom fonts for this template -->
    <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="${pageContext.request.contextPath}/resources/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gray-600 sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${pageContext.request.contextPath }/admin/home">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i style="width:30px;"  class="fas fa-gamepad "></i>
                </div>
                <div class="sidebar-brand-text mx-3">LCK CRITIC <sup>admin</sup></div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath }/admin/home">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>관리자 홈</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Interface
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>통합 관리</span>
                </a>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">데이터 관리:</h6>
                        <a class="collapse-item" href="${pageContext.request.contextPath}/admin/game/gameList">게임데이터 관리</a>
                        <a class="collapse-item" href="${pageContext.request.contextPath}/admin/member/memberList">회원데이터 관리</a>
                        <a class="collapse-item" href="${pageContext.request.contextPath}/admin/item/itemList">아이템데이터 관리</a>
                    </div>
                </div>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Links
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
                    aria-expanded="true" aria-controls="collapsePages">
                    <i class="fas fa-fw fa-folder"></i>
                    <span>Pages</span>
                </a>
                <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Pages:</h6>
                        <a class="collapse-item" href="${pageContext.request.contextPath}/game/main">사용자 페이지로</a>
                        
                    </div>
                </div>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

            <!-- Sidebar Message -->
            <div class="sidebar-card d-none d-lg-flex">
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
                <p class="text-center mb-2"><strong><sec:authentication property="principal.nickname"/> </strong>님 환영합니다</p>
            </div>

        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

						 <div class="topbar-divider d-none d-sm-block"></div>
                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small"><sec:authentication property="principal.nickname"/> </span>
                                <sec:authentication property='principal.profileImgname' var="profileImg" />
                                <c:if test="${profileImg == 'unknown_profile.jpg'}">
								<img class="img-profile rounded-circle" src="<c:url value='/resources/images/unknown_profile/${profileImg}' />" id="thumbnailImg" width="152">
								</c:if>
								<c:if test="${profileImg != 'unknown_profile.jpg'}">
								<img class="img-profile rounded-circle" src="<c:url value='/images/profile/${profileImg}' />" id="thumbnailImg" width="152">
								</c:if>
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/member/update/profile">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Profile
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">회원관리(지금까지 가입한 회원 총 ${count }명)</h1>
                    </div>
	
                    <!-- Content Row -->
					<div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">회원 목록</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
											<th>번호</th>
											<th>이메일</th>
											<th>이름</th>
											<th>닉네임</th>
											<th>가입일</th>
											<th>인증</th>
											<th>포인트</th>
											<th>권한</th>
										</tr>
                                    </thead>
                                    <tfoot>
                                       <tr>
											<th>번호</th>
											<th>이메일</th>
											<th>이름</th>
											<th>닉네임</th>
											<th>가입일</th>
											<th>인증</th>
											<th>포인트</th>
											<th>권한</th>
										</tr>
                                    </tfoot>
                                    <tbody>
									<c:if test="${not empty members }">
										<c:forEach var="member" items="${members }">
										<tr>
											<td>${member.mNum }</td>
											<td>
												<a href='<c:url value="/admin/member/detail/${member.mNum }"/>'>${member.mEmail }</a>
											</td>
											<td>${member.mName }</td>
											<td>${member.mNickname }</td>
											<td>${member.mRegdate }</td>
											<td>${member.mIsvalid }</td>
											<td>${member.mPoint }</td>
											<td>
												<c:if test="${member.authLevel == 'ROLE_USER'}"><c:out value="일반사용자" /></c:if>
												<c:if test="${member.authLevel == 'ROLE_GUEST'}"><c:out value="무인증사용자" /></c:if>
											</td>
										</tr>
										</c:forEach>
									</c:if>
									<c:if test="${empty members }">
										<tr>
											<td colspan="7">등록된 사용자가 없습니다.</td>
										</tr>
									</c:if>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>


                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; GAME REVIEW 2022</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">일은 잘 마무리 하셨나요?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">정말 로그아웃 하시겠습니까?</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/member/logout">Logout</a>
                </div>
            </div>
        </div>
    </div>

   <!-- Bootstrap core JavaScript-->
    <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="${pageContext.request.contextPath}/resources/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="${pageContext.request.contextPath}/resources/js/demo/datatables-demo.js"></script>
</body>

</html>
	<%--

	<form:form action="${pageContext.request.contextPath}/admin/member/memberList/search" commandName="search" method="get">
	<div id="searchForm">
		검색 키워드 입력 : 
		<form:select path="searchType">
			<form:option value="email">이메일</form:option>
			<form:option value="name">이름</form:option>
			<form:option value="nickname">닉네임</form:option>
		</form:select>
		<form:input path="searchValue" placeholder="이메일 or 이름 or 닉네임"/>
		<input type="submit" value="검색">
		<form:errors path="searchValue" cssClass="errors"/>
	</div>
	</form:form>
	<div id="memberListDiv">
		<table>
			<tr>
				<th>번호</th>
				<th>이메일</th>
				<th>이름</th>
				<th>닉네임</th>
				<th>가입일</th>
				<th>이메일인증여부</th>
				<th>보유포인트</th>
				<th>권한</th>
			</tr>
		<c:if test="${not empty members }">
			<c:forEach var="member" items="${members }">
			<tr>
				<td>${member.mNum }</td>
				<td>
					<a href='<c:url value="/admin/member/detail/${member.mNum }"/>'>${member.mEmail }</a>
				</td>
				<td>${member.mName }</td>
				<td>${member.mNickname }</td>
				<td>${member.mRegdate }</td>
				<td>${member.mIsvalid }</td>
				<td>${member.mPoint }</td>
				<td>
					<c:if test="${member.authLevel == 'ROLE_USER'}"><c:out value="일반사용자" /></c:if>
					<c:if test="${member.authLevel == 'ROLE_GUEST'}"><c:out value="무인증사용자" /></c:if>
				</td>
			</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty members }">
			<tr>
				<td colspan="7">등록된 사용자가 없습니다.</td>
			</tr>
		</c:if>
		</table>
	</div>
</article>

<br><a href="${pageContext.request.contextPath}/admin/home">관리자홈으로</a>
</body>
</html>

--%>