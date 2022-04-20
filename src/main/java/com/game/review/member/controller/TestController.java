package com.game.review.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.game.review.HomeController;
import com.game.review.member.dao.MemberDAO;
import com.game.review.member.dto.MemberDTO;

@Controller
public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String testSelect(@RequestParam("pw") String pw, Model model) {
		if(pw != null) {
			String enpw = encoder.encode(pw);
			logger.debug("pw : " + enpw);
		}
		MemberDTO member = (MemberDTO) memberDAO.selectBySeq(1L);
		model.addAttribute("member", member);
		return "test";
	}
}
