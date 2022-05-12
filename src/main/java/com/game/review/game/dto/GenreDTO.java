package com.game.review.game.dto;

import org.apache.ibatis.type.Alias;

@Alias("genreDTO")
public class GenreDTO {
	private Long grNum;//시퀀스
	private Long genNum;//장르시퀀스
	private Long gNum;//게임시퀀스
	private String gName;//게임이름
	private String genName;//장르이름
	

	public String getGenName() {
		return genName;
	}

	public void setGenName(String genName) {
		this.genName = genName;
	}

	public Long getgNum() {
		return gNum;
	}

	public void setgNum(Long gNum) {
		this.gNum = gNum;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public Long getGrNum() {
		return grNum;
	}

	public void setGrNum(Long grNum) {
		this.grNum = grNum;
	}

	public Long getGenNum() {
		return genNum;
	}

	public void setGenNum(Long genNum) {
		this.genNum = genNum;
	}

	@Override
	public String toString() {
		return "GenreDTO [grNum=" + grNum + ", genNum=" + genNum + ", gName=" + gName + ", gNum=" + gNum + "]";
	}

}
