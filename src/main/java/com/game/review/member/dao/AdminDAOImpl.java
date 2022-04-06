package com.game.review.member.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.review.member.dto.AdminDTO;
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
}
