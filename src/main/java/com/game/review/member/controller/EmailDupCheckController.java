package com.game.review.member.controller;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.review.HomeController;
import com.game.review.member.service.EmailDupCheckService;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Controller
public class EmailDupCheckController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	public EmailDupCheckService emailDubCehckService;
	
	@RequestMapping(value="/member/emailCheck")
	@ResponseBody
	public String emailCheck(@RequestBody String email) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(email);
		String emaildata = element.getAsJsonObject().get("email").getAsString();
		//빈값 0 / 형식에러 3 / 중복 2 / 정상 1
		if(emaildata == null || emaildata.trim().isEmpty()) {
			//빈값
			return "0";
		}else {
			String emailRegExp=
					"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
			Pattern pattern = Pattern.compile(emailRegExp);
			if(!pattern.matcher(emaildata).matches()) {
				return "3";
			}
		}
		
		logger.debug("email : " + emaildata);
		boolean isDup = emailDubCehckService.emailChecker(emaildata);
		//중복이면 true, 중복아니면 false
		logger.debug("isDup : " + isDup);
		if(isDup) {
			return "2";
		}
		
		return "1";
	}
	
}
