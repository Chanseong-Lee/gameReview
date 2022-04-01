package com.game.review.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.review.member.dao.MemberDAO;
import com.game.review.member.dto.MemberDTO;

@Service
public class EmailDupCheckService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	public boolean emailChecker(String email) {
		boolean isDup =false;
		
		MemberDTO member = (MemberDTO) memberDAO.selectByEmail(email);
		if(member != null) {
			isDup=true;
			return isDup;
		}
		return isDup;
	}
}
