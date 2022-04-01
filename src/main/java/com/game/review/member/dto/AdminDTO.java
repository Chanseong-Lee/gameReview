package com.game.review.member.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;
@Alias("adminDTO")
public class AdminDTO {
	private Long adNum;
	private String adId;
	private String adPassword;
	private String adNickname;
	private Timestamp adRegdate;
	private String adKey;
	private Long adPoint;
	
	public Long getAdNum() {
		return adNum;
	}
	public void setAdNum(Long adNum) {
		this.adNum = adNum;
	}
	public String getAdId() {
		return adId;
	}
	public void setAdId(String adId) {
		this.adId = adId;
	}
	public String getAdPassword() {
		return adPassword;
	}
	public void setAdPassword(String adPassword) {
		this.adPassword = adPassword;
	}
	public String getAdNickname() {
		return adNickname;
	}
	public void setAdNickname(String adNickname) {
		this.adNickname = adNickname;
	}
	public Timestamp getAdRegdate() {
		return adRegdate;
	}
	public void setAdRegdate(Timestamp adRegdate) {
		this.adRegdate = adRegdate;
	}
	public String getAdKey() {
		return adKey;
	}
	public void setAdKey(String adKey) {
		this.adKey = adKey;
	}
	public Long getAdPoint() {
		return adPoint;
	}
	public void setAdPoint(Long adPoint) {
		this.adPoint = adPoint;
	}
	
	
}
