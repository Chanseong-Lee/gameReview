package com.game.review.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.review.member.dao.MemberDAO;

@Service
public class EmailDupCheckService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	public int emailChecker(String email) {
		int checker = -1;
		//중복아니면 0, 중복있으면 1
		checker = memberDAO.countByEmail(email);
		return checker;
		
	}

}
