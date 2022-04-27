package com.game.review.pointshop.service;

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
import com.game.review.member.dto.MemberDTO;
import com.game.review.member.exception.FailToInsertException;
import com.game.review.member.exception.NoValueException;
import com.game.review.pointshop.dao.PointshopDAO;
import com.game.review.pointshop.dto.InventoryDTO;
import com.game.review.pointshop.dto.ItemsDTO;
import com.game.review.pointshop.dto.ShopDTO;

@Service
public class PointShopService {
	private static final Logger logger = LoggerFactory.getLogger(PointShopService.class);

	@Autowired
	private PointshopDAO pointshopDAO;
	
	//상점목록
	public List<ShopDTO> itemList(LoginUserDetails loginUserDetails){
		List<ShopDTO> items = null;
		Collection<GrantedAuthority> auth = loginUserDetails.getAuthorities();
		Iterator<GrantedAuthority> it = auth.iterator();
		String authlevel = null;
		while(it.hasNext()) {
			authlevel = it.next().getAuthority();
		}
		logger.info(authlevel);
		if(authlevel.equals("ROLE_ADMIN")) {
			//관리자일경우
			items = pointshopDAO.selectAdminItemsForShop(loginUserDetails.getNum());
		}else if(authlevel.equals("ROLE_USER")) {
			//일반멤버
			items = pointshopDAO.selectItemsForShop(loginUserDetails.getNum());
		}
		return items;
	}
	
	@Transactional
	public void purchase(Long itemNum, LoginUserDetails loginUserDetails) {
		
		//권한확인
		Collection<GrantedAuthority> auth = loginUserDetails.getAuthorities();
		Iterator<GrantedAuthority> it = auth.iterator();
		String authlevel = null;
		while(it.hasNext()) {
			authlevel = it.next().getAuthority();
		}
		logger.info(authlevel);
		if(authlevel.equals("ROLE_ADMIN")) {
			//관리자
			//인벤토리에 아이템 추가
			InventoryDTO inven = new InventoryDTO();
			inven.setItemNum(itemNum);
			inven.setAdNum(loginUserDetails.getNum());
			
			int res = pointshopDAO.insertAdminInventory(inven);
			if(res != 1) {
				throw new FailToInsertException();
			}
			
			//관리자는 포인트 차감 안함
			
		}else if(authlevel.equals("ROLE_USER")){
			//일반멤버
			//인벤토리에 아이템 추가
			InventoryDTO inven = new InventoryDTO();
			inven.setItemNum(itemNum);
			inven.setmNum(loginUserDetails.getNum());
			
			int res = pointshopDAO.insertInventory(inven);
			if(res != 1) {
				throw new FailToInsertException();
			}
			
			//아이템 가격 불러오기
			ItemsDTO item = (ItemsDTO) pointshopDAO.selectItemsBySeq(itemNum);
			if(item == null) {
				throw new NoValueException();
			}
			Long price = item.getItemPrice();
			
			//구매후 포인트 차감
			Long memberPoint = loginUserDetails.getPoint();
			Long pointAfterPurchase = memberPoint - price;
			logger.info("보유포인트 - 가격 : " + memberPoint +" - " + price + " = "+pointAfterPurchase);
			MemberDTO member = new MemberDTO();
			member.setmNum(loginUserDetails.getNum());
			member.setmPoint(pointAfterPurchase);
			pointshopDAO.updateMemberPoint(member);
			
			//세션 포인트 수정
			loginUserDetails.setPoint(pointAfterPurchase);
		}
		
		
	}
	
}
