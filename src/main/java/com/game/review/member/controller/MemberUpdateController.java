package com.game.review.member.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.game.review.member.command.LoginUserDetails;

@Controller
public class MemberUpdateController {
	@RequestMapping(value="member/update")
	public String memberUpdate(@AuthenticationPrincipal LoginUserDetails lud, Model model) {
		//업데이트 구현안함 테스트만 성공
		
		//spring security session수정도 같이 해줘야함 구글링할것
		
		
		model.addAttribute("lud", lud);
		return "member/update/update";
	}
}
