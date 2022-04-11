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
import com.game.review.member.command.FindLoginDataCommand;
import com.game.review.member.dao.MemberDAO;
import com.game.review.member.dto.MemberDTO;
import com.game.review.member.email.MailUtils;
import com.game.review.member.email.TempKey;
import com.game.review.member.exception.NoEmailExistException;

@Service
public class FindLoginDataService {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public int findEmail(FindLoginDataCommand findLoginDataCommand) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", findLoginDataCommand.getEmail());
		map.put("name", findLoginDataCommand.getName());
		int result = memberDAO.countByEmailAndName(map);
		if(result == 0) {
			throw new NoEmailExistException();
		}
		return result;
	}
	
	@Transactional
	public int findPassword(FindLoginDataCommand findLoginDataCommand) throws MessagingException, UnsupportedEncodingException {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", findLoginDataCommand.getEmail());
		map.put("name", findLoginDataCommand.getName());
		MemberDTO member = (MemberDTO) memberDAO.selectByEmailAndName(map);
		if(member == null) {
			throw new NoEmailExistException();
		}
		
		//generate a temp password
		String tempPwd = new TempKey().getKey(6, false);
		String encodedTempPwd = encoder.encode(tempPwd);
		member.setmPassword(encodedTempPwd);
		
		int result = memberDAO.updateTempPassword(member);
		
		//send mail for finding password
		MailUtils sendMail = new MailUtils(mailSender);
		
		logger.debug("인코딩전 임시 비밀번호 : " + tempPwd);
		sendMail.setSubject("[게임리뷰] 임시비밀번호 발급 안내");
		sendMail.setText(
				"<h1>임시비밀번호 발급 안내</h1>"
				+ "<br>"
				+ "<p>"
				+ "<b><font color=\"pink\">" + member.getmName() + "</font>님</b><br>"
				+ "<b><i>임시비밀번호 : " + tempPwd +"</i></b></p>"
				+ "<div style=\"border:1px solid black; background-color:gray\">"
				+ "<p><a href=\"http://localhost:8080/review/member/loginForm\""
				+ " target='_blank'><i>[로그인 페이지]</i></a></p>"
				+ "</div>"
				);
		sendMail.setFrom("leechanseong89@gmail.com", "Admin<게임리뷰사이트>");
		sendMail.setTo(member.getmEmail());
		sendMail.send();
		return result;
	}
	
	
}
