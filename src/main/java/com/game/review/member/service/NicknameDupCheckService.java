package com.game.review.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.review.member.dao.MemberDAO;

@Service
public class NicknameDupCheckService {
	@Autowired
	private MemberDAO memberDAO;
	
	public int nicknameChecker(String nickname) {
		int checker = -1;
		//중복아니면 0, 중복있으면 1
		checker = memberDAO.countByNickname(nickname);
		return checker;
	}
}
