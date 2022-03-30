package com.game.review.member.dao;

public interface AdminDAO {
	
	public Object selectBySeq(Long mNum);
	public Object selectById(String mEmail);
	
	
}
