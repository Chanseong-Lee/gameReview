package com.game.review.member.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import com.game.review.HomeController;
import com.game.review.member.command.LoginUserDetails;
@Service
public class LoginSuccessHandler implements AuthenticationSuccessHandler{
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		
		
		//WebAuthenticationDetails객체는 IP와 세션ID를 갖고있다.
		WebAuthenticationDetails web = (WebAuthenticationDetails) authentication.getDetails();
		logger.info("IP : " + web.getRemoteAddress());
		logger.info("세션 ID : " + web.getSessionId());
		
		//로그인 성공한 사용자의 정보 추출
		LoginUserDetails user = (LoginUserDetails) authentication.getPrincipal();
		logger.info("로그인 성공 후 userdetails : " + user);
		logger.info("로그인 성공 후 userdetails의 이름 : " + user.getUsername());
		
		//아이디 기억하기 체크되어있으면 쿠키 생성
		String rememberId = request.getParameter("rememberId");
		logger.info("아이디기억하기 : " + rememberId);
		Cookie cookie = new Cookie("rememberId", user.getUsername());
		cookie.setPath("/");
		if(rememberId != null && rememberId.equals("on")) {
			cookie.setMaxAge(60 * 60 * 24 * 30); //30days
		}else {
			cookie.setMaxAge(0); //체크안되어있으면 쿠키 즉시 소멸
		}
		response.addCookie(cookie);
		
		//권한리스트 추출
		Collection<GrantedAuthority> authList = (Collection<GrantedAuthority>) authentication.getAuthorities();
		Iterator<GrantedAuthority> authIter = authList.iterator();
		String url=request.getContextPath() + "/member/loginSuccess";//일반사용자
		while(authIter.hasNext()) {
			GrantedAuthority authority = authIter.next();
			if(authority.getAuthority().equals("ROLE_ADMIN")) {
				url=request.getContextPath() + "/admin/home";
			}
		}
		
		request.getSession().setAttribute("msg", "관리자 페이지");
		Enumeration<String> list = request.getSession().getAttributeNames();
		while (list.hasMoreElements()) {
			System.out.println("세션이름 : "+list.nextElement());
		}
		response.sendRedirect(url);
		
		/*
		//로그인 버튼 눌러서 접속했을 경우의 데이터를 얻는다(login controller에서 세팅해둔 uri주소)
		String blockLoginPage = (String) request.getSession().getAttribute("blockLoginPage");
		
		//로그인 실패하거나 했을때 이전페이지가 로그인창인 경우
		if(blockLoginPage != null) {
			request.getSession().removeAttribute("blockLoginPage");
		}
		
		
		//Spring Security가 요청을 가로챈 경우, 사용자가 원래 요청했던 URI 정보를 저장한 객체
		//예를 들어서 로그인 안한 사용자가 권한이 지정되어있는 곳으로 요청했을경우 그 uri
		// -> 권한없으므로 Spring Security가 요청을 가로채 로그인페이지로 요청을바꿔서 서블릿에게 전달
		//이때 기존 요청을 세션에 저장하게 되는데 이 정보를 가져와서 포워딩/리다이렉트시킬 uri로 사용, 만약 null일경우 지금의 케이스가 아님
		
		RequestCache requestCache = new HttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		//정상적으로 로그인되어 해당 권한을 정확히 요청해서 spring security가 요청을 가로채지 않았다면 null
		//권한 없는자가 요청했을경우 시큐리티가 요청을 가로채고 savedrequest객체가 존재함
		if(savedRequest != null) {
			String uri = savedRequest.getRedirectUrl();
			//uri를 얻었으므로 세션에 저장된 객체를 지워 메모리 누수 방지
			requestCache.removeRequest(request, response);
			logger.info("권한없는자가 요청했던 uri : " + uri);
			
		}else if(blockLoginPage != null && !blockLoginPage.equals("")) {// ""가 아니라면 직접 로그인 페이지로 접속한 경우임
			String uri = blockLoginPage;
		}
		
		*/
	}

}
