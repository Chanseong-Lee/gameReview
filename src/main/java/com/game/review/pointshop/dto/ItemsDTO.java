package com.game.review.pointshop.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

@Alias("itemsDTO")
public class ItemsDTO {
	private Long itemNum;
	private String itemName;
	private String itemFilename;
	private Long itemPrice;
	private Timestamp itemRegdate;
	
	public Long getItemNum() {
		return itemNum;
	}
	public void setItemNum(Long itemNum) {
		this.itemNum = itemNum;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemFilename() {
		return itemFilename;
	}
	public void setItemFilename(String itemFilename) {
		this.itemFilename = itemFilename;
	}
	public Long getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(Long itemPrice) {
		this.itemPrice = itemPrice;
	}
	public Timestamp getItemRegdate() {
		return itemRegdate;
	}
	public void setItemRegdate(Timestamp itemRegdate) {
		this.itemRegdate = itemRegdate;
	}
	@Override
	public String toString() {
		return "ItemsDTO [itemNum=" + itemNum + ", itemName=" + itemName + ", itemFilename=" + itemFilename
				+ ", itemPrice=" + itemPrice + ", itemRegdate=" + itemRegdate + "]";
	}
	
}
