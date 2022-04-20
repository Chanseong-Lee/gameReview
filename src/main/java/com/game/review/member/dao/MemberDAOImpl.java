package com.game.review.member.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.review.member.dto.MemberDTO;
import com.game.review.member.dto.ProfileImgDTO;

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
	public int countAll() {
		return sqlSessionTemplate.selectOne("countAll");
	}

	@Override
	public int insert(MemberDTO memberDTO) {
		return sqlSessionTemplate.insert("insert", memberDTO);
	}

	@Override
	public int updateProfile(MemberDTO memberDTO) {
		return sqlSessionTemplate.update("updateProfile", memberDTO);
	}
	
	@Override
	public int insertDefaultProfileImg(String mEmail) {
		return sqlSessionTemplate.insert("insertDefaultProfileImg", mEmail);
	}
	
	@Override
	public int updateProfileImg(ProfileImgDTO profileImgDTO) {
		return sqlSessionTemplate.update("updateProfileImg", profileImgDTO);
	}
	
	@Override
	public ProfileImgDTO selectProfileImg(Long mNum) {
		return sqlSessionTemplate.selectOne("selectProfileImg", mNum);
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

	@Override
	public int countByEmailAndName(Map<String, String> map) {
		return sqlSessionTemplate.selectOne("countByEmailAndName", map);
	}

	@Override
	public Object selectByEmailAndName(Map<String, String> map) {
		return sqlSessionTemplate.selectOne("selectByEmailAndName", map);
	}

	@Override
	public int updateTempPassword(MemberDTO memberDTO) {
		return sqlSessionTemplate.update("updateTempPassword", memberDTO);
	}
	
	@Override
	public int updatePassword(MemberDTO memberDTO) {
		return sqlSessionTemplate.update("updatePassword", memberDTO);
	}

	@Override
	public List<MemberDTO> searchByEmail(String email) {
		return sqlSessionTemplate.selectList("searchByEmail", email);
	}

	@Override
	public List<MemberDTO> searchByNickname(String nickname) {
		return sqlSessionTemplate.selectList("searchByNickname", nickname);

	}

	@Override
	public List<MemberDTO> searchByName(String name) {
		return sqlSessionTemplate.selectList("searchByName", name);

	}

	@Override
	public Object selectByNickname(String mNickname) {
		return sqlSessionTemplate.selectOne("selectByNickname", mNickname);
	}
	
	@Override
	public int updateProfileByAdmin(MemberDTO memberDTO) {
		return sqlSessionTemplate.update("updateProfileByAdmin", memberDTO); 
	}

}
