<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
td {
	height: 50px;
}
.errorAfterSubmit{
	color:red;
}
</style>

</head>
<body>
	<h1>회원가입</h1>
	<%-- 
	 이메일 및 패스워드에 이상이 없고, 모든 내용이 입력되었을 때만 submit을 활성화 하도록 구현 
	 전부 이상없이 완료해야 submit이 활성화 되므로 데이터 유효성 검증은 js에서 진행
	 이메일 중복체크는 ajax를 이용하여 비동기로 처리
	 혹시 모를 우회기법 사용시 방어할 수 있도록 유효성 검증 로직을 서버에서도 처리.
	 --%>
	 <%--아이디는 영문자만 가능. 4자이상 20자 미만 --%>
	<form:form action="regist" commandName="mrc" method="post">
		<table>
			<tr>
				<td>이메일</td>
				<td><form:input id="email" path="email" oninput="checkEmail()" />&nbsp;<input
					type="button" value="중복확인" onclick="checkEmail()"><br>
					<span class="errorAfterSubmit"><form:errors path="email" /></span>
					<span id="emailError"></span>
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td><form:input id="name" path="name" oninput="checkName()"/><br>
				 	<span class="errorAfterSubmit"><form:errors path="name" /></span>
				 	<span id="nameError"></span>
				 </td>
			</tr>
			<tr>
				<td>닉네임</td>
				<td>
					<form:input id="nickname" path="nickname" oninput="checkNickname()"/><br> 
					<span class="errorAfterSubmit"><form:errors path="nickname" /></span>
					<span id="nicknameError"></span>
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><form:password id="password" path="password" oninput="checkPw()"/><br> 
				<span class="errorAfterSubmit"><form:errors path="password" /></span></td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td><form:password id="confirmPassword" path="confirmPassword" oninput="checkPw()"/><br> 
					<span class="errorAfterSubmit"><form:errors path="confirmPassword" /></span>
					<span id="noMatchErr"></span>
				</td>
			</tr>
		</table>
		<input type="submit" id="submit" value="회원가입">
	</form:form>
<script type="text/javascript">

function submitDisable(bool) {
    document.querySelector('#submit').disabled = bool;
}

window.addEventListener('load', function () {
    submitDisable(true);
    // if(document.querySelector("#email").value){
    // 	this.document.querySelector("#emailError").innerHTML = "<font color=\"green\">사용가능한 이메일입니다.</font>";
    // 	submitDisable(false);
    // }
})

let idChecker = 0;
let nicknameChecker = 0;
let pwdChecker = 0;
let nameChecker = 0;
// idchecker와 pwdchecker가 둘다 1이면 실행하는 메서드 구현해야됨
function submitActivator(){
	let nameInputValue = document.querySelector("#name").value;
	let passwordInputValue = document.querySelector("#password").value;
    if(idChecker == 1 && pwdChecker == 1 
    		&&	nicknameChecker == 1 && nameInputValue.trim().length){
        submitDisable(false);
    }else{
    	submitDisable(true);
    }
}

let checkEmail = function () {
    let emailValue = document.querySelector('#email').value;
    const dataEmail = { 
        email: emailValue,
    };
    fetch('emailCheck', {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        },
        body: JSON.stringify(dataEmail),
    }).then(function(response){
    	return response.text()
    	}).then(function(text){
        console.log(text);
        // 0 = 정상, 1=중복, 2=빈값, 3=이메일형식에러
        let emailMsgSpan = document.querySelector("#emailError");
        let emailInput = document.querySelector("#email");
        if(text=='0'){
        	//정상
            emailMsgSpan.innerHTML = "사용가능한 이메일입니다.";
        	emailMsgSpan.style.color = "green";
            emailInput.style.backgroundColor="#B0F6AC";
            idChecker = 1;
            submitActivator();
        }else if(text == '1'){
        	//중복
        	emailMsgSpan.innerHTML = "이미 사용중인 이메일입니다.";
        	emailMsgSpan.style.color = "red";
        	emailInput.style.backgroundColor="#FFCECE";
        	idChecker = 0;
        	submitActivator();
        }else if(text == '2'){
        	//빈값
        	emailMsgSpan.innerHTML = "필수 항목입니다.";
        	emailMsgSpan.style.color = "red";
        	emailInput.style.backgroundColor="#FFCECE";
        	idChecker = 0;
        	submitActivator();
        }else if(text == '3'){
        	//이메일형식에러
        	emailMsgSpan.innerHTML = "이메일 형식이 아닙니다.";
        	emailMsgSpan.style.color = "red";
        	emailInput.style.backgroundColor="#FFCECE";
        	idChecker = 0;
        	submitActivator();
        }
    });
}

let checkNickname = function(){
	let nicknameValue = document.querySelector('#nickname').value;
	const dataNickname = { 
	        nickname: nicknameValue,
	    };
	fetch('nicknameCheck', {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        },
        body: JSON.stringify(dataNickname),
    }).then(function(response){
    	return response.text()
    	}).then(function(text){
        console.log(text);
        // 0 = 정상, 1=중복, 2=빈값, 3=이메일형식에러
        let nicknameMsgSpan = document.querySelector("#nicknameError");
        let nicknameInput = document.querySelector("#nickname");
        if(!nicknameValue.trim()){
        	nicknameMsgSpan.innerHTML = "필수항목입니다.";
        	nicknameMsgSpan.style.color = "red";
        	nicknameInput.style.backgroundColor="#FFCECE";
        	nicknameChecker = 0;
        	submitActivator();
        }else if(nicknameValue.length < 2 || nicknameValue.length > 11){
       		nicknameMsgSpan.innerHTML = "닉네임은 한글, 영문 2~10자입니다.";
        	nicknameMsgSpan.style.color = "red";
        	nicknameInput.style.backgroundColor="#FFCECE";
        	nicknameChecker = 0;
        	submitActivator();
       	}else if(nicknameValue.search(/\s/) != -1){
       		nicknameMsgSpan.innerHTML = "닉네임은 공백을 포함할 수 없습니다.";
        	nicknameMsgSpan.style.color = "red";
        	nicknameInput.style.backgroundColor="#FFCECE";
        	nicknameChecker = 0;
        	submitActivator();
       	}else if(!isNickname(nicknameValue)){
       		nicknameMsgSpan.innerHTML = "닉네임은 한글, 영문, 숫자만 가능합니다.";
        	nicknameMsgSpan.style.color = "red";
        	nicknameInput.style.backgroundColor="#FFCECE";
        	nicknameChecker = 0;
        	submitActivator();
       	}else{
	        if(text=='0'){
    	    	//정상
        	    nicknameMsgSpan.innerHTML = "사용가능한 닉네임입니다.";
        		nicknameMsgSpan.style.color = "green";
	            nicknameInput.style.backgroundColor="#B0F6AC";
	            nicknameChecker = 1;
        	    submitActivator();
        	}else if(text == '1'){
        		//중복
	        	nicknameMsgSpan.innerHTML = "이미 사용중인 닉네임입니다.";
    	    	nicknameMsgSpan.style.color = "red";
        		nicknameInput.style.backgroundColor="#FFCECE";
        		nicknameChecker = 0;
        		submitActivator();
        	}else if(text == '2'){
        		//빈값
        		nicknameMsgSpan.innerHTML = "필수 항목입니다.";
        		nicknameMsgSpan.style.color = "red";
        		nicknameInput.style.backgroundColor="#FFCECE";
        		nicknameChecker = 0;
        		submitActivator();
        	}
       	}
	    });
}

let checkPw = function(){
	let pwInput = document.querySelector("#password");
	let pwConfirmInput = document.querySelector("#confirmPassword");
	let pwValue = pwInput.value;
	let pwConfirmValue = pwConfirmInput.value;
	let errSpan = document.querySelector("#noMatchErr");
	if(pwConfirmValue == "" && (pwConfirmValue == pwValue || pwConfirmValue != pwValue)){
		//아직 비밀번호 재확인에 입력 안했을때
		pwdChecker = 0;
		submitActivator();
	}else if(pwValue == pwConfirmValue){
		//일치
		errSpan.innerHTML = "비밀번호가 일치합니다.";
		errSpan.style.color = "green";
		pwConfirmInput.style.backgroundColor = "#B0F6AC";
		pwdChecker = 1;
		submitActivator();
	}else if(pwValue != pwConfirmValue){
		//일치하지 않을때
		errSpan.innerHTML = "비밀번호가 일치하지 않습니다.";
		errSpan.style.color = "red";
		pwConfirmInput.style.backgroundColor = "#FFCECE";
		pwdChecker = 0;
		submitActivator();
	}
}

let checkName = function(){
	let nameInput = document.querySelector("#name");
	let nameValue = nameInput.value;
	let errSpan = document.querySelector("#nameError");
	if(!isName(nameValue)){
		errSpan.innerHTML = "이름은 한글 또는 영어만 가능합니다.";
		errSpan.style.color = "red";
		nameInput.style.backgroundColor = "#FFCECE";
		submitActivator();
	}else{
		errSpan.innerHTML = "사용가능한 이름입니다.";
		errSpan.style.color = "green";
		nameInput.style.backgroundColor = "#B0F6AC";
		submitActivator();
	}
}
//아래부터는 정규식관련

//사용안함
function isEmail(asValue) {
	var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	return regExp.test(asValue);
}
//사용안함
function isPassword(asValue) {
	var regExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}$/;
    //특수문자없이
	return regExp.test(asValue);
}

function isName(asValue) {//6~20
	var regExp = /^[가-힣]{2,10}|[a-zA-Z]{2,10}\s[a-zA-Z]{2,10}|[a-zA-Z]{2,20}$/;
	//한글이름 및 영어이름
	return regExp.test(asValue);
}
function isNickname(asValue) {
	var regExp = /^[a-zA-Z0-9ㄱ-힣][a-zA-Z0-9ㄱ-힣]*$/;
	//한글 영어 숫자
	return regExp.test(asValue);
}
</script>

<!--<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/ajaxForEmail.js"></script>-->
</body>
</html>