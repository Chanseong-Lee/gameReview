package com.game.review.admin.dao;

import java.util.List;

import com.game.review.admin.dto.AdminDTO;

public interface AdminDAO {
	
	public Object selectBySeq(Long num);
	public Object selectById(String Id);
	public List<AdminDTO> selectAll();
	public int updateAdminProfile(AdminDTO adminDTO);
	public int updateAdminProfileImg(AdminDTO adminDTO);
}
