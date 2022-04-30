package com.game.review.pointshop.dao;

import java.util.List;

import com.game.review.member.dto.MemberDTO;
import com.game.review.pointshop.dto.InventoryDTO;
import com.game.review.pointshop.dto.ItemsDTO;
import com.game.review.pointshop.dto.ShopDTO;

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
	
	//아이템삭제
	public int deleteItemBySeq(Long itemNum);
	
	//상점용 리스트
	public List<ShopDTO> selectItemsForShop(Long mNum);
	
	//관리자용 상점리스트
	public List<ShopDTO> selectAdminItemsForShop(Long adNum);
	
	//아이템구매
	public int insertInventory(InventoryDTO inventoryDTO);
	
	//관리자 아이템 구매
	public int insertAdminInventory(InventoryDTO inventoryDTO);
	
	//구매후 포인트 차감
	public int updateMemberPoint(MemberDTO memberDTO);
	
	//인벤토리 리스트
	public List<InventoryDTO> selectInventoryBySeq(Long mNum);
	//관리자 인벤토리 리스트
	public List<InventoryDTO> selectAdminInventoryBySeq(Long adNum);
	
	//아이템 선택
	public int updateInvenUseToNo(InventoryDTO inventoryDTO);
	public int updateInvenUseToYes(InventoryDTO inventoryDTO);
	
	//관리자 아이템 선택
	public int updateAdminInvenUseToNo(InventoryDTO inventoryDTO);
	public int updateAdminInvenUseToYes(InventoryDTO inventoryDTO);
	
	
	// 인벤 아이템 삭제
	public int deleteItemInInven(InventoryDTO inventoryDTO);
	//관리자 인벤 아이템 삭제
	public int deleteAdminItemInInven(InventoryDTO inventoryDTO);
	
	//사용중인 아이템 출력
	public Object selectUsingItemBymNum(Long mNum);
	
	//사용중인 아이템 출력
	public Object selectUsingAdminItemBymNum(Long adNum);
	
	public int countAllItem();
	
}
