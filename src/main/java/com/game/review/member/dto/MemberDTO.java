package com.game.review.member.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;
@Alias("MemberDTO")
public class MemberDTO {
	private Long mNum;
	private String mEmail;
	private String mPassword;
	private String mName;
	private String mNickname;
	private Timestamp mRegdate;
	private String mIsvalid;
	private Long mPoint;
	private Long authNum;
	
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
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
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
	public Long getAuthNum() {
		return authNum;
	}
	public void setAuthNum(Long authNum) {
		this.authNum = authNum;
	}
	
	
	
}
