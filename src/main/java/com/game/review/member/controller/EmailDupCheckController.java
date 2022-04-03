package com.game.review.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.review.HomeController;
import com.game.review.member.command.MemberRegCommand;
import com.game.review.member.service.EmailDupCheckService;
import com.game.review.member.validate.EmailDupValidator;

@Controller
public class EmailDupCheckController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	public EmailDupCheckService emailDupCheckService;
	
	@RequestMapping(value="/member/emailCheck")
	@ResponseBody
	public String emailCheck(@RequestBody MemberRegCommand mrc, Errors errors) throws Exception {
		String email = mrc.getEmail();
		logger.debug("data success email : " + email);
		new EmailDupValidator().validate(mrc, errors);
		
		if(email == null || email.trim().isEmpty()) {
			logger.debug("이메일 빈값=2");
			return "2";
		}else if(errors.hasErrors()) {
			logger.debug("이메일 정규식에러=3");
			return "3";
		}
		//0정상, 1중복
		int checkerForEmail = emailDupCheckService.emailChecker(email);
		if(checkerForEmail == 1) {
			logger.debug("이메일중복=1");
			return "1";
		}else {
			logger.debug("이메일정상!=0");
			return "0";
		}
	}
}
