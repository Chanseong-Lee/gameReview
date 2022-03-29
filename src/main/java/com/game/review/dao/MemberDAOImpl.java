package com.game.review.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.review.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	//전체멤버
	@Override
	public List<MemberDTO> selectAll() {
		return sqlSessionTemplate.selectList("selectAll");
	}
	
	@Override
	public Object selectBySeq(Long mNum) {
		return sqlSessionTemplate.selectOne("selectBySeq", mNum);
	}
	
	@Override
	public Object selectByEmail(String mEmail) {
		return sqlSessionTemplate.selectOne("selectByEmail", mEmail);
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(MemberDTO memberDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(MemberDTO memberDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long mNum) {
		// TODO Auto-generated method stub
		return 0;
	}

}
