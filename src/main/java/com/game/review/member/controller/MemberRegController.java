package com.game.review.member.controller;

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
import com.game.review.member.command.MemberRegCommand;
import com.game.review.member.exception.noExistValidKeyException;
import com.game.review.member.service.MemberRegService;
@Controller
public class MemberRegController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private MemberRegService memberRegService;
	
	//into the join form
	@RequestMapping(value="/member/regist", method=RequestMethod.GET)
	public String showMemberRegForm(@ModelAttribute("mrc")MemberRegCommand memberRegCommand) {
		return "member/memberRegForm";
	}
	
	//on submit, add a member to DB and send email to the member -> redirect to the success page
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
	
	// will be redirected after the submit and show phrases including simple status of the member as like the name, nickname and email
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
	
	
	
	// will be invoked when the member clicks a-tag in their email which sent by server after joining a member
	//param -> email, validKey
	@RequestMapping(value="/member/verifyEmail", method=RequestMethod.GET)
	public String emailAuth(String email, String validKey, Model model) {
		try {
		memberRegService.emailAuth(email, validKey);
		model.addAttribute("email", email);
		model.addAttribute("validKey", validKey);
		} catch(noExistValidKeyException e) {
			logger.error("없는 인증키");
		}
		return "member/mailAuthSuccess";//alert로 메일인증 완료 및 로그인 하라는 메시지 알림
	}
}