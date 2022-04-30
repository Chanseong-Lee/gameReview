package com.game.review.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.review.member.dao.MemberDAO;
import com.game.review.pointshop.dao.PointshopDAO;
@Service
public class AdminHomeService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private PointshopDAO pointshopDAO;
	
	public int countAllMember() {
		return memberDAO.countAllMember();
	}
	
	public int countAllItem() {
		return pointshopDAO.countAllItem();
	}
}
