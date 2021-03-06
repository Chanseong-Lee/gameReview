package com.game.review.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.review.HomeController;
import com.game.review.member.command.ChangePasswordCommand;
import com.game.review.member.command.LoginUserDetails;
import com.game.review.member.command.MemberUpdateCommand;
import com.game.review.member.command.PasswordCommand;
import com.game.review.member.exception.AlreadyExistNicknameException;
import com.game.review.member.exception.NoImageException;
import com.game.review.member.exception.NoNewPasswordException;
import com.game.review.member.exception.NoSessionDbMatchException;
import com.game.review.member.exception.NoValueException;
import com.game.review.member.exception.PasswordNotMatchingException;
import com.game.review.member.service.MemberUpdateService;
import com.game.review.member.validate.MemberUpdateCommandValidator;

@Controller
public class MemberUpdateController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberUpdateController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MemberUpdateService memberUpdateService;
	
	//?????? ?????????(??????)
	@RequestMapping(value="member/update/profile")
	public String goProfile(Model model) {
		//????????? ?????? userdetails ??????
		return "member/update/profile";
	}
	
	//?????? ?????? ?????? ?????????
	@RequestMapping(value="member/update/updateForm", method=RequestMethod.GET)
	public String memberUpdateForm(@ModelAttribute("updateCommand") MemberUpdateCommand memberUpdateCommand, Model model) {
		//???????????? ???????????? ???????????? ??????
		
		//spring security session????????? ?????? ???????????? ???????????????
		return "member/update/updateForm";
	}
	
	//ajax??? ???????????? ?????? ?????? ?????? ?????????
	@RequestMapping("member/update/ajaxMemberUpdateForm")
	public String ajaxMemberUpdateForm() {
		return "member/update/ajaxUpdateForm";
	}
	
	//ajax??? ???????????? ???????????? ?????? ?????? ?????????
	@RequestMapping("member/update/ajaxPwdUpdateForm")
	public String ajaxPwdUpdateForm() {
		return "member/update/ajaxPwdForm";
	}
	
	//ajax??? ?????? ?????? ??????
	@RequestMapping(value="member/update/ajaxMemberUpdate", method=RequestMethod.POST)
	@ResponseBody
	public String ajaxMemberUpdate(
			MemberUpdateCommand memberUpdateCommand, //formdata??? ???????????? @RequestBody????????????
			@AuthenticationPrincipal 
			LoginUserDetails loginUserDetails) throws Exception {
		
		logger.info("???????????? ??????????????? ??? : "+memberUpdateCommand);
		logger.info("????????? : " + loginUserDetails);
		try {
			if(memberUpdateCommand.getNickname() == null || memberUpdateCommand.getNickname().trim().isEmpty()) {
				//??????
				logger.debug("????????? ??????=2");
				return "2";
			}
			memberUpdateService.updateProfile(memberUpdateCommand, loginUserDetails);
			loginUserDetails.setNickname(memberUpdateCommand.getNickname());
			//?????? ??????????????? ??????
			logger.debug("??????!=1");
			return "0";//??????
		}catch(AlreadyExistNicknameException e) {
			logger.error("????????? ??????!");
			return "1";
		}catch(NoImageException e) {
			logger.error("?????????????????? ??????!");
			return "4";
		}catch(NoSessionDbMatchException e) {
			logger.error("????????????!");
			return "3";
		}
	}
	
	//ajax??? ???????????? ??????
	@RequestMapping(value="member/update/ajaxPwdUpdate", method=RequestMethod.POST)
	@ResponseBody
	public String changePwd(
			@RequestBody 
			ChangePasswordCommand changePasswordCommand, 
			@AuthenticationPrincipal 
			LoginUserDetails loginUserDetails) throws Exception {
		logger.info("password from input : " + changePasswordCommand.getPassword());
		logger.info("confirmPassword from input : " + changePasswordCommand.getConfirmPassword());
		logger.info("???????????? ????????? : " + loginUserDetails.getUsername());
		//?????? = 1, ?????? = 0, ????????? = 2, ?????? =  3, ????????????????????? ????????? = 4
		try {
			String res = memberUpdateService.updatePwd(
					changePasswordCommand.getPassword(), 
					changePasswordCommand.getConfirmPassword(), 
					loginUserDetails.getUsername(),
					loginUserDetails.getPassword()
					);
			
			//????????? ??????
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							loginUserDetails.getUsername(), changePasswordCommand.getPassword()
							)
					);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			logger.info("authentication.getCredentials() : " +authentication.getCredentials() );
			return res;
		}catch(PasswordNotMatchingException e) {
			logger.error("???????????? ?????????!");
			return "2";
		}catch(NoValueException e) {
			logger.error("??????!");
			return "3";
		}catch(NoNewPasswordException e) {
			logger.error("?????? ??????????????? ??????!");
			return "4";
		}
	}
	
	
	
	/*
	//?????? ?????? ??????
	@RequestMapping(value="member/update/update", method=RequestMethod.POST)
	public String memberUpdate(@ModelAttribute("updateCommand") MemberUpdateCommand memberUpdateCommand, BindingResult errors, Model model, @AuthenticationPrincipal LoginUserDetails loginUserDetails) {
		new MemberUpdateCommandValidator().validate(memberUpdateCommand, errors);
		if(errors.hasErrors()) {
			return "member/update/updateForm";
		}
		logger.info("???????????? ??????????????? ??? : "+memberUpdateCommand.getNickname());
		logger.info("????????? : " + loginUserDetails);
		try {
			memberUpdateService.updateProfile(memberUpdateCommand, loginUserDetails);
			loginUserDetails.setNickname(memberUpdateCommand.getNickname());
		} catch(AlreadyExistNicknameException e) {
			logger.error("????????? ??????!");
			errors.rejectValue("nickname", "dupNickname");
			return "member/update/updateForm";
		} catch(NoSessionDbMatchException e) {
			return "exceptions/profileUpdateEx";
		}
		return "redirect:/member/update/profile";
	}
	*/
	//????????? ???????????? ???????????? ???
	@RequestMapping(value="member/update/authForm")
	public String authForm(@ModelAttribute("passwordCommand") PasswordCommand passwordCommand) {
		
		return "member/update/authForm";
	}
	
	//????????? ???????????? ??????
	@RequestMapping(value="member/update/auth")
	public String auth(@ModelAttribute("passwordCommand") PasswordCommand passwordCommand, BindingResult errors, @AuthenticationPrincipal LoginUserDetails loginUserDetails) {
		if(passwordCommand.getPassword() == null && passwordCommand.getPassword().trim().isEmpty()) {
			errors.rejectValue("password", "required");
			return "member/update/authForm";
		}
		logger.info(loginUserDetails.getPassword());
		boolean isMatch = memberUpdateService.auth(passwordCommand.getPassword(), loginUserDetails.getPassword());
		if(!isMatch) {
			errors.rejectValue("password", "passwordNotMatch");
			return "member/update/authForm";
		}
		return "member/update/authSuccess";
	}
}
