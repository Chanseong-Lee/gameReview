package com.game.review.pointshop.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

@Alias("shopDTO")
public class ShopDTO {
	private Long itemNum;
	private String itemName;
	private String itemFilename;
	private Long itemPrice;
	private Timestamp itemRegdate;
	private String isSold;
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
	public String getIsSold() {
		return isSold;
	}
	public void setIsSold(String isSold) {
		this.isSold = isSold;
	}
	@Override
	public String toString() {
		return "ShopDTO [itemNum=" + itemNum + ", itemName=" + itemName + ", itemFilename=" + itemFilename
				+ ", itemPrice=" + itemPrice + ", itemRegdate=" + itemRegdate + ", isSold=" + isSold + "]";
	}
	
	
}
