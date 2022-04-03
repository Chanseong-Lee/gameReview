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
let pwdChecker = 0;

// idchecker와 pwdchecker가 둘다 1이면 실행하는 메서드 구현해야됨
function submitActivator(){
    if(idChecker == 1 && pwdChecker == 1){
        submitDisable(false);
    }
}

let checkEmail = function () {
    let emailValue = document.querySelector('#email').value;
    let nicknameValue = document.querySelector('#nickname').value;
    const dataEmail = { 
        email: emailValue,
    };
    fetch('emailCheck', {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        },
        body: JSON.stringify(data),
    }).then(function (response) {
        response.json();
        // response.text().then(function (text) {
        //     let emailMsgSpan = document.querySelector("#emailError");
        //     if (text == '1') {
        //         //정상
        //         emailMsgSpan.innerHTML = "<font color=\"green\">사용가능한 이메일입니다.</font>";
        //         submitDisable(false);//사용가능이면 submit 활성화
        //     } else if (text == '2') {
        //         emailMsgSpan.innerHTML = "<font color=\"red\">이미 사용중인 이메일입니다.</font>";
        //         submitDisable(true);
        //     } else if (text == '3') {
        //         emailMsgSpan.innerHTML = "<font color=\"red\">이메일 형식이 아닙니다.</font>";
        //         submitDisable(true);
        //     } else if (text == '0') {
        //         emailMsgSpan.innerHTML = "<font color=\"red\">필수 항목입니다..</font>";
        //         submitDisable(true);
        //     }
        // });
    }).then(function(data){
        console.log("jason : "+JSON.stringify(data));
        console.log(data.good);
        let emailMsgSpan = document.querySelector("#emailError");
        if(data.good=='0'){
            emailMsgSpan.innerHTML = "<font color=\"green\">사용가능한 이메일입니다.</font>";
        }
    }).catch(function(error){
        console.err("err!!");
    });
}

function isEmail(asValue) {
	var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
 
	return regExp.test(asValue);
}

function isPassword(asValue) {
	var regExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}$/;
    //특수문자없이
	return regExp.test(asValue);
}
function isId(asValue) {//6~20
	var regExp = /^[a-z]+[a-z0-9]{5,19}$/g;
 
	return regExp.test(asValue);
}
function isNickname(asValue) {
	var regExp = /^[a-zA-Z0-9ㄱ-힣][a-zA-Z0-9ㄱ-힣]*$/;
 
	return regExp.test(asValue);
}