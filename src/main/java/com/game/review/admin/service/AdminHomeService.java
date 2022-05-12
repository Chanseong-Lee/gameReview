package com.game.review.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.review.game.dao.ArticlesDAO;
import com.game.review.game.dao.GameDAO;
import com.game.review.member.dao.MemberDAO;
import com.game.review.pointshop.dao.PointshopDAO;
@Service
public class AdminHomeService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private PointshopDAO pointshopDAO;
	
	@Autowired
	private GameDAO gameDAO;
	
	@Autowired
	private ArticlesDAO articlesDAO;
	
	public int countAllMember() {
		return memberDAO.countAllMember();
	}
	
	public int countAllItem() {
		return pointshopDAO.countAllItem();
	}
	
	public int countAllGame() {
		return gameDAO.countGame();
	}
	
	public int countAllReview() {
		return articlesDAO.countArticles();
	}
}
