package com.game.review.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.game.review.dao.MemberDAO;
import com.game.review.dto.MemberDTO;

@Controller
public class TestController {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String testSelect(Model model) {
		
		MemberDTO member = (MemberDTO) memberDAO.selectBySeq(1L);
		model.addAttribute("member", member);
		return "test";
	}
}
