package com.game.review.member.command;

import org.springframework.web.multipart.MultipartFile;

public class MemberUpdateCommand {

	private MultipartFile profileImg;
	private String nickname;
	private boolean backTobasicImg;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public MultipartFile getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(MultipartFile profileImg) {
		this.profileImg = profileImg;
	}

	public boolean isBackTobasicImg() {
		return backTobasicImg;
	}

	public void setBackTobasicImg(boolean backTobasicImg) {
		this.backTobasicImg = backTobasicImg;
	}

	@Override
	public String toString() {
		return "MemberUpdateCommand [profileImg=" + profileImg + ", nickname=" + nickname + ", backTobasicImg="
				+ backTobasicImg + "]";
	}
	
	

}
