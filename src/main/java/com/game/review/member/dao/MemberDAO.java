package com.game.review.member.dao;

import java.util.List;
import java.util.Map;

import com.game.review.member.dto.MemberDTO;
import com.game.review.member.dto.ProfileImgDTO;

public interface MemberDAO {
	//전체멤버
	public List<MemberDTO> selectAll();
	
	//한명만
	public Object selectBySeq(Long mNum);
	public Object selectByEmail(String mEmail);
	
	//아이디 찾기
	public int countByEmailAndName(Map<String, String> map);
	
	//비밀번호찾기
	public Object selectByEmailAndName(Map<String, String> map);
	
	public Object selectByNickname(String mNickname);
	
	//ajax 이메일 중복체크용
	public int countByEmail(String mEmail);
	
	//ajax 닉네임 중복체크용
	public int countByNickname(String mNickname);
	
	//전체 회원수
	public int countAllMember();

	//등록
	public int insert(MemberDTO memberDTO);
	
	//수정(마이페이지에서)
	public int updateProfile(MemberDTO memberDTO);

	//관리자에의해 프로필 수정
	public int updateProfileByAdmin(MemberDTO memberDTO);
	
	//회원가입 시 디폴트 프로필 이미지 삽입
	public int insertDefaultProfileImg(String mEmail);
	
	//회원가입 시 디폴트 아이콘 세팅
	public int insertDefaultIcon(String mEmail);
	
	//프로필사진 수정
	public int updateProfileImg(ProfileImgDTO profileImgDTO);
	
	
	public Object selectProfileImg(Long mNum);
	
	//임시비밀번호발급
	public int updateTempPassword(MemberDTO memberDTO);

	//비밀번호 수정
	public int updatePassword(MemberDTO memberDTO);
	
	//인증키
	public int updateValidKey(Map<String, String> dataValues);
	
	//삭제(탈퇴)
	public int delete(Long mNum);
	
	//인증키비교
	public int countByValidKey(String validKey);
	
	//멤버관리 검색
	public List<MemberDTO> searchByEmail(String email);
	public List<MemberDTO> searchByNickname(String nickname);
	public List<MemberDTO> searchByName(String name);
	
	
	
}
