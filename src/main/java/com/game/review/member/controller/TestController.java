package com.game.review.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.game.review.member.dao.MemberDAO;
import com.game.review.member.dto.MemberDTO;

@Controller
public class TestController {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String testSelect(Model model) {
		
		MemberDTO member = (MemberDTO) memberDAO.selectBySeq(3L);
		model.addAttribute("member", member);
		return "test";
	}
}
