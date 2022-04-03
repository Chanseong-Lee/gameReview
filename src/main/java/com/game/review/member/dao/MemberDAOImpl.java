package com.game.review.member.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.review.member.dto.MemberDTO;

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
	public int countByEmail(String mEmail) {
		return sqlSessionTemplate.selectOne("countByEmail", mEmail);
	}
	
	@Override
	public int countByNickname(String mNickname) {
		return sqlSessionTemplate.selectOne("countByNickname", mNickname);
	}
	
	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(MemberDTO memberDTO) {
		return sqlSessionTemplate.insert("insert", memberDTO);
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

	@Override
	public int updateValidKey(Map<String, String> dataValues) {
		return sqlSessionTemplate.update("updateValidKey", dataValues);
	}

	@Override
	public int countByValidKey(String validKey) {
		return sqlSessionTemplate.selectOne("countByValidKey", validKey);
	}

}
