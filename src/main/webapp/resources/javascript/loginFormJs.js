	let errorMsgSpan = document.querySelector("#errorMsg");
	function loginSubmit(){
		let emailVal = document.querySelector("#email").value;
		let passwordVal = document.querySelector("#password").value;
		if(!emailVal){
			errorMsgSpan.innerHTML = "아이디를 입력하세요";
			return false;
		}
		if(!passwordVal){
			errorMsgSpan.innerHTML = "비밀번호를 입력하세요";
			return false;
		}
		return true;
	}
	
	function submitDisable(bool) {
	    document.querySelector('#submit').disabled = bool;
	}
	
	window.addEventListener('load', function () {
	    submitDisable(true);
	})
	
	let emailChecker = 0;
	let pwdChecker = 0;
	
	function submitActivator(){
	    if(emailChecker == 1 && pwdChecker == 1){
	        submitDisable(false);
	    }else{
	    	submitDisable(true);
	    }
	}
	
	let onLoadEmailValue = document.querySelector("#email").value;
	if(onLoadEmailValue != 0){
		emailChecker = 1;
		submitActivator();
	}
	
	function notEmptyEmail(){
		let emailVal = document.querySelector("#email").value;
		if(emailVal.trim().length != 0){
			emailChecker = 1;
			submitActivator();
		}else{
			emailChecker = 0;
			submitActivator();
		}
	}
	
	function notEmptyPwd(){
		let pwdVal = document.querySelector("#password").value;
		if(pwdVal.trim().length != 0){
			pwdChecker = 1;
			submitActivator();
		}else{
			pwdChecker = 0;
			submitActivator();
		}
	}
	