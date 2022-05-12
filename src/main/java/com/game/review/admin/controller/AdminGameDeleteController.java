package com.game.review.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.game.review.admin.service.AdminGameDeleteService;

@Controller
public class AdminGameDeleteController {

	@Autowired
	AdminGameDeleteService adminGameDeleteService;

	@RequestMapping(value = "/admin/game/deleteGame/{gNum}", method = RequestMethod.GET)
	public String deleteGame(@PathVariable("gNum") Long gNum) {

		adminGameDeleteService.deleteGame(gNum);
		return "admin/game/deleteSuccess";
	}

}
