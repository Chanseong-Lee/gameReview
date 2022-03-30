package com.game.review.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.game.review.HomeController;
import com.game.review.command.MemberRegCommand;
import com.game.review.service.MemberRegService;
@Controller
public class MemberRegController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private MemberRegService memberRegService;
	
	@RequestMapping(value="/member/regist", method=RequestMethod.GET)
	public String showMemberRegForm(@ModelAttribute("mrc")MemberRegCommand memberRegCommand) {
		return "member/memberRegForm";
	}
	
	
	@RequestMapping(value="/member/regist", method=RequestMethod.POST)
	public String insertMember(@ModelAttribute("mrc")MemberRegCommand memberRegCommand, RedirectAttributes rttr) {
		
		try {
			memberRegService.insertMember(memberRegCommand);
			rttr.addAttribute("nickname", memberRegCommand.getNickname());
			rttr.addAttribute("name", memberRegCommand.getName());
			rttr.addAttribute("email", memberRegCommand.getEmail());
			return "redirect:/member/registSuccess";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "exceptions/encodingEx";
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "exceptions/messageEx";
		}
	}

	@RequestMapping(value="/member/registSuccess", method=RequestMethod.GET)
	public String regSuccess(
			HttpServletRequest request,
			Model model,
			@RequestParam("nickname") String nickname,
			@RequestParam("name") String name, 
			@RequestParam("email") String email) {
		logger.debug("rugSuccess : nickname" + nickname);
		model.addAttribute("nickname", nickname);
		model.addAttribute("name", name);
		model.addAttribute("email", email);
		return "member/registSuccess";
	}
}
