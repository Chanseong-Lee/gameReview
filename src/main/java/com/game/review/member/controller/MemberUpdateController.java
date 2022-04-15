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
import com.game.review.member.exception.NoNewPasswordException;
import com.game.review.member.exception.NoSessionDbMatchException;
import com.game.review.member.exception.NoValueException;
import com.game.review.member.exception.PasswordNotMatchingException;
import com.game.review.member.service.MemberUpdateService;
import com.game.review.member.validate.MemberUpdateCommandValidator;

@Controller
public class MemberUpdateController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MemberUpdateService memberUpdateService;
	
	//회원 프로필(정보)
	@RequestMapping(value="member/update/profile")
	public String goProfile(Model model) {
		//뷰에서 바로 userdetails 출력
		return "member/update/profile";
	}
	
	//회원 수정 부모 페이지
	@RequestMapping(value="member/update/updateForm", method=RequestMethod.GET)
	public String memberUpdateForm(@ModelAttribute("updateCommand") MemberUpdateCommand memberUpdateCommand, Model model) {
		//업데이트 구현안함 테스트만 성공
		
		//spring security session수정도 같이 해줘야함 구글링할것
		return "member/update/updateForm";
	}
	
	//ajax로 끼워넣을 회원 수정 자식 페이지
	@RequestMapping("member/update/ajaxMemberUpdateForm")
	public String ajaxMemberUpdateForm() {
		return "member/update/ajaxUpdateForm";
	}
	
	//ajax로 끼워넣을 비밀번호 수정 자식 페이지
	@RequestMapping("member/update/ajaxPwdUpdateForm")
	public String ajaxPwdUpdateForm() {
		return "member/update/ajaxPwdForm";
	}
	
	//ajax로 회원 수정 처리
	@RequestMapping(value="member/update/ajaxMemberUpdate", method=RequestMethod.POST)
	@ResponseBody
	public String ajaxMemberUpdate(@RequestBody MemberUpdateCommand memberUpdateCommand, @AuthenticationPrincipal LoginUserDetails loginUserDetails) throws Exception {
		logger.info("컨트롤러 커맨드객체 값 : "+memberUpdateCommand.getNickname());
		logger.info("세션값 : " + loginUserDetails);
		try {
			if(memberUpdateCommand.getNickname() == null || memberUpdateCommand.getNickname().trim().isEmpty()) {
				//빈값
				logger.debug("닉네임 빈값=2");
				return "2";
			}
			memberUpdateService.updateProfile(memberUpdateCommand, loginUserDetails.getNum());
			loginUserDetails.setNickname(memberUpdateCommand.getNickname());
			logger.debug("정상!=1");
			return "0";//정상
		}catch(AlreadyExistNicknameException e) {
			logger.error("닉네임 중복!");
			return "1";
		}catch(NoSessionDbMatchException e) {
			logger.error("세션이상!");
			return "3";
		}
	}
	
	//ajax로 비밀번호 변경
	@RequestMapping(value="member/update/ajaxPwdUpdate", method=RequestMethod.POST)
	@ResponseBody
	public String changePwd(
			@RequestBody 
			ChangePasswordCommand changePasswordCommand, 
			@AuthenticationPrincipal 
			LoginUserDetails loginUserDetails) throws Exception {
		logger.info("password from input : " + changePasswordCommand.getPassword());
		logger.info("confirmPassword from input : " + changePasswordCommand.getConfirmPassword());
		logger.info("로그인된 계정명 : " + loginUserDetails.getUsername());
		//성공 = 1, 실패 = 0, 불일치 = 2, 빈값 =  3, 기존비밀번호와 동일함 = 4
		try {
			String res = memberUpdateService.updatePwd(
					changePasswordCommand.getPassword(), 
					changePasswordCommand.getConfirmPassword(), 
					loginUserDetails.getUsername(),
					loginUserDetails.getPassword()
					);
			
			//세션도 수정
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							loginUserDetails.getUsername(), changePasswordCommand.getPassword()
							)
					);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			logger.info("authentication.getCredentials() : " +authentication.getCredentials() );
			return res;
		}catch(PasswordNotMatchingException e) {
			logger.error("비밀번호 불일치!");
			return "2";
		}catch(NoValueException e) {
			logger.error("빈값!");
			return "3";
		}catch(NoNewPasswordException e) {
			logger.error("기존 비밀번호와 동일!");
			return "4";
		}
	}
	
	
	
	
	//회원 수정 처리
	@RequestMapping(value="member/update/update", method=RequestMethod.POST)
	public String memberUpdate(@ModelAttribute("updateCommand") MemberUpdateCommand memberUpdateCommand, BindingResult errors, Model model, @AuthenticationPrincipal LoginUserDetails loginUserDetails) {
		new MemberUpdateCommandValidator().validate(memberUpdateCommand, errors);
		if(errors.hasErrors()) {
			return "member/update/updateForm";
		}
		logger.info("컨트롤러 커맨드객체 값 : "+memberUpdateCommand.getNickname());
		logger.info("세션값 : " + loginUserDetails);
		try {
			memberUpdateService.updateProfile(memberUpdateCommand, loginUserDetails.getNum());
			loginUserDetails.setNickname(memberUpdateCommand.getNickname());
		} catch(AlreadyExistNicknameException e) {
			logger.error("닉네임 중복!");
			errors.rejectValue("nickname", "dupNickname");
			return "member/update/updateForm";
		} catch(NoSessionDbMatchException e) {
			return "exceptions/profileUpdateEx";
		}
		return "redirect:/member/update/profile";
	}
	
	//수정전 비밀번호 입력하는 폼
	@RequestMapping(value="member/update/authForm")
	public String authForm(@ModelAttribute("passwordCommand") PasswordCommand passwordCommand) {
		
		return "member/update/authForm";
	}
	
	//수정전 비밀번호 처리
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
