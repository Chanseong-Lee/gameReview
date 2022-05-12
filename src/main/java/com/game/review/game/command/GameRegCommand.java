package com.game.review.game.command;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

public class GameRegCommand {

	private String gCode;
	private String gName;
	private String gDev;
	private String gDate;
	private Long gPrice;
	private String gContent;
	private ArrayList<String> genNum;
	private MultipartFile imgFile;

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

	public String getgDate() {
		return gDate;
	}

	public void setgDate(String gDate) {
		this.gDate = gDate;
	}

	public Long getgPrice() {
		return gPrice;
	}

	public void setgPrice(Long gPrice) {
		this.gPrice = gPrice;
	}

	public String getgContent() {
		return gContent;
	}

	public void setgContent(String gContent) {
		this.gContent = gContent;
	}

	public ArrayList<String> getGenNum() {
		return genNum;
	}

	public void setGenNum(ArrayList<String> genNum) {
		this.genNum = genNum;
	}

	public MultipartFile getImgFile() {
		return imgFile;
	}

	public void setImgFile(MultipartFile imgFile) {
		this.imgFile = imgFile;
	}

	@Override
	public String toString() {
		return "GameRegCommand [gCode=" + gCode + ", gName=" + gName + ", gDev=" + gDev + ", gDate=" + gDate
				+ ", gPrice=" + gPrice + ", gContent=" + gContent + ", genNum=" + genNum + ", imgFile=" + imgFile + "]";
	}

}