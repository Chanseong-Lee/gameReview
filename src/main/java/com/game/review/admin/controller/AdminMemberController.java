package com.game.review.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.game.review.admin.command.AdminMemberUpdateCommand;
import com.game.review.admin.command.SearchCommand;
import com.game.review.admin.service.AdminMemberService;
import com.game.review.admin.validate.AdminMemberUpdateCommandValidator;
import com.game.review.admin.validate.SearchCommandValidator;
import com.game.review.member.dto.MemberDTO;
import com.game.review.member.dto.ProfileImgDTO;
import com.game.review.member.exception.AlreadyExistNicknameException;
import com.game.review.member.exception.NoExistMemberException;
import com.game.review.member.exception.NoImageException;
import com.game.review.member.exception.NoSessionDbMatchException;

@Controller
public class AdminMemberController {
	private static final Logger logger = LoggerFactory.getLogger(AdminMemberController.class);
	@Autowired
	private AdminMemberService adminMemberService;
	
	@RequestMapping(value="/admin/member/memberList", method = RequestMethod.GET)
	public String goMemberList(@ModelAttribute("search") SearchCommand searchCommand, Model model) {
		
		int count = adminMemberService.countAll();
		List<MemberDTO> members = adminMemberService.showMemberList();
		
		model.addAttribute("count", count);
		model.addAttribute("members", members);
		
		return "admin/member/memberList";
	}
	
	@RequestMapping(value="/admin/member/memberList/search", method=RequestMethod.GET)
	public String search(@ModelAttribute("search") SearchCommand searchCommand, BindingResult errors, Model model) {
		new SearchCommandValidator().validate(searchCommand, errors);
		List<MemberDTO> members = adminMemberService.search(searchCommand);
		model.addAttribute("members", members);
		return "admin/member/memberList";
	}
	
	@RequestMapping(value="/admin/member/detail/{num}")
	public String detail(@PathVariable Long num, Model model) {
		
		try {
			MemberDTO member = adminMemberService.detail(num);
			ProfileImgDTO img = adminMemberService.profileImg(num);
			model.addAttribute("member", member);
			model.addAttribute("profileImg", img.getProfileImgname());
		}catch(NoExistMemberException e) {
			logger.error("해당 멤버 존재 안함");
			return "exception/accesssError";
		}
		return "admin/member/memberDetail";
	}
	
	@RequestMapping(value="/admin/member/memberUpdate/{num}", method=RequestMethod.GET)
	public String goUpdateForm(
			@PathVariable 
			Long num, 
			@ModelAttribute("updateCommand") 
			AdminMemberUpdateCommand amuc,
			Model model) {
		
		try {
			MemberDTO member = adminMemberService.detail(num);
			ProfileImgDTO img = adminMemberService.profileImg(num);
			model.addAttribute("member", member);
			model.addAttribute("profileImg", img.getProfileImgname());
		}catch(NoExistMemberException e) {
			logger.error("해당 멤버 존재 안함");
			return "exception/accesssError";
		}
		return "admin/member/memberUpdateForm";
	}
	
	@RequestMapping(value="/admin/member/memberUpdate/{num}", method=RequestMethod.POST)
	public String memberUpdate(
			@PathVariable Long num,
			@ModelAttribute("updateCommand") AdminMemberUpdateCommand amuc, 
			BindingResult errors,
			Model model) {
		
		logger.info(""+amuc);
		try {
			MemberDTO member = adminMemberService.detail(num);
			ProfileImgDTO img = adminMemberService.profileImg(num);
			model.addAttribute("member", member);
			model.addAttribute("profileImg", img.getProfileImgname());
			new AdminMemberUpdateCommandValidator().validate(amuc, errors);
			if(errors.hasErrors()) {
				return "admin/member/memberUpdateForm";
			}
			adminMemberService.updateProfileByAdmin(amuc, num);
			
			return "redirect:/admin/member/detail/"+num;
		}catch (AlreadyExistNicknameException e) {
			logger.error("닉네임 중복!");
			errors.rejectValue("nickname", "dupNickname");
			return "admin/member/memberUpdateForm";
		}catch(NoImageException e) {
			logger.error("이미지파일이 아님!");
			errors.rejectValue("profileImg", "noImage");
			return "admin/member/memberUpdateForm";
		}catch(NoSessionDbMatchException e) {
			logger.error("세션이상!");
			return "exceptions/profileUpdateEx";
		}
	}
}
