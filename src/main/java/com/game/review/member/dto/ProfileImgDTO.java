package com.game.review.member.dto;

import org.apache.ibatis.type.Alias;

@Alias("profileImgDTO")
public class ProfileImgDTO {
	private Long profileNum;
	private String profileImgname;
	private Long mNum;
	public Long getProfileNum() {
		return profileNum;
	}
	public void setProfileNum(Long profileNum) {
		this.profileNum = profileNum;
	}
	public String getProfileImgname() {
		return profileImgname;
	}
	public void setProfileImgname(String profileImgname) {
		this.profileImgname = profileImgname;
	}
	public Long getmNum() {
		return mNum;
	}
	public void setmNum(Long mNum) {
		this.mNum = mNum;
	}
	@Override
	public String toString() {
		return "ProfileImgDTO [profileNum=" + profileNum + ", profileImgname=" + profileImgname + ", mNum=" + mNum
				+ "]";
	}
	
	
}
