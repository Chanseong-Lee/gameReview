package com.game.review.member.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.game.review.HomeController;
import com.game.review.member.command.LoginUserDetails;
import com.game.review.member.command.MemberUpdateCommand;
import com.game.review.member.dao.MemberDAO;
import com.game.review.member.dto.MemberDTO;
import com.game.review.member.dto.ProfileImgDTO;
import com.game.review.member.exception.AlreadyExistNicknameException;
import com.game.review.member.exception.NoImageException;
import com.game.review.member.exception.NoNewPasswordException;
import com.game.review.member.exception.NoSessionDbMatchException;
import com.game.review.member.exception.NoValueException;
import com.game.review.member.exception.PasswordNotMatchingException;
import com.game.review.member.validate.FileTypeByTika;
@Service
public class MemberUpdateService {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public void updateProfile(MemberUpdateCommand memberUpdateCommand, LoginUserDetails loginUserDetails) throws NoSessionDbMatchException, AlreadyExistNicknameException {
		
		if(!memberUpdateCommand.getNickname().equals(loginUserDetails.getNickname())) {//기존이름과 같지않을때 db와 비교
			int dup = memberDAO.countByNickname(memberUpdateCommand.getNickname());
			if(dup == 1) {
				throw new AlreadyExistNicknameException();
			}
		}
		String path = "C:\\stsproject\\upload\\images\\profile";
		MultipartFile profileImg = memberUpdateCommand.getProfileImg();
		
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
				
				if(!isImage) { //이미지파일이 아니면 예외
					inputStream.close();
					throw new NoImageException();
					
				}else { //이미지 파일이라면 
					file = new File(path, savedFilename);
					inputStream.close();
					profileImg.transferTo(file); //파일 저장
					
					//저장후 DB업데이트
					ProfileImgDTO profileImgDTO = new ProfileImgDTO();
					profileImgDTO.setProfileImgname(savedFilename);
					profileImgDTO.setmNum(loginUserDetails.getNum());
					int resForNewImg = memberDAO.updateProfileImg(profileImgDTO);
					logger.info("새 프로필 사진 업데이트 성공? : " + resForNewImg);
					
					//DB업데이트 후 업데이트 전 사진 삭제
					if(!loginUserDetails.getProfileImgname().equals(unknownProfile)) {//기존 파일이 기본사진이 아니면 삭제
						oldFile = new File(path, loginUserDetails.getProfileImgname());
						if(oldFile.exists()) {
							if(oldFile.delete()) {
								logger.info("기존 프로필사진 삭제 성공!");
							}else {
								logger.info("기존 프로필사진 삭제 실패...");
							}
						}
					}
					//세션값 수정
					loginUserDetails.setProfileImgname(savedFilename);
				}
				
			} else { //파일이 null일때
				
				//뷰에서 기본프로필로 돌아가기 선택했을경우
				if(memberUpdateCommand.isBackTobasicImg()) { 
					
					ProfileImgDTO profileImgDTO = new ProfileImgDTO();
					profileImgDTO.setProfileImgname(unknownProfile);
					profileImgDTO.setmNum(loginUserDetails.getNum());
					int resForBasicImg = memberDAO.updateProfileImg(profileImgDTO);
					logger.info("기본 프로필 사진 업데이트 성공? : " + resForBasicImg);
					
					
					
					//DB업데이트 후 업데이트 전 사진 삭제
					if(!loginUserDetails.getProfileImgname().equals(unknownProfile)) {//기존 파일이 기본사진이 아니면 삭제
						oldFile = new File(path, loginUserDetails.getProfileImgname());
						if(oldFile.exists()) {
							if(oldFile.delete()) {
								logger.info("기존 프로필사진 삭제 성공!");
							}else {
								logger.info("기존 프로필사진 삭제 실패...");
							}
						}
					}
					//세션값 수정
					loginUserDetails.setProfileImgname(unknownProfile);
				}
			}
			
			//회원정보 수정 시작
			//세션에 저장되어있는 기존 닉네임과 커맨드객체의 닉네임이 일치하면 db접속 안함
			if(!memberUpdateCommand.getNickname().equals(loginUserDetails.getNickname())) {
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setmNum(loginUserDetails.getNum());
				memberDTO.setmNickname(memberUpdateCommand.getNickname());
				
				int res = memberDAO.updateProfile(memberDTO);
				logger.info("닉네임 업데이트 성공? : " + res);
			
				if(res != 1) {
					throw new NoSessionDbMatchException();
				}
			} else {
				logger.info("기존 닉네임과 같으므로 db접근 안함");
			}
			
			
		}catch(IOException e ) {
			logger.error("IO예외발생!");
		}
		
		
	}
	
	public String updatePwd(String newPassword, String confirmPassword, String email, String oldPassword) throws NoValueException, PasswordNotMatchingException, NoNewPasswordException {
		
		if(newPassword.trim().isEmpty() || newPassword == null) {
			throw new NoValueException();
		}
		
		if(encoder.matches(newPassword, oldPassword)) {
			throw new NoNewPasswordException();
		}
		
		if(!newPassword.equals(confirmPassword)) {
			throw new PasswordNotMatchingException();
		}
		
		
		String encodedPwd = encoder.encode(newPassword);
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setmEmail(email);
		memberDTO.setmPassword(encodedPwd);
		int res = memberDAO.updatePassword(memberDTO);
		
		return Integer.toString(res);
		
	}
	
	public boolean auth(String inputPwd, String sessionPwd) {
		boolean isMatch = encoder.matches(inputPwd, sessionPwd);
		return isMatch;
	}
}
