package com.game.review.member.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.game.review.HomeController;
import com.game.review.member.command.MemberUpdateCommand;
import com.game.review.member.dao.MemberDAO;
import com.game.review.member.dto.MemberDTO;
import com.game.review.member.exception.AlreadyExistNicknameException;
import com.game.review.member.exception.NoNewPasswordException;
import com.game.review.member.exception.NoSessionDbMatchException;
import com.game.review.member.exception.NoValueException;
import com.game.review.member.exception.PasswordNotMatchingException;
@Service
public class MemberUpdateService {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public void updateProfile(MemberUpdateCommand memberUpdateCommand, Long num) throws NoSessionDbMatchException, AlreadyExistNicknameException {
		
		int dup = memberDAO.countByNickname(memberUpdateCommand.getNickname());
		if(dup == 1) {
			throw new AlreadyExistNicknameException();
		}
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setmNum(num);
		memberDTO.setmNickname(memberUpdateCommand.getNickname());
		int res = memberDAO.updateProfile(memberDTO);
		logger.info("업데이트 성공?: " + res);
		if(res != 1) {
			throw new NoSessionDbMatchException();
		}
	}
	
	public String updatePwd(String newPassword, String confirmPassword, String email, String oldPassword) throws NoValueException, PasswordNotMatchingException, NoNewPasswordException {
		
		if(newPassword.trim().isEmpty() || newPassword == null) {
			throw new NoValueException();
		}
		
		if(encoder.matches(newPassword, oldPassword)) {
			throw new NoNewPasswordException();
		}
		
		if(!newPassword.equals(confirmPassword)) {
			throw new PasswordNotMatchingException();
		}
		
		
		String encodedPwd = encoder.encode(newPassword);
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setmEmail(email);
		memberDTO.setmPassword(encodedPwd);
		int res = memberDAO.updatePassword(memberDTO);
		
		return Integer.toString(res);
		
	}
	
	public boolean auth(String inputPwd, String sessionPwd) {
		boolean isMatch = encoder.matches(inputPwd, sessionPwd);
		return isMatch;
	}
}
