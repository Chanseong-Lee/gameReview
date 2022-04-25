package com.game.review.member.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.review.member.dao.MemberDAO;

@Service
public class MemberDeleteService {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDeleteService.class);

	@Autowired
	private MemberDAO memberDAO;
	
	public int memberDelete(Long num) {
		int res = memberDAO.delete(num);
		if(res != 1) {
			logger.error("삭제실패!!!!!!");
		}
		return res;
	}
}
