package com.game.review.game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	
	@RequestMapping(value="/game/main", method=RequestMethod.GET)
	public String goMain() {
		
		return "game/main";
	}
	
	@RequestMapping(value="/game/review/test")
	public String test() {
		return "game/review/test";
	}
}
