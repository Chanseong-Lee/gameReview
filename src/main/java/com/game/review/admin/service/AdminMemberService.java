package com.game.review.admin.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.game.review.admin.command.AdminMemberUpdateCommand;
import com.game.review.admin.command.SearchCommand;
import com.game.review.member.dao.MemberDAO;
import com.game.review.member.dto.MemberDTO;
import com.game.review.member.dto.ProfileImgDTO;
import com.game.review.member.exception.AlreadyExistNicknameException;
import com.game.review.member.exception.NoExistMemberException;
import com.game.review.member.exception.NoImageException;
import com.game.review.member.exception.NoSessionDbMatchException;
import com.game.review.member.service.MemberUpdateService;
import com.game.review.member.validate.FileTypeByTika;

@Service
public class AdminMemberService {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminMemberService.class);
	
	@Autowired
	private MemberDAO memberDAO;
	
	public List<MemberDTO> showMemberList() {
		return memberDAO.selectAll();
	}
	
	public int countAll() {
		return memberDAO.countAll();
	}
	
	public List<MemberDTO> search(SearchCommand searchCommand){
		List<MemberDTO> members = null;
		
		if(searchCommand.getSearchType().equals("email")) {
			members = memberDAO.searchByEmail(searchCommand.getSearchValue());
		}else if(searchCommand.getSearchType().equals("name")) {
			members = memberDAO.searchByName(searchCommand.getSearchValue());
		}else if(searchCommand.getSearchType().equals("nickname")) {
			members = memberDAO.searchByNickname(searchCommand.getSearchValue());
		}
		
		return members;
	}
	
	public MemberDTO detail(Long num) {
		MemberDTO member =  (MemberDTO) memberDAO.selectBySeq(num);
		if(member == null) {
			throw new NoExistMemberException();
		}
		return member;
	}
	
	public ProfileImgDTO profileImg(Long num) {
		ProfileImgDTO imgDTO = (ProfileImgDTO) memberDAO.selectProfileImg(num);
		return imgDTO;
	}
	
	@Transactional
	public void updateProfileByAdmin(AdminMemberUpdateCommand amuc, Long num) throws NoSessionDbMatchException, AlreadyExistNicknameException, NoImageException {
		
		MemberDTO member = detail(num);
		
		//기존 닉네임과 일치하면 굳이 db랑 비교안함
		if(!member.getmNickname().equals(amuc.getNickname())) {
			//닉네임 중복체크
			int dup = memberDAO.countByNickname(amuc.getNickname());
			if(dup == 1) {
				throw new AlreadyExistNicknameException();
			}
		}
		
		String path = "C:\\stsproject\\upload\\images\\profile";
		MultipartFile profileImg = amuc.getProfileImg();
		
		InputStream inputStream = null;
		File file = null;
		File oldFile = null;
		String unknownProfile = "unknown_profile.jpg";
		
		try {
			
			if(profileImg != null && !profileImg.isEmpty()) { //파일이 있다면 mimetype검증
				String savedFilename = UUID.randomUUID().toString().replaceAll("-", "") + profileImg.getOriginalFilename();
				logger.info("파일명 : " + savedFilename);
				inputStream = profileImg.getInputStream();
				boolean isImage = FileTypeByTika.validImgFile(inputStream);
				ProfileImgDTO oldImgDTO = (ProfileImgDTO) memberDAO.selectProfileImg(member.getmNum());
				String oldName = oldImgDTO.getProfileImgname();
				if(!isImage) { //이미지파일이 아니면 예외
					inputStream.close();
					throw new NoImageException();
					
				}else { //이미지 파일일경우 db저장
					file = new File(path, savedFilename);
					inputStream.close();
					profileImg.transferTo(file); //파일 저장
					ProfileImgDTO profileImgDTO = new ProfileImgDTO();
					profileImgDTO.setProfileImgname(savedFilename);
					profileImgDTO.setmNum(member.getmNum());
					int resForNewImg = memberDAO.updateProfileImg(profileImgDTO);
					logger.info("새 프로필 사진 업데이트 성공? : " + resForNewImg);
				}
				
				
				if(!oldName.equals(unknownProfile)) {
					oldFile = new File(path, oldName);
					if(oldFile.exists()) {
						if(oldFile.delete()) {
							logger.info("기존 프로필사진 삭제 성공!");
						}else {
							logger.info("기존 프로필사진 삭제 실패...");
						}
					}
				}
			}else { //파일이 null일때
				//뷰에서 기본프로필로 돌아가기 선택했을때
				if(amuc.isBackToBasicImg()) {
					
					//변경전 기존 프로필사진명 불러오기
					ProfileImgDTO oldImgDTO = (ProfileImgDTO) memberDAO.selectProfileImg(member.getmNum());
					String oldName = oldImgDTO.getProfileImgname();
					
					//기본프로필로 db저장
					ProfileImgDTO profileImgDTO = new ProfileImgDTO();
					profileImgDTO.setProfileImgname(unknownProfile);
					profileImgDTO.setmNum(member.getmNum());
					int resForBasicImg = memberDAO.updateProfileImg(profileImgDTO);
					logger.info("기본 프로필 사진 업데이트 성공? : " + resForBasicImg);
					
					//기존사진 삭제
					if(!oldName.equals(unknownProfile)) {
						oldFile = new File(path, oldName);
						if(oldFile.exists()) {
							if(oldFile.delete()) {
								logger.info("기존 프로필사진 삭제 성공!");
							}else {
								logger.info("기존 프로필사진 삭제 실패...");
							}
						}
					}
				}
			}
			
			//회원정보 수정
			int res = -1;
			MemberDTO memberDTO = new MemberDTO();
			memberDTO.setmName(amuc.getName());
			memberDTO.setmNickname(amuc.getNickname());
			memberDTO.setmPoint(amuc.getPoint());
			memberDTO.setmNum(member.getmNum());
			memberDAO.updateProfileByAdmin(memberDTO);
			
		}catch(IOException e) {
			logger.error("IO예외발생!");
			e.printStackTrace();
		}
		
	}

}
