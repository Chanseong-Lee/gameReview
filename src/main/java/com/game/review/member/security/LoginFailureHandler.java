package com.game.review.member.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

//will be invoked when login fails
@Service
public class LoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		System.out.println(exception.getMessage());
		
		request.setAttribute("email", request.getParameter("email"));
		
		if (exception instanceof AuthenticationServiceException) {
			request.setAttribute("loginFailMsg", "존재하지 않는 사용자 입니다.");
		} else if (exception instanceof BadCredentialsException) {
			request.setAttribute("loginFailMsg", "아이디 또는 비밀번호가 틀립니다.");
		} else if (exception instanceof LockedException) {
			request.setAttribute("loginFailMsg", "잠긴 계정입니다.");
		} else if (exception instanceof DisabledException) {
			request.setAttribute("loginFailMsg", "이메일인증을 하지않아 로그인이 불가능합니다.");
		} else if (exception instanceof AccountExpiredException) {
			request.setAttribute("loginFailMsg", "만료된 계정입니다..");
		} else if (exception instanceof CredentialsExpiredException) {
			request.setAttribute("loginFailMsg", "비밀번호가 만료되었습니다.");
		}
		
		//로그인 실패 종류를 리퀘스트에 담고 로그인뷰로 다시 포워딩
		RequestDispatcher dispatcher = request.getRequestDispatcher("/member/loginForm");
		dispatcher.forward(request, response);

	}

}
