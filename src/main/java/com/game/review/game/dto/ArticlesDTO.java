package com.game.review.game.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

@Alias("ArticlesDTO")
public class ArticlesDTO {

	  private Long aNum ;
	  private String aContent ;
	  private Long aScore ;
	  private Timestamp aRegdate ;
	  private Long aLike ;
	  private Long mNum ;
	  private String mNickname;
	  private Long gNum ;
	  private String itemName;
	  private String itemFilename;
	 
	public String getmNickname() {
		return mNickname;
	}
	public void setmNickname(String mNickname) {
		this.mNickname = mNickname;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Long getaNum() {
		return aNum;
	}
	public void setaNum(Long aNum) {
		this.aNum = aNum;
	}
	public String getaContent() {
		return aContent;
	}
	public void setaContent(String aContent) {
		this.aContent = aContent;
	}
	public Long getaScore() {
		return aScore;
	}
	public void setaScore(Long aScore) {
		this.aScore = aScore;
	}
	public Timestamp getaRegdate() {
		return aRegdate;
	}
	public void setaRegdate(Timestamp aRegdate) {
		this.aRegdate = aRegdate;
	}
	public Long getaLike() {
		return aLike;
	}
	public void setaLike(Long aLike) {
		this.aLike = aLike;
	}
	public Long getmNum() {
		return mNum;
	}
	public void setmNum(Long mNum) {
		this.mNum = mNum;
	}
	public Long getgNum() {
		return gNum;
	}
	public void setgNum(Long gNum) {
		this.gNum = gNum;
	}
	@Override
	public String toString() {
		return "ArticlesDTO [aNum=" + aNum + ", aContent=" + aContent + ", aScore=" + aScore + ", aRegdate=" + aRegdate
				+ ", aLike=" + aLike + ", mNum=" + mNum + ", mNickname=" + mNickname + ", gNum=" + gNum + ", itemName="
				+ itemName + "]";
	}
	public String getItemFilename() {
		return itemFilename;
	}
	public void setItemFilename(String itemFilename) {
		this.itemFilename = itemFilename;
	}
	
}
