package com.game.review.admin.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.review.admin.dto.AdminDTO;
@Repository
public class AdminDAOImpl implements AdminDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	@Override
	public Object selectBySeq(Long num) {
		return sqlSessionTemplate.selectOne("selectBySeqAdmin", num);
	}

	@Override
	public Object selectById(String id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("selectByIdAdmin", id);
	}

	@Override
	public List<AdminDTO> selectAll() {
		return sqlSessionTemplate.selectList("selectAllAdmin");
	}

	@Override
	public int updateAdminProfile(AdminDTO adminDTO) {
		return sqlSessionTemplate.update("updateAdminProfile", adminDTO);
	}

	@Override
	public int updateAdminProfileImg(AdminDTO adminDTO) {
		return sqlSessionTemplate.update("updateAdminProfileImg", adminDTO);
	}
}
