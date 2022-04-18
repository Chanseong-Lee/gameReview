package com.game.review.member.command;

import java.sql.Timestamp;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
//시큐리티가 /member/login 주소 요청을 낚아채서 로그인을 진행시킨다.
//로그인 진행이 완료가 되면 session을 만들어줍니다.
//(우리가 아는 세션이지만 시큐리티만의 세션의 공간을 갖는다.SecurityContextHolder이라는 키값에 세션정보가 저장됨)
//시큐리티가 갖고있는 세션에 저장될 Object 타입=> Authentication타입 객체
//Authentication안에  User정보가 저장되야함.
//User Object타입 => UserDetails타입 객체
//Security Session => Authentication => UserDetails
public class LoginUserDetails extends User {

	private Long num;
	private String nickname;
	private String name;
	private Long point;
	private Timestamp regdate;
	private String profileImgname;
	
	public LoginUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public LoginUserDetails(String username, String password, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, true, true, true, authorities);
	}
	
	@Override
	public String getUsername() {
		return super.getUsername();
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}
	
	
	public Long getNum() {
		return num;
	}


	public void setNum(Long num) {
		this.num = num;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Long getPoint() {
		return point;
	}


	public void setPoint(Long point) {
		this.point = point;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	public String getProfileImgname() {
		return profileImgname;
	}

	public void setProfileImgname(String profileImgname) {
		this.profileImgname = profileImgname;
	}


}
