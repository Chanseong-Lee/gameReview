package com.game.review.member.command;

public class AuthInfo {
	private Long mNum;
	private String email;
	private String name;
	private String password;
	private Long authLevel;
	
	public Long getmNum() {
		return mNum;
	}
	public void setmNum(Long mNum) {
		this.mNum = mNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getAuthLevel() {
		return authLevel;
	}
	public void setAuthLevel(Long authLevel) {
		this.authLevel = authLevel;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
