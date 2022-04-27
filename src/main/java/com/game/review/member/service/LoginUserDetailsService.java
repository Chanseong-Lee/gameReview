package com.game.review.member.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.game.review.HomeController;
import com.game.review.admin.dao.AdminDAO;
import com.game.review.admin.dto.AdminDTO;
import com.game.review.member.command.LoginUserDetails;
import com.game.review.member.dao.MemberDAO;
import com.game.review.member.dto.MemberDTO;
import com.game.review.member.dto.ProfileImgDTO;
import com.game.review.pointshop.dao.PointshopDAO;
import com.game.review.pointshop.dto.InventoryDTO;

// 시큐리티 설정에서  login-page="/member/login"
// /member/login요청이 오면 자동으로 UserDetailsService타입으로 IoC되어있는  loadUserByUsername()가 호출됨
@Service
public class LoginUserDetailsService implements UserDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginUserDetailsService.class);
	@Autowired
	private MemberDAO memberDAO;

	@Autowired
	private AdminDAO adminDAO;
	
	@Autowired
	private PointshopDAO pointshopDAO;

	//시큐리티 session(내부에 Authentication(내부에  UserDetails))
	//loadUserByUsername가 호출되면  Authentication객체를 만들어주고 UserDetails를 그안에 리턴시킨다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//username-parameter="email"로 input태그와 매핑시켜줘야 인자로 제대로 넘어옴
		logger.info("userDetailsService : "+username);
		System.out.println("dao:"+memberDAO);
		MemberDTO member = (MemberDTO) memberDAO.selectByEmail(username);
		AdminDTO admin = (AdminDTO) adminDAO.selectById(username);
		LoginUserDetails loginUserDetails = null;

		if (admin == null && member != null && member.getAuthLevel().equals("ROLE_USER")) {
			// 일반회원
			ProfileImgDTO img = (ProfileImgDTO) memberDAO.selectProfileImg(member.getmNum());
			InventoryDTO icon = (InventoryDTO) pointshopDAO.selectUsingItemBymNum(member.getmNum());
			loginUserDetails = new LoginUserDetails(member.getmEmail(), member.getmPassword(),
					AuthorityUtils.createAuthorityList(member.getAuthLevel())
					);
			loginUserDetails.setNum(member.getmNum());
			loginUserDetails.setNickname(member.getmNickname());
			loginUserDetails.setName(member.getmName());
			loginUserDetails.setPoint(member.getmPoint());
			loginUserDetails.setRegdate(member.getmRegdate());
			loginUserDetails.setProfileImgname(img.getProfileImgname());
			loginUserDetails.setUsingIcon(icon.getItemFilename());
			return loginUserDetails;
		} else if(admin == null && member != null && member.getAuthLevel().equals("ROLE_GUEST")) {	
			//이메일 인증 안한사람은 권한 게스트. 로그인 불가..
			loginUserDetails = new LoginUserDetails(member.getmEmail(), member.getmPassword(), false,
					AuthorityUtils.createAuthorityList(member.getAuthLevel())
					);
			loginUserDetails.setNum(member.getmNum());
			loginUserDetails.setNickname(member.getmNickname());
			loginUserDetails.setName(member.getmName());
			loginUserDetails.setPoint(member.getmPoint());
			loginUserDetails.setRegdate(member.getmRegdate());
	
			return loginUserDetails;
		} else if (member == null && admin != null && admin.getAuthLevel().equals("ROLE_ADMIN")) {
			// 관리자일때
			InventoryDTO icon = (InventoryDTO) pointshopDAO.selectUsingAdminItemBymNum(admin.getAdNum());
			loginUserDetails = new LoginUserDetails(admin.getAdId(), admin.getAdPassword(),
					AuthorityUtils.createAuthorityList(admin.getAuthLevel())
					);
			loginUserDetails.setNum(admin.getAdNum());
			loginUserDetails.setNickname(admin.getAdNickname());
			loginUserDetails.setName("관리자");
			loginUserDetails.setPoint(admin.getAdPoint());
			loginUserDetails.setRegdate(admin.getAdRegdate());
			loginUserDetails.setProfileImgname(admin.getAdProfile());
			loginUserDetails.setUsingIcon(icon.getItemFilename());
			return loginUserDetails;
		} else {
			// 어디에도 없을때는 예외
			throw new UsernameNotFoundException("회원 혹은 관리자 존재하지 않음 : " + username);
		}
	}

}
