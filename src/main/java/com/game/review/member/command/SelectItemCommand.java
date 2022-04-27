package com.game.review.member.command;

public class SelectItemCommand {
	private Long itemNum;

	public Long getItemNum() {
		return itemNum;
	}

	public void setItemNum(Long itemNum) {
		this.itemNum = itemNum;
	}

	@Override
	public String toString() {
		return "SelectItemCommand [itemNum=" + itemNum + "]";
	}
	
}
