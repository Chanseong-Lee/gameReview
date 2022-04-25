package com.game.review.admin.command;

import org.springframework.web.multipart.MultipartFile;

public class AddItemCommand {
	private String itemName;
	private MultipartFile itemImg;
	private Long itemPrice;
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public MultipartFile getItemImg() {
		return itemImg;
	}
	public void setItemImg(MultipartFile itemImg) {
		this.itemImg = itemImg;
	}
	public Long getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(Long itemPrice) {
		this.itemPrice = itemPrice;
	}
	@Override
	public String toString() {
		return "AddItemCommand [itemName=" + itemName + ", itemImg=" + itemImg + ", itemPrice=" + itemPrice + "]";
	}
}
