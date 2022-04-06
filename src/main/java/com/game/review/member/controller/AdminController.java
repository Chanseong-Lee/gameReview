package com.game.review.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	
	@RequestMapping(value="/admin/home")
	public String goHomeForAdmin() {
		return "admin/adminHome";
	}
}
