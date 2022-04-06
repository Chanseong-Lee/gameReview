package com.game.review.member.service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.review.HomeController;
import com.game.review.member.command.MemberRegCommand;
import com.game.review.member.dao.MemberDAO;
import com.game.review.member.dto.MemberDTO;
import com.game.review.member.email.MailUtils;
import com.game.review.member.email.TempKey;
import com.game.review.member.exception.AlreadyExistEmailException;
import com.game.review.member.exception.noExistValidKeyException;
@Service
public class MemberRegService {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public void insertMember(MemberRegCommand mc) throws MessagingException, UnsupportedEncodingException {
		
		//email duplication verification
		MemberDTO hasEmail = (MemberDTO) memberDAO.selectByEmail(mc.getEmail());
		
		if(hasEmail != null) {
			throw new AlreadyExistEmailException();
		}
		
		//encryption code
		String encodedPw = encoder.encode(mc.getPassword());
		
		//generate key for email authentication
		String validKey = "N";
		validKey = new TempKey().getKey(50, false);
		
		logger.debug("validkey in service : " + validKey);
		
		//set parameters of dto
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setmEmail(mc.getEmail());
		memberDTO.setmPassword(encodedPw);
		memberDTO.setmNickname(mc.getNickname());
		memberDTO.setmName(mc.getName());
		memberDTO.setmIsvalid(validKey);
		memberDAO.insert(memberDTO);
		
		//send mail for verification
		MailUtils sendMail = new MailUtils(mailSender);
		logger.debug("mc.getName() : " + mc.getName());
		logger.debug("memberDTO.getmName() : " + memberDTO.getmName());
		logger.debug("memberDTO.getmEmail() : " + memberDTO.getmEmail());
		
		
		sendMail.setSubject("[게임리뷰] 이메일 인증");//제목
		sendMail.setText(
				"<h1>메일 인증</h1>"
				+ "<br>"
				+ "<p>"
				+ "<b><font color=\"pink\">" + memberDTO.getmName() + "</font>님</b><br>"
				+ "게임리뷰에 가입해주셔서 대단히 감사합니다."
				+ "<br>아래 [이메일 인증 확인]을 눌러주세요.</p>"
				+ "<div border=\"1\" background-color=\"gray\">"
				+ "<p><a href=\"http://localhost:8080/review/member/verifyEmail?"
				+ "email=" + memberDTO.getmEmail()
				+ "&validKey=" + validKey 
				+ "\" target='_blank'><i>[이메일 인증 확인]</i></a></p>"
				+ "</div>"
				);
		sendMail.setFrom("leechanseong89@gmail.com", "Admin<게임리뷰사이트>");
		sendMail.setTo(memberDTO.getmEmail());
		sendMail.send();
	}
	
	@Transactional
	public void emailAuth(String email, String validKey) {
		//compare the key from client with a key in db
		int existValidKey = memberDAO.countByValidKey(validKey);
		logger.debug("validKey : "+ validKey);
		logger.debug("existValidKey : "+ existValidKey);
		if(existValidKey != 1) {
			throw new noExistValidKeyException();
		}
		Map<String, String> dataValues = new HashMap<>();
		logger.debug("에러나면 이건 출력되면 안됨");
		dataValues.put("email", email);
		dataValues.put("validKey", "Y");
		//검증키가 Y이면 인증된 MEMBER
		memberDAO.updateValidKey(dataValues);
	}
}
