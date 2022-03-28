package com.game.review.dto;

import java.sql.Timestamp;

public class MemberDTO {
	private Long mNum;
	private String mEmail;
	private String mPassword;
	private String mNickname;
	private Timestamp mRegdate;
	private String mIsvalid;
	private Long mPoint;
	
	public Long getmNum() {
		return mNum;
	}
	public void setmNum(Long mNum) {
		this.mNum = mNum;
	}
	public String getmEmail() {
		return mEmail;
	}
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	public String getmPassword() {
		return mPassword;
	}
	public void setmPassword(String mPassword) {
		this.mPassword = mPassword;
	}
	public String getmNickname() {
		return mNickname;
	}
	public void setmNickname(String mNickname) {
		this.mNickname = mNickname;
	}
	public Timestamp getmRegdate() {
		return mRegdate;
	}
	public void setmRegdate(Timestamp mRegdate) {
		this.mRegdate = mRegdate;
	}
	public String getmIsvalid() {
		return mIsvalid;
	}
	public void setmIsvalid(String mIsvalid) {
		this.mIsvalid = mIsvalid;
	}
	public Long getmPoint() {
		return mPoint;
	}
	public void setmPoint(Long mPoint) {
		this.mPoint = mPoint;
	}
	
	
}
