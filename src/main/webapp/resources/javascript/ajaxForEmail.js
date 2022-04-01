function submitDisable(bool) {
    document.querySelector('#submit').disabled = bool;
}

window.addEventListener('load', function () {
    submitDisable(true);
    if(document.querySelector("#email").value){
    	this.document.querySelector("#emailError").innerHTML = "<font color=\"green\">사용가능한 이메일입니다.</font>";
    	submitDisable(false);
    }
})

let checkEmail = function () {
    let emailValue = document.querySelector('#email').value;
    let data = { email: emailValue };
    fetch('emailCheck', {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data),
    }).then(function (response) {
        response.text().then(function (text) {
            let emailMsgSpan = document.querySelector("#emailError");
            if (text == '1') {
                //정상
                emailMsgSpan.innerHTML = "<font color=\"green\">사용가능한 이메일입니다.</font>";
                submitDisable(false);//사용가능이면 submit 활성화
            } else if (text == '2') {
                emailMsgSpan.innerHTML = "<font color=\"red\">이미 사용중인 이메일입니다.</font>";
                submitDisable(true);
            } else if (text == '3') {
                emailMsgSpan.innerHTML = "<font color=\"red\">이메일 형식이 아닙니다.</font>";
                submitDisable(true);
            } else if (text == '0') {
                emailMsgSpan.innerHTML = "<font color=\"red\">필수 항목입니다..</font>";
                submitDisable(true);
            }
        });
    });
}
