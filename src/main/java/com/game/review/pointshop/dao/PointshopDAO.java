package com.game.review.pointshop.dao;

import java.util.List;

import com.game.review.pointshop.dto.ItemsDTO;

public interface PointshopDAO {
	
	//관리자가 아이콘 등록
	public int insertIconByAdmin(ItemsDTO itemsDTO);
	
	//관리자페이지 아이콘 목록 출력
	public List<ItemsDTO> selectItems();
	
	//아이템 등록할때 이름 중복확인
	public int countByItemName(String itemName);
	
	//디테일
	public Object selectItemsBySeq(Long itemNum);
	
	//아이템 수정
	public int updateItemNamePrice(ItemsDTO itemsDTO);
	public int updateItemFilename(ItemsDTO itemsDTO);
}
