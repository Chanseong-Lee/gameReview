package com.game.review.member.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.game.review.HomeController;
import com.game.review.member.command.FindLoginDataCommand;
import com.game.review.member.exception.NoEmailExistException;
import com.game.review.member.service.FindLoginDataService;
import com.game.review.member.validate.FindLoginDataCommandValidator;

@Controller
public class FindLoginValueController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private FindLoginDataService findLoginDataService;
	
	@RequestMapping(value="/member/find/email", method=RequestMethod.GET)
	public String findEmailForm(@ModelAttribute("findEmail") FindLoginDataCommand findEmailCommand) {
		
		return "member/find/findEmailForm";
	}
	
	@RequestMapping(value="/member/find/findEmail", method=RequestMethod.POST)
	public String doFindEmail(@ModelAttribute("findEmail") FindLoginDataCommand findLoginDataCommand, BindingResult errors) {
		logger.debug("이메일찾기 커맨드 값 :" + findLoginDataCommand.getEmail() +", "+ findLoginDataCommand.getName());
		new FindLoginDataCommandValidator().validate(findLoginDataCommand, errors);
		try {
			if(errors.hasErrors()) {
				return "member/find/findEmail";
			}
			findLoginDataService.findEmail(findLoginDataCommand);
			
			return "member/find/findEmailSuccess";
		}catch(NoEmailExistException e) {
			return "member/find/findEmailFail";
		}
	}
	@RequestMapping(value="/member/find/password", method=RequestMethod.GET)
	public String findPasswordForm(@ModelAttribute("findEmail") FindLoginDataCommand findLoginDataCommand) {
		
		return "member/find/findPasswordForm";
	}
	
	@RequestMapping(value="/member/find/findPassword", method=RequestMethod.POST)
	public String doFindPassword(@ModelAttribute("findEmail") FindLoginDataCommand findLoginDataCommand, BindingResult errors, Model model) {
		logger.debug("비밀번호찾기 커맨드 값 :" + findLoginDataCommand.getEmail() +", "+ findLoginDataCommand.getName());
		new FindLoginDataCommandValidator().validate(findLoginDataCommand, errors);
		try {
			if(errors.hasErrors()) {
				return "member/find/findEmailForm";
			}
			findLoginDataService.findPassword(findLoginDataCommand);
			model.addAttribute("memberEmail", findLoginDataCommand.getEmail());
			
			return "member/find/findPasswordSuccess";
		} catch(NoEmailExistException e) {
			return "member/find/findEmailFail";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "exceptions/encodingEx";
		} catch (MessagingException e) {
			e.printStackTrace();
			return "exceptions/messageEx";
		}
		
	}
}
