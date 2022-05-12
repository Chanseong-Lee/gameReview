package com.game.review.game.command;

import java.util.ArrayList;

public class AddGenreCommnad {
	private String genName;
	private ArrayList<String> genNum;

	public void setGenNum(ArrayList<String> genNum) {
		this.genNum = genNum;
	}

	public ArrayList<String> getGenNum() {
		return genNum;
	}

	public String getGenName() {
		return genName;
	}

	public void setGenName(String genName) {
		this.genName = genName;
	}

}
