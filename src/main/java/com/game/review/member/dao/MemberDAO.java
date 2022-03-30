package com.game.review.member.dao;

import java.util.List;
import java.util.Map;

import com.game.review.member.dto.MemberDTO;

public interface MemberDAO {
	//전체멤버
	public List<MemberDTO> selectAll();
	
	//한명만
	public Object selectBySeq(Long mNum);
	public Object selectByEmail(String mEmail);
	
	//전체 회원수
	public int count();

	//등록
	public int insert(MemberDTO memberDTO);
	
	//수정(마이페이지에서)
	public int update(MemberDTO memberDTO);
	
	//삭제(탈퇴)
	public int delete(Long mNum);
	
	//인증키비교
	public int countByValidKey(String validKey);
	
	//인증키
	public int updateValidKey(Map<String, String> dataValues);
	
	
	
}
