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