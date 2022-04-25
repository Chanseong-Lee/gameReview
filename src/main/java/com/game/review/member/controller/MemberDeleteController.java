package com.game.review.member.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.review.member.command.LoginUserDetails;
import com.game.review.member.service.MemberDeleteService;

@Controller
public class MemberDeleteController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDeleteController.class);
	
	@Autowired
	private MemberDeleteService memberDeleteService;
	
	@RequestMapping(value="member/update/ajaxMemberDeleteForm", method=RequestMethod.GET )
	public String memberDeleteForm() {
		return "member/delete/deleteForm";
	}
	
	@RequestMapping(value="member/update/ajaxMemberDelete", method=RequestMethod.POST)
	@ResponseBody
	public String ajaxMemberDelete(
			@AuthenticationPrincipal 
			LoginUserDetails loginUserDetails) {
		
		int res = memberDeleteService.memberDelete(loginUserDetails.getNum());
		
		if(res == 1) {
			
			logger.info("탈퇴성공");
			return "1";
		}else {
			//탈퇴 실패
			logger.error("탈퇴 실패");
			return "2";
		}
	}
	
	@RequestMapping(value="member/delete/deleteSuccess", method=RequestMethod.GET)
	public String deleteSuccess() {
		SecurityContextHolder.clearContext();//세션 클리어 - 로그아웃
		return "member/delete/deleteSuccess";
	}
	
}
