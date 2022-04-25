package com.game.review.pointshop.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

@Alias("inventoryDTO")
public class InventoryDTO {
	private Long invenNum;
	private Long mNum;
	private String invenUse;
	private Long itemNum;
	private String itemName;
	private String itemFilename;
	private Long itemPrice;
	private Timestamp itemRegdate;
	public Long getInvenNum() {
		return invenNum;
	}
	public void setInvenNum(Long invenNum) {
		this.invenNum = invenNum;
	}
	public Long getmNum() {
		return mNum;
	}
	public void setmNum(Long mNum) {
		this.mNum = mNum;
	}
	public String getInvenUse() {
		return invenUse;
	}
	public void setInvenUse(String invenUse) {
		this.invenUse = invenUse;
	}
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
		return "InventoryDTO [invenNum=" + invenNum + ", mNum=" + mNum + ", invenUse=" + invenUse + ", itemNum="
				+ itemNum + ", itemName=" + itemName + ", itemFilename=" + itemFilename + ", itemPrice=" + itemPrice
				+ ", itemRegdate=" + itemRegdate + "]";
	}
	
	
	
	
}
