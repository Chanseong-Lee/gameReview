package com.game.review.member.controller;

import java.security.Principal;

import javax.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.game.review.HomeController;

@Controller
public class LoginController {
	/*
	 * 스프링시큐리티로 로그인 구현.
	 * authentication(인증) : 본인이 맞는지? -인증성공 후-> authorization(인가) : 인증된사용자가 요청한 자원에 접근 가능한지 결정
	 * 인증과 인가를위해 principal을 아이디로, credential을 비밀번호로 사용하는 credential기반의 인증방식을 사용
	 * principal : 접근주체, 보호받는 리소스에 접근하는 대상
	 * credential : 리소스에 접근하는 대상의 비밀번호
	 * SecurityContextHoler 보안 주체의 세부정보를 포함하여 응용프로그램의 현재 보안 컨텍스트에 대한 세부정호가 저장됨
	 * SecurityContext : Authentication을 보관하는 역할을하며, SecurityContext를 통해 Authentication객체를 꺼내올 수 있다.
	 * Authentication : 현재 접근하는 주체의 정보와 권한을 담는 interface.
	 * -> Authentication객체는 SecurityContext에 저장되며, SecurityContextHolder를 통해 SecurityContext에 접근하고,
	 * 	 SecurityContext를 통해 Authentication객체에 접근한다.
	 * 
	 *  UsernamePasswordAuthenticationToken은 Authentication을 implements한 AbstractAuthenticationToken의 하위클래스
	 *  -> User의 ID가 principal역할, Password가 Credential역할을 함. UsernamePasswordAuthenticationTokendml의 첫번째 생성자는
	 *  	인증전의 객체를 생성하고, 두번째 생성자는 인증이 완료된 객체를 생성한다.
	 *  
	 *  AuthenticationProvider에서는 실제인증에 대한 부분을 처리. 인증전의 Authentication객체를 받아서 인증이 완료된 객체를 리턴.
	 *  -> 인터페이스를 구현해서 Custom한 AuthenticationProvider를 작성해서 AuthenticationManager에 등록한다.
	 *  
	 *  AuthenticationManager를 통하여 인증에 대한 부븐이 처리되는데, 실직적으로는 내부의 AuthenticationProvider에 의해 처리됨.
	 *  인증이 성공하면 2번째 생성자를 이용해 인증을 성공한(isAuthenticated=true)객체를 생성하여 SecurityContext에 저장한다.
	 *  그리고 인증상태를 유지하기 위해 세션에 보관하며, 인증이 실패한 경우에는 AuthenticationException을 발생시킨다.
	 *  
	 *  AuthenticationManager를 implements한 ProviderManager는 실제 인증과정에 대한 로직을 가지고있는 AuthenticationProvider를 List로 갖고있음
	 *  ProviderManager는 for문을 통해 모든 provider를 조회하면서 인증처리를 한다.
	 *  
	 *  혹시 직접구현한 CustomAuthenticationProvider를 등록할거라면 WebSecurityConfigurerAdapter를 상속받은 SecurityConfig에서 할수있음.
	 *  
	 *  인증에 성공하여 생성된 UserDetails 객체는 Authentication객체를 구현한 UsernamePasswordAuthenticationToken을 생성하기 위해 사용.
	 *  UserDetails인터페이스의 경우 직접 개발한 VO모델에 implements하여 처리하거나 UserDetailsVO에 implements하여 처리가능하다.
	 *  
	 *  UserDetailsService인터페이스는 UserDetails객체를 반환하는 단하나의 메소드를 갖고있음. 일반적으로 이를 구현한 클래스 내부에 
	 *  DAO를 주입받아 DB연결을 하여 처리한다.
	 *  
	 *  Password인코딩은 AuthenticationManagerBuilder.userDetailsSercvice().passwordEncoder()를 통해 패스워드 암호화에 사용될
	 *  password encoder 구현체를 지정할 수 있다.
	 *  
	 *  GrantedAuthority는 현재 사용자(principal)가 가지고있는 권한을 의미.
	 *  일반적으로 ROLE_ADMIN나 ROLE_USER와 같이 ROLE_*의 형태로 사용하며, 이들은 보통 roles라고 한다.
	 *  GrantedAuthority객체는 UserDetailsService에 의해 불러올 수 있고, 특정 자원에 대한 권한이 있는지를 검사하여 접근허용 여부를 결정한다.
	 *  
	 * 비로그인 사용자 /user/all
	 * 로그인한 사용자 /user/member
	 * 관리자 /user/admin
	 * 
	 * */
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@RequestMapping(value = "member/loginForm")//로그인 실패 로직이 리다이렉트가 아닌 포워딩을 하므로 로그인 요청의 post도 받음. 그래서 메소드방법 둘다 가능하게설정
	public String showLoginForm(Model model , @CookieValue(value="rememberId", required = false) Cookie cookie) {// 쿠키추가설정해줘야됨
		
		if(cookie != null) {
			model.addAttribute("email", cookie.getValue());
			model.addAttribute("checked", "checked");
		}
		
		return "member/login/loginForm";
	}
	
	@RequestMapping(value="member/loginSuccess", method=RequestMethod.GET)
	public String loginSuccess() {
		return "member/login/loginSuccess";
	}
	
	@RequestMapping(value="member/loginFail", method=RequestMethod.GET)
	public String loginFail() {
		return "member/login/loginFail";
	}
	
	@RequestMapping(value="member/logoutSuccess", method=RequestMethod.GET)
	public String logoutSuccess() {
		logger.info("로그아웃 석세스 요청됨?");
		return "member/login/logoutSuccess";
	}
	
	@RequestMapping(value="/exceptions/accessError")
	public String accessDenied(Authentication auth, Model model) {
		
		logger.info("access denied : " + auth);
		return "exceptions/accessError";
	}
	
	/*
	//spring security는 post요청을 자동으로 만들어주기 때문에 여기서 구현 안함
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
	 */

//	나중에 기존요청 로그인후 진행하도록 할거면 이곳 활용	
//	/* 사용자가 서버에 무언가를 요청할때 헤더의 Referer에 URI가 세팅됨.
//	 * 요청 시점의 사용자 URL정보를 세션에 담아서 전달(잘 지워줘야함)
//	 * 로그인이 틀려서 다시 하면 오청 시점의 URI가 로그인페이지가 되므로 조건문으로 막아줘야한다.
//	 * */
//	String url = request.getHeader("Referer");
//	if(url.contains("/member/loginForm")) {
//		request.getSession().setAttribute("blockLoginPage", url); //이 세션의 속성값은 로그인 핸들러에서 처리
//	}
}
