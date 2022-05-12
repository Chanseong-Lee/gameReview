package com.game.review.game.command;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class GameFileModifyCommand {
	
	private Long gNum;
	private List<Long> gfNum;
	private String gfFilename;
	private String gfSavedfilename;
	private String gName;
	private String gfLocation;
	private MultipartFile imgFile;
	private List<MultipartFile> slideImgFile;
	private String gCode;

	public Long getgNum() {
		return gNum;
	}
	public void setgNum(Long gNum) {
		this.gNum = gNum;
	}
	public List<Long> getGfNum() {
		return gfNum;
	}
	public void setGfNum(List<Long> gfNum) {
		this.gfNum = gfNum;
	}
	public String getGfFilename() {
		return gfFilename;
	}
	public void setGfFilename(String gfFilename) {
		this.gfFilename = gfFilename;
	}
	public String getGfSavedfilename() {
		return gfSavedfilename;
	}
	public void setGfSavedfilename(String gfSavedfilename) {
		this.gfSavedfilename = gfSavedfilename;
	}
	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public MultipartFile getImgFile() {
		return imgFile;
	}
	public void setImgFile(MultipartFile imgFile) {
		this.imgFile = imgFile;
	}
	public List<MultipartFile> getSlideImgFile() {
		return slideImgFile;
	}
	public void setSlideImgFile(List<MultipartFile> slideImgFile) {
		this.slideImgFile = slideImgFile;
	}
	public String getgCode() {
		return gCode;
	}
	public void setgCode(String gCode) {
		this.gCode = gCode;
	}
	
	public String getGfLocation() {
		return gfLocation;
	}
	public void setGfLocation(String gfLocation) {
		this.gfLocation = gfLocation;
	}
	@Override
	public String toString() {
		return "GameFileModifyCommand [gNum=" + gNum + ", gfNum=" + gfNum + ", gfFilename=" + gfFilename
				+ ", gfSavedfilename=" + gfSavedfilename + ", gName=" + gName + ", gfLocation=" + gfLocation
				+ ", imgFile=" + imgFile + ", slideImgFile=" + slideImgFile + ", gCode=" + gCode + "]";
	}
	
	
}
