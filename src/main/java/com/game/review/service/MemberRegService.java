package com.game.review.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.review.HomeController;
import com.game.review.command.MemberRegCommand;
import com.game.review.dao.MemberDAO;
import com.game.review.dto.MemberDTO;
import com.game.review.email.MailUtils;
import com.game.review.email.TempKey;
@Service
public class MemberRegService {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private MemberDAO memberDAO;
	private JavaMailSender mailSender;
	
	@Transactional
	public void insertMember(MemberRegCommand mc) throws MessagingException, UnsupportedEncodingException {
		
		//encryption code
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPw = encoder.encode(mc.getPassword());
		
		//generate key for email authentication
		String validKey = "N";
		validKey = new TempKey().getKey(50, false);
		
		logger.debug(validKey);
		
		//set parameters of dto
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setmEmail(mc.getEmail());
		memberDTO.setmPassword(encodedPw);
		memberDTO.setmNickname(mc.getNickname());
		memberDTO.setmIsvalid(validKey);
		memberDAO.insert(memberDTO);
		
		//send mail for validation
		MailUtils sendMail = new MailUtils(mailSender);
		
		sendMail.setSubject("[게임리뷰] 이메일 인증");//제목
		sendMail.setText(
				"<h1>메일 인증</h1>"
				+ "<br>"
				+ "<p>"
				+ "<b><font color=\"pink\">" + memberDTO.getmName() + "</font>님</b><br>"
				+ "게임리뷰에 가입해주셔서 대단히 감사합니다."
				+ "<br>아래 [이메일 인증 확인]을 눌러주세요.</p>"
				+ "<div border=\"1\" background-color=\"gray\">"
				+ "<p><a href=\"http://localhost:8080/review/verifyEmail?email="
				+ memberDTO.getmEmail()+"&validKey="
				+ validKey + "\" target='_blank'><i>[이메일 인증 확인]</i></a></p>"
				+ "</div>"
				);
		sendMail.setFrom("leechanseong89@gmail.com", "이찬성");
		sendMail.setTo(memberDTO.getmEmail());
		sendMail.send();
	}
}
