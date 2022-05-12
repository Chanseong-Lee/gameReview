package com.game.review.game.dto;

import org.apache.ibatis.type.Alias;

@Alias("gameFilesDTO")
public class GameFilesDTO {

	private Long gfNum;
	private String gfFilename;
	private String gfSavedfilename;
	private String gName;
	private String gfLocation;
	private Long gNum;

	public Long getgNum() {
		return gNum;
	}

	public void setgNum(Long gNum) {
		this.gNum = gNum;
	}
	

	public String getGfLocation() {
		return gfLocation;
	}

	public void setGfLocation(String gfLocation) {
		this.gfLocation = gfLocation;
	}

	public Long getGfNum() {
		return gfNum;
	}

	public void setGfNum(Long gfNum) {
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

	@Override
	public String toString() {
		return "GameFilesDTO [gfNum=" + gfNum + ", gfFilename=" + gfFilename + ", gfSavedfilename=" + gfSavedfilename
				+ ", gName=" + gName + ", gfLocation=" + gfLocation + ", gNum=" + gNum + "]";
	}

}