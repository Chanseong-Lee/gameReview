package com.game.review.member.dao;

import java.util.List;

import com.game.review.member.dto.AdminDTO;

public interface AdminDAO {
	
	public Object selectBySeq(Long num);
	public Object selectById(String Id);
	public List<AdminDTO> selectAll();
}
