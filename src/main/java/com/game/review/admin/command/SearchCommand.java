package com.game.review.admin.command;

public class SearchCommand {
	private String searchType;
	private String searchValue;
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	@Override
	public String toString() {
		return "SearchCommand [searchType=" + searchType + ", searchValue=" + searchValue + "]";
	}
	
	
}
