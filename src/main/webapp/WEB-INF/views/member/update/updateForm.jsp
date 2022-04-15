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
</style>
</head>
<body>
<h1>회원정보 수정</h1>
<button type="button" onclick="fetchPage('ajaxMemberUpdateForm')">회원정보 수정하기</button>
<button type="button" onclick="fetchPage('ajaxPwdUpdateForm')">비밀번호 수정하기</button>
<br><br>
<article></article>

<%--
<form:form action="${pageContext.request.contextPath}/member/update/update" commandName="updateCommand" method="post">
<table>
	<tr>
		<td colspan="2">
			<div id="prifileImgBox">
				이미지 추가할 예정
			</div>
		</td>
	</tr>
	<tr>
		<td>이메일 계정</td>
		<td>
			<sec:authentication property="principal.username"/>
		</td>
	</tr>
	<tr>
		<td>이름</td>
		<td>
			<sec:authentication property="principal.name"/>
		</td>
	</tr>
	<tr>
		<td>닉네임</td>
		<td>
			<input type="text" name="nickname" value="<sec:authentication property="principal.nickname"/>"><br>
			<form:errors path="nickname"></form:errors>
		</td>
	</tr>
</table>
<input type="submit" id="submit" value="변경사항 저장">
<button type="button" onclick="location.href='${pageContext.request.contextPath}/member/update/profile'">프로필 화면으로</button>
</form:form>
 --%>
 
<%--
<form:form action="${pageContext.request.contextPath}/member/update/updatePwd" commandName="updateCommand" method="post">
<table>
	<tr>
		<th colspan="2">
			비밀번호 수정
		</th>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td>	
			<input type="password" name="password" placeholder="새 비밀번호 입력"><br>
		</td>
	</tr>
	<tr>
		<td>비밀번호 확인</td>
		<td>
			<input type="password" name="passwordConfirm" placeholder="비밀번호 확인"><br>
			<span id="errorMsg"><form:errors path="password"/></span>
		</td>
	</tr>
</table><br>
<div id="success"></div><br>
<input type="submit" id="submit" value="변경사항 저장">
<button type="button" onclick="location.href='${pageContext.request.contextPath}/member/update/profile'">프로필 화면으로</button>
 --%>
 
<script type="text/javascript">
window.onload = fetchPage('ajaxMemberUpdateForm');

function fetchPage(name) {
    fetch(name).then(function (response) {
      response.text().then(function (text) {
        document.querySelector('article').innerHTML = text;
      });
    });
 }

let nicknameChecker = 0;


let updateBtn = function(){
	let nicknameValue = document.querySelector("#nickname").value;
	console.log("닉네임 : " + nicknameValue);
	const dataMember = {
		nickname : nicknameValue,	
	};
	
	fetch('ajaxMemberUpdate', {
		method:"POST",
		headers:{
			"Content-Type": "application/json; charset=utf-8"
		},
		body: JSON.stringify(dataMember),
	}).then(function(response){
		return response.text();
	}).then(function(text){
		console.log(text);
		// 0 = 정상, 1=중복, 2=빈값, 3=이메일형식에러
		let nicknameMsgSpan = document.querySelector("#nicknameError");
        let nicknameInput = document.querySelector("#nickname");
        if(!nicknameValue.trim()){
        	nicknameMsgSpan.innerHTML = "필수항목입니다.";
        	nicknameMsgSpan.style.color = "red";
        	nicknameInput.style.backgroundColor="#FFCECE";
        	document.querySelector("#success").innerHTML = "";
        }else if(nicknameValue.length < 2 || nicknameValue.length > 11){
       		nicknameMsgSpan.innerHTML = "닉네임은 한글, 영문 2~10자입니다.";
        	nicknameMsgSpan.style.color = "red";
        	nicknameInput.style.backgroundColor="#FFCECE";
        	document.querySelector("#success").innerHTML = "";
       	}else if(nicknameValue.search(/\s/) != -1){
       		nicknameMsgSpan.innerHTML = "닉네임은 공백을 포함할 수 없습니다.";
        	nicknameMsgSpan.style.color = "red";
        	nicknameInput.style.backgroundColor="#FFCECE";
        	document.querySelector("#success").innerHTML = "";
       	}else if(!isNickname(nicknameValue)){
       		nicknameMsgSpan.innerHTML = "닉네임은 한글, 영문, 숫자만 가능합니다.";
        	nicknameMsgSpan.style.color = "red";
        	nicknameInput.style.backgroundColor="#FFCECE";
        	document.querySelector("#success").innerHTML = "";
       	}else{
       		if(text=='0'){
    			//정상
       			nicknameMsgSpan.innerHTML = "";
        		//nicknameMsgSpan.style.color = "";
	          	nicknameInput.style.backgroundColor="white";
	            document.querySelector("#success").innerHTML = "변경사항이 저장되었습니다.";
	            document.querySelector("#success").style.color="green";
    		}else if(text=='1'){
    			//중복
    			nicknameMsgSpan.innerHTML = "이미 사용중인 닉네임입니다.";
    	    	nicknameMsgSpan.style.color = "red";
        		nicknameInput.style.backgroundColor="#FFCECE";
        		document.querySelector("#success").innerHTML = "";
    		}else if(text=='2'){
    			//빈값
    			nicknameMsgSpan.innerHTML = "필수항목입니다.";
            	nicknameMsgSpan.style.color = "red";
            	nicknameInput.style.backgroundColor="#FFCECE";
            	document.querySelector("#success").innerHTML = "";
    		}
       	}
	
	})
}

function isNickname(asValue) {
	var regExp = /^[a-zA-Z0-9ㄱ-힣][a-zA-Z0-9ㄱ-힣]*$/;
	//한글 영어 숫자
	return regExp.test(asValue);
}

</script>

</body>
</html>