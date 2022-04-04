package com.game.review.member.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.game.review.HomeController;
import com.game.review.member.command.AuthInfo;
import com.game.review.member.command.LoginCommand;
import com.game.review.member.exception.NoEmailExistException;
import com.game.review.member.exception.PasswordNotMatchingException;
import com.game.review.member.service.LoginService;
import com.game.review.member.validate.LoginCommandValidator;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "member/login", method = RequestMethod.GET)
	public String showLoginForm(@ModelAttribute("LoginCommand") LoginCommand loginCommand) {// 쿠키추가설정해줘야됨
		// 내일은 여기 쿠키설정!
		return "member/loginForm";
	}

	@RequestMapping(value = "member/login", method = RequestMethod.POST)
	public String submitLogin(
			@ModelAttribute("LoginCommand") LoginCommand loginCommand, 
			BindingResult bindingResult,
			HttpSession session,
			HttpServletResponse response) {

		//커맨드객체 검증 구현
		new LoginCommandValidator().validate(loginCommand, bindingResult);
		if(bindingResult.hasErrors()) {
			return "member/loginForm";
		}
		try {
			AuthInfo authInfo = loginService.authenticate(loginCommand.getEmail(), loginCommand.getPassword());
			//예외없이 여기까지오면 로그인 성공한것으로 간주
			//세션에 저장
			session.setAttribute("authInfo", authInfo);
			
			//쿠키생성
			Cookie rememberCookie = new Cookie("remember", loginCommand.getEmail());
			rememberCookie.setPath("/");
			if(loginCommand.isRememberEmail()) {//checked
				rememberCookie.setMaxAge(60*60*24*10); //for 10 days
			}else {//not checked
				rememberCookie.setMaxAge(0);
			}
			response.addCookie(rememberCookie);
			return "member/loginSuccess";
		}catch (NoEmailExistException e) {
			logger.debug("이메일 존재하지 않음 in LoginController");
			bindingResult.reject("noEmail");
			return "member/loginForm";
		}catch(PasswordNotMatchingException e) {
			logger.debug("비밀번호 안맞음 in LoginController");
			bindingResult.reject("passwordNotMatch");
			return "member/loginForm";
		}
	}
}
