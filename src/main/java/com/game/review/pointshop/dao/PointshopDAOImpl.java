package com.game.review.pointshop.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.review.pointshop.dto.ItemsDTO;

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
	
}
