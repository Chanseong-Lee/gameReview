package com.game.review.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.game.review.admin.service.AdminHomeService;

@Controller
public class AdminController {
	
	@Autowired
	private AdminHomeService adminHomeService;
	
	@RequestMapping(value="/admin/home")
	public String goHomeForAdmin(Model model) {
		
		int memberCnt = adminHomeService.countAllMember();
		int itemCnt = adminHomeService.countAllItem();
		int gameCnt = adminHomeService.countAllGame();
		int reviewCnt = adminHomeService.countAllReview();
		model.addAttribute("memberCnt", memberCnt);
		model.addAttribute("itemCnt", itemCnt);
		model.addAttribute("gameCnt", gameCnt);
		model.addAttribute("reviewCnt", reviewCnt);
		return "admin/adminHome";
	}

}
