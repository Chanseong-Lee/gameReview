package com.game.review.pointshop.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.review.member.dto.MemberDTO;
import com.game.review.pointshop.dto.InventoryDTO;
import com.game.review.pointshop.dto.ItemsDTO;
import com.game.review.pointshop.dto.ShopDTO;

@Repository
public class PointshopDAOImpl implements PointshopDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int insertIconByAdmin(ItemsDTO itemsDTO) {
		return sqlSessionTemplate.insert("insertIconByAdmin", itemsDTO);
	}

	@Override
	public List<ItemsDTO> selectItems() {
		return sqlSessionTemplate.selectList("selectItems");
	}

	@Override
	public int countByItemName(String itemName) {
		return sqlSessionTemplate.selectOne("countByItemName", itemName);
	}

	@Override
	public Object selectItemsBySeq(Long itemNum) {
		return sqlSessionTemplate.selectOne("selectItemsBySeq", itemNum);
	}

	@Override
	public int updateItemNamePrice(ItemsDTO itemsDTO) {
		return sqlSessionTemplate.update("updateItemNamePrice", itemsDTO);
	}

	@Override
	public int updateItemFilename(ItemsDTO itemsDTO) {
		return sqlSessionTemplate.update("updateItemFilename", itemsDTO);
	}

	@Override
	public int deleteItemBySeq(Long itemNum) {
		return sqlSessionTemplate.delete("deleteItemBySeq", itemNum);
	}

	@Override
	public List<ShopDTO> selectItemsForShop(Long mNum) {
		return sqlSessionTemplate.selectList("selectItemsForShop", mNum);
	}

	@Override
	public List<ShopDTO> selectAdminItemsForShop(Long adNum) {
		return sqlSessionTemplate.selectList("selectAdminItemsForShop", adNum);
	}
	
	@Override
	public int insertInventory(InventoryDTO inventoryDTO) {
		return sqlSessionTemplate.insert("insertInventory", inventoryDTO);
	}
	
	@Override
	public int insertAdminInventory(InventoryDTO inventoryDTO) {
		return sqlSessionTemplate.insert("insertAdminInventory", inventoryDTO);
	}

	@Override
	public int updateMemberPoint(MemberDTO memberDTO) {
		return sqlSessionTemplate.update("updateMemberPoint", memberDTO);
	}

	@Override
	public List<InventoryDTO> selectInventoryBySeq(Long mNum) {
		return sqlSessionTemplate.selectList("selectInventoryBySeq", mNum);
	}
	
	@Override
	public List<InventoryDTO> selectAdminInventoryBySeq(Long adNum) {
		return sqlSessionTemplate.selectList("selectAdminInventoryBySeq", adNum);
	}

	@Override
	public int updateInvenUseToNo(InventoryDTO inventoryDTO) {
		return sqlSessionTemplate.update("updateInvenUseToNo", inventoryDTO);
	}

	@Override
	public int updateInvenUseToYes(InventoryDTO inventoryDTO) {
		return sqlSessionTemplate.update("updateInvenUseToYes", inventoryDTO);
	}

	@Override
	public int updateAdminInvenUseToNo(InventoryDTO inventoryDTO) {
		return sqlSessionTemplate.update("updateAdminInvenUseToNo", inventoryDTO);
	}

	@Override
	public int updateAdminInvenUseToYes(InventoryDTO inventoryDTO) {
		return sqlSessionTemplate.update("updateAdminInvenUseToYes", inventoryDTO);
	}
	
	@Override
	public int deleteItemInInven(InventoryDTO inventoryDTO) {
		return sqlSessionTemplate.delete("deleteItemInInven", inventoryDTO);
	}

	@Override
	public int deleteAdminItemInInven(InventoryDTO inventoryDTO) {
		return sqlSessionTemplate.delete("deleteAdminItemInInven", inventoryDTO);
	}
	
	@Override
	public Object selectUsingItemBymNum(Long mNum) {
		return sqlSessionTemplate.selectOne("selectUsingItemBymNum", mNum);
	}


	@Override
	public Object selectUsingAdminItemBymNum(Long adNum) {
		return sqlSessionTemplate.selectOne("selectUsingAdminItemBymNum", adNum);
	}



	

	
}
