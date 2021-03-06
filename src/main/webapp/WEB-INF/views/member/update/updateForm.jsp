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
<title>LCK CRITIC</title>
<script src="https://kit.fontawesome.com/c965630904.js" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css2?family=Oswald:wght@300;400;500;600;700&amp;display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css">
<style>
table{
	border: 2px solid gray;
	border-collapse: collapse;
}

td{
	border: 1px solid gray;
	padding: 5px 5px 5px 5px;
	
}
#profileImgBox {
	border: 1px solid black;
    width: 150px;
    height: 150px; 
    border-radius: 70%;
    overflow: hidden;
    margin: auto;
}
#uploadBtn{
 	padding: 3px 20px;
  	background-color:#FF6600;
  	border-radius: 4px;
  	color: white;
  	cursor: pointer;
}

#backToBasicProfileImgBtn{
  	padding: 3px 10px;
  	background-color:#FF6600;
  	border-radius: 4px;
  	color: white;
  	cursor: pointer;
	
}

#deleteForm{
	text-align: center;
	border: solid 2px gray;
	padding : 20px;
	width: 280px;
}

</style>
</head>
<body>
<div class="container">
	<h1>회원정보 수정</h1>
	<ul class="nav nav-tabs">
	  <li class="nav-item">
	    <a class="nav-link active" data-bs-toggle="tab" onclick="fetchPage('ajaxMemberUpdateForm')">Home</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link" data-bs-toggle="tab" onclick="fetchPage('ajaxPwdUpdateForm')">Profile</a>
	  </li>
	</ul>
	
	<sec:authorize access="hasRole('ROLE_USER')">
	<button type="button" onclick="fetchPage('ajaxMemberDeleteForm')">탈퇴하기</button>
	</sec:authorize>
	<br><br>
	<article></article>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script type="text/javascript">
window.onload = function(){
	fetchPage('ajaxMemberUpdateForm');
}

//ajax로 페이지 로딩
function fetchPage(name) {
	fetch(name).then(function (response) {
		response.text().then(function (text) {
			document.querySelector('article').innerHTML = text;
		});
	});
}

let nicknameChecker = 0;
let backTobasicImg = false;
//회원정보 수정
let updateBtn = function () {
	let nicknameMsgSpan = document.querySelector("#nicknameError");
	let nicknameInput = document.querySelector("#nickname");
	let nicknameValue = nicknameInput.value;
	let profileImg = document.querySelector("#profileImg")
	console.log("닉네임 : " + nicknameValue);
	console.log("파일 : " + profileImg.files[0]);
	console.log("기본프로필설정? : " + backTobasicImg);
	let formData = new FormData();
	formData.append("nickname", nicknameValue);
	formData.append("backTobasicImg", backTobasicImg);
	if(profileImg.files[0]!=null){
		formData.append("profileImg", profileImg.files[0]);
	}
	if (!nicknameValue.trim()) {
		nicknameMsgSpan.innerHTML = "필수항목입니다.";
		nicknameMsgSpan.style.color = "red";
		nicknameInput.style.backgroundColor = "#FFCECE";
		document.querySelector("#success").innerHTML = "";
	} else if (nicknameValue.length < 2 || nicknameValue.length > 11) {
		nicknameMsgSpan.innerHTML = "닉네임은 한글, 영문 2~10자입니다.";
		nicknameMsgSpan.style.color = "red";
		nicknameInput.style.backgroundColor = "#FFCECE";
		document.querySelector("#success").innerHTML = "";
	} else if (nicknameValue.search(/\s/) != -1) {
		nicknameMsgSpan.innerHTML = "닉네임은 공백을 포함할 수 없습니다.";
		nicknameMsgSpan.style.color = "red";
		nicknameInput.style.backgroundColor = "#FFCECE";
		document.querySelector("#success").innerHTML = "";
	} else if (!isNickname(nicknameValue)) {
		nicknameMsgSpan.innerHTML = "닉네임은 한글, 영문, 숫자만 가능합니다.";
		nicknameMsgSpan.style.color = "red";
		nicknameInput.style.backgroundColor = "#FFCECE";
		document.querySelector("#success").innerHTML = "";
	} else {
		fetch('ajaxMemberUpdate', {
			//form-data일경우 헤더를 지워준다.
			method: "POST",
			body: formData,
		}).then(function (response) {
			return response.text();
		}).then(function (text) {
			console.log(text);
			// 0 = 정상, 1=중복, 2=빈값, 3=이메일형식에러

			if (text == '0') {
				//정상
				nicknameMsgSpan.innerHTML = "";
				//nicknameMsgSpan.style.color = "";
				nicknameInput.style.backgroundColor = "white";
				document.querySelector("#success").innerHTML = "변경사항이 저장되었습니다.";
				document.querySelector("#success").style.color = "green";
				opener.parent.location.reload();
			} else if (text == '1') {
				//중복
				nicknameMsgSpan.innerHTML = "이미 사용중인 닉네임입니다.";
				nicknameMsgSpan.style.color = "red";
				nicknameInput.style.backgroundColor = "#FFCECE";
				document.querySelector("#success").innerHTML = "";
			} else if (text == '2') {
				//빈값
				nicknameMsgSpan.innerHTML = "필수항목입니다.";
				nicknameMsgSpan.style.color = "red";
				nicknameInput.style.backgroundColor = "#FFCECE";
				document.querySelector("#success").innerHTML = "";
			} else if (text == '4'){
				nicknameMsgSpan.innerHTML = "";
				//nicknameMsgSpan.style.color = "";
				nicknameInput.style.backgroundColor = "white";
				document.querySelector("#success").innerHTML = "이미지 파일만 업로드 가능합니다.";
				document.querySelector("#success").style.color = "red";
			}
		})
	}
}

let updatePwdBtn = function () {
	let passwordInput = document.querySelector("#password");
	let passwordValue = passwordInput.value;
	let passwordErrorMsgSpan = document.querySelector("#passwordErrorMsg");
	let confirmPasswordInput = document.querySelector("#confirmPassword");
	let confirmPasswordValue = confirmPasswordInput.value;
	let confirmPasswordErrorMsgSpan = document.querySelector("#confirmPasswordErrorMsg");
	console.log("password : " + passwordValue);
	console.log("confirmPassword : " + confirmPasswordValue);
	const dataPwd = {
		password : passwordValue,
		confirmPassword : confirmPasswordValue,
	}
	if (!passwordValue.trim()) {
		passwordErrorMsgSpan.innerHTML = "필수항목입니다.";
		passwordErrorMsgSpan.style.color = "red";
		passwordInput.style.backgroundColor = "#FFCECE";
		document.querySelector("#success").innerHTML = "";
		confirmPasswordErrorMsgSpan.innerHTML = ""
		confirmPasswordInput.style.backgroundColor = "white";
	} else {
		if (passwordValue != confirmPasswordValue) {
			passwordErrorMsgSpan.innerHTML = "";
			passwordInput.style.backgroundColor = "white";
			confirmPasswordErrorMsgSpan.innerHTML = "비밀번호가 일치하지 않습니다."
			confirmPasswordErrorMsgSpan.style.color = "red";
			confirmPasswordInput.style.backgroundColor = "#FFCECE";
			document.querySelector("#success").innerHTML = "";
		} else {
			//fetch go
			fetch('ajaxPwdUpdate', {
				method: "POST",
				headers: {
					"Content-Type": "application/json; charset=utf-8"
				},
				body: JSON.stringify(dataPwd),
			}).then(function(response){
				return response.text()
			}).then(function(text){
				//성공 = '1', 실패 = '0', 불일치='2', 빈값 = '3', 기존과 동일='4'
				if(text == '1'){
					passwordErrorMsgSpan.innerHTML = "";
					passwordInput.style.backgroundColor = "white";
					confirmPasswordErrorMsgSpan.innerHTML = ""
					confirmPasswordInput.style.backgroundColor = "white";
					document.querySelector("#success").innerHTML = "변경사항이 저장되었습니다.";
					document.querySelector("#success").style.color = "green";
					opener.parent.location.reload();
				}else if(text == '2'){
					passwordErrorMsgSpan.innerHTML = "";
					passwordInput.style.backgroundColor = "white";
					confirmPasswordErrorMsgSpan.innerHTML = "비밀번호가 일치하지 않습니다."
					confirmPasswordErrorMsgSpan.style.color = "red";
					confirmPasswordInput.style.backgroundColor = "#FFCECE";
					document.querySelector("#success").innerHTML = "";
				}else if(text == '3'){
					passwordErrorMsgSpan.innerHTML = "필수항목입니다.";
					passwordErrorMsgSpan.style.color = "red";
					passwordInput.style.backgroundColor = "#FFCECE";
					document.querySelector("#success").innerHTML = "";
					confirmPasswordErrorMsgSpan.innerHTML = ""
					confirmPasswordInput.style.backgroundColor = "white";
				}else if(text == '4'){
					passwordErrorMsgSpan.innerHTML = "기존 비밀번호와 동일합니다.";
					passwordErrorMsgSpan.style.color = "red";
					passwordInput.style.backgroundColor = "#FFCECE";
					document.querySelector("#success").innerHTML = "";
					confirmPasswordErrorMsgSpan.innerHTML = ""
					confirmPasswordInput.style.backgroundColor = "white";
				}else{
					alert("server-side-error has been occured!");
					document.location.href="${pageContext.request.contextPath}/game/main";
				}
			});
		}
	}
}

function deleteBtn(){
	let isAgreed = confirm("정말로 탈퇴하시겠습니까?");
	if(isAgreed){
		fetch('ajaxMemberDelete', {
			method:'POST',
		}).then(function(response){
			return response.text();
		}).then(function(text){
			//탈퇴성공 1, 탈퇴 실패 2
			if(text=='1'){
				window.location.href='${pageContext.request.contextPath}/member/delete/deleteSuccess';
			}else{
				alert("서버문제 발생! 관리자에게 문의하십시오!\n프로필 화면으로 이동합니다.");
				window.location.href="${pageContext.request.contextPath}/member/update/profile";
			}
		})
		
	}
}


function setTumbnail(){
	let fileInfo = document.getElementById("profileImg").files[0];
	let reader = new FileReader();
		
	reader.onload = function(){
		document.getElementById("thumbnailImg").src = reader.result;
	}
	if(fileInfo){
		reader.readAsDataURL(fileInfo);
	}
}


function isNickname(asValue) {
	var regExp = /^[a-zA-Z0-9ㄱ-힣][a-zA-Z0-9ㄱ-힣]*$/;
	//한글 영어 숫자
	return regExp.test(asValue);
}

function basicImg(){
	backTobasicImg = confirm("기본 프로필 사진으로 돌아가시겠습니까?");
	if(backTobasicImg){
		document.getElementById("thumbnailImg").src = "${pageContext.request.contextPath}/resources/images/unknown_profile/unknown_profile.jpg";
		document.querySelector("#profileImg").value = null;
	}
}
</script>

</body>
</html>