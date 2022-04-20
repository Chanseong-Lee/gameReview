package com.game.review.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.review.HomeController;
import com.game.review.member.command.MemberRegCommand;
import com.game.review.member.service.NicknameDupCheckService;

@Controller
public class NicknameDupCheckController {
	
	private static final Logger logger = LoggerFactory.getLogger(NicknameDupCheckController.class);
	
	@Autowired
	private NicknameDupCheckService nicknameDupCheckService;
	
	@RequestMapping(value="/member/nicknameCheck", method=RequestMethod.POST)
	@ResponseBody
	public String nicknameCheck(@RequestBody MemberRegCommand mrc, Errors errors) {
		String nickname = mrc.getNickname();
		logger.debug("data success nickname : " + nickname);
		
		if(nickname == null || nickname.trim().isEmpty()) {
			//빈값
			logger.debug("닉네임 빈값=2");
			return "2";
		}
		int checkerForNickname = nicknameDupCheckService.nicknameChecker(nickname);
		if(checkerForNickname == 1) {
			//중복
			return "1";
		}else {
			//정상
			return "0";
		}
	}
}
