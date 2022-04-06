package com.game.review.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.review.member.command.AuthInfo;
import com.game.review.member.dao.MemberDAO;
import com.game.review.member.dto.MemberDTO;
import com.game.review.member.exception.PasswordNotMatchingException;
import com.game.review.member.exception.NoEmailExistException;

@Service
public class LoginService {
	@Autowired
	private MemberDAO memberDAO;
	
	public AuthInfo authenticate(String email, String password) {
		MemberDTO member = (MemberDTO) memberDAO.selectByEmail(email);
		if(member == null) {
			throw new NoEmailExistException();
		}
		if(!member.getmPassword().equals(password)) {
			throw new PasswordNotMatchingException();
		}
		AuthInfo info = new AuthInfo();
		info.setmNum(member.getmNum());
		info.setEmail(member.getmEmail());
		info.setPassword(member.getmPassword());
		info.setNickname(member.getmNickname());
		//info.setAuthLevel(member.getAuthNum());
		return info;
	}
}
