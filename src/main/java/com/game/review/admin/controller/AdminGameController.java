package com.game.review.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.game.review.admin.service.AdminMemberService;

@Controller
public class AdminGameController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminGameController.class);

	@Autowired
	private AdminMemberService adminMemberController;
	
	@RequestMapping(value="/admin/game/gameList", method = RequestMethod.GET)
	public String goGameList(Model model) {
		return "admin/game/gameList";
	}
}
