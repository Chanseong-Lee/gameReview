package com.game.review.game.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

@Alias("gamesDTO")
public class GamesDTO {
	private Long gNum;
	private String gCode;
	private String gName;
	private String gDev;
	private Timestamp gDate;
	private Long gPrice;
	private Long gAvg;
	private String gContent;
	private Long adNum;
	private Long rank;
	
	public Long getgNum() {
		return gNum;
	}
	public void setgNum(Long gNum) {
		this.gNum = gNum;
	}
	public String getgCode() {
		return gCode;
	}
	public void setgCode(String gCode) {
		this.gCode = gCode;
	}
	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public String getgDev() {
		return gDev;
	}
	public void setgDev(String gDev) {
		this.gDev = gDev;
	}
	public Timestamp getgDate() {
		return gDate;
	}
	public void setgDate(Timestamp gDate) {
		this.gDate = gDate;
	}
	public Long getgPrice() {
		return gPrice;
	}
	public void setgPrice(Long gPrice) {
		this.gPrice = gPrice;
	}
	public Long getgAvg() {
		return gAvg;
	}
	public void setgAvg(Long gAvg) {
		this.gAvg = gAvg;
	}
	public String getgContent() {
		return gContent;
	}
	public void setgContent(String gContent) {
		this.gContent = gContent;
	}
	public Long getAdNum() {
		return adNum;
	}

	public void setAdNum(Long adNum) {
		this.adNum = adNum;
	}

	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}
	@Override
	public String toString() {
		return "GamesDTO [gNum=" + gNum + ", gCode=" + gCode + ", gName=" + gName + ", gDev=" + gDev + ", gDate="
				+ gDate + ", gPrice=" + gPrice + ", gAvg=" + gAvg + ", gContent=" + gContent + ", adNum=" + adNum
				+ ", rank=" + rank + "]";
	}



}
