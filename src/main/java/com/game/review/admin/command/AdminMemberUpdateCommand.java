package com.game.review.admin.command;

import org.springframework.web.multipart.MultipartFile;

public class AdminMemberUpdateCommand {
	private String name;
	private String nickname;
	private Long point;
	MultipartFile profileImg;
	private boolean backToBasicImg;
	
	public MultipartFile getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(MultipartFile profileImg) {
		this.profileImg = profileImg;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Long getPoint() {
		return point;
	}
	public void setPoint(Long point) {
		this.point = point;
	}
	
	public boolean isBackToBasicImg() {
		return backToBasicImg;
	}
	public void setBackToBasicImg(boolean backToBasicImg) {
		this.backToBasicImg = backToBasicImg;
	}
	@Override
	public String toString() {
		return "AdminMemberUpdateCommand [name=" + name + ", nickname=" + nickname + ", point=" + point
				+ ", profileImg=" + profileImg + ", backToBasicImg=" + backToBasicImg + "]";
	}
	
}
