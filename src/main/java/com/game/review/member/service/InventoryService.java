package com.game.review.member.service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.review.member.command.LoginUserDetails;
import com.game.review.member.command.SelectItemCommand;
import com.game.review.member.exception.NoValueException;
import com.game.review.member.exception.NowUsingItemException;
import com.game.review.pointshop.dao.PointshopDAO;
import com.game.review.pointshop.dto.InventoryDTO;
import com.game.review.pointshop.service.PointShopService;

@Service
public class InventoryService {
	private static final Logger logger = LoggerFactory.getLogger(InventoryService.class);

	@Autowired
	private PointshopDAO pointshopDAO;
	
	
	//인벤템 목록
	public List<InventoryDTO> inventoryList(LoginUserDetails loginUserDetails) {
		
		List<InventoryDTO> items = null;
		//권한확인
		Collection<GrantedAuthority> auth = loginUserDetails.getAuthorities();
		Iterator<GrantedAuthority> it = auth.iterator();
		String authlevel = null;
		
		while(it.hasNext()) {
			authlevel = it.next().getAuthority();
		}
		
		logger.info(authlevel);
		
		if(authlevel.equals("ROLE_ADMIN")) {
			//관리자일때
			items = pointshopDAO.selectAdminInventoryBySeq(loginUserDetails.getNum());
		}else if(authlevel.equals("ROLE_USER")) {
			//일반회원일때
			items = pointshopDAO.selectInventoryBySeq(loginUserDetails.getNum());
		}
		
		return items;
	}
	
	//아이템 선택
	@Transactional
	public void selectItem(SelectItemCommand selectItemCommand, LoginUserDetails loginUserDetails) {
		
		//권한확인
		Collection<GrantedAuthority> auth = loginUserDetails.getAuthorities();
		Iterator<GrantedAuthority> it = auth.iterator();
		String authlevel = null;
		while(it.hasNext()) {
			authlevel = it.next().getAuthority();
		}
		logger.info(authlevel);
		
		InventoryDTO inven = new InventoryDTO();
		
		if(authlevel.equals("ROLE_ADMIN")) {
			//관리자일때
			inven.setAdNum(loginUserDetails.getNum());
			inven.setItemNum(selectItemCommand.getItemNum());
			int resNo = pointshopDAO.updateAdminInvenUseToNo(inven);
			if(resNo!=1) {
				throw new NoValueException();
			}
			int resYes = pointshopDAO.updateAdminInvenUseToYes(inven);
			if(resYes!=1) {
				throw new NoValueException();
			}
			InventoryDTO db = (InventoryDTO) pointshopDAO.selectUsingAdminItemBymNum(loginUserDetails.getNum());
			//세션값도 수정
			loginUserDetails.setUsingIcon(db.getItemFilename());
			
		}else if(authlevel.equals("ROLE_USER")) {
			//일반회원일때
			inven.setmNum(loginUserDetails.getNum());
			inven.setItemNum(selectItemCommand.getItemNum());
			int resNo = pointshopDAO.updateInvenUseToNo(inven);
			if(resNo!=1) {
				throw new NoValueException();
			}
			int resYes = pointshopDAO.updateInvenUseToYes(inven);
			if(resYes!=1) {
				throw new NoValueException();
			}
			InventoryDTO db = (InventoryDTO) pointshopDAO.selectUsingItemBymNum(loginUserDetails.getNum());
			//세션값도 수정
			loginUserDetails.setUsingIcon(db.getItemFilename());
		}
	}
	
	@Transactional
	public void deleteItem(SelectItemCommand selectItemCommand, LoginUserDetails loginUserDetails) {
		
		//권한확인
		Collection<GrantedAuthority> auth = loginUserDetails.getAuthorities();
		Iterator<GrantedAuthority> it = auth.iterator();
		String authlevel = null;
		while(it.hasNext()) {
			authlevel = it.next().getAuthority();
		}
		logger.info(authlevel);
		if(authlevel.equals("ROLE_ADMIN")) {
			//관리자일때
			
			//지우려는 아이템이 사용중이면 예외발생
			InventoryDTO usingInven = (InventoryDTO) pointshopDAO.selectUsingAdminItemBymNum(loginUserDetails.getNum());
			if(usingInven.getInvenUse().equals("Y")) {
				if(usingInven.getItemNum() == selectItemCommand.getItemNum()) {
					throw new NowUsingItemException();
				}
			}
			InventoryDTO inven = new InventoryDTO();
			inven.setAdNum(loginUserDetails.getNum());
			inven.setItemNum(selectItemCommand.getItemNum());
			int res = pointshopDAO.deleteAdminItemInInven(inven);
			if(res != 1) {
				throw new NoValueException();
			}
			
		}else if(authlevel.equals("ROLE_USER")) {
			//일반 회원일때 
			
			//지우려는 아이템이 사용중이면 예외발생
			InventoryDTO usingInven = (InventoryDTO) pointshopDAO.selectUsingItemBymNum(loginUserDetails.getNum());
			if(usingInven.getInvenUse().equals("Y")) {
				if(usingInven.getItemNum() == selectItemCommand.getItemNum()) {
					throw new NowUsingItemException();
				}
			}
			
			InventoryDTO inven = new InventoryDTO();
			inven.setmNum(loginUserDetails.getNum());
			inven.setItemNum(selectItemCommand.getItemNum());
			
			int res = pointshopDAO.deleteItemInInven(inven);
			if(res != 1) {
				throw new NoValueException();
			}
		}

	}
		
}
