package com.game.review.pointshop.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.game.review.member.command.LoginUserDetails;
import com.game.review.member.exception.FailToInsertException;
import com.game.review.pointshop.dto.ShopDTO;
import com.game.review.pointshop.service.PointShopService;

@Controller
public class PointShopController {
	
	private static final Logger logger = LoggerFactory.getLogger(PointShopController.class);
	
	@Autowired
	private PointShopService pointShopService;
	
	//상점 목록
	@RequestMapping(value="/shop", method=RequestMethod.GET)
	public String goShop(@AuthenticationPrincipal LoginUserDetails loginUserDetails, Model model) {
		logger.info(loginUserDetails.toString());
		
		List<ShopDTO> items = pointShopService.itemList(loginUserDetails);
		
		model.addAttribute("items", items);
		
		return "shop/shopMain";
	}
	
	//구매하기
	@RequestMapping(value="/shop/purchase/{itemNum}", method=RequestMethod.GET)
	public String purchase(@PathVariable Long itemNum, @AuthenticationPrincipal LoginUserDetails loginUserDetails) {
		
		//서비스에서 구매하기로직
		try {
			
			pointShopService.purchase(itemNum, loginUserDetails);
			
			return "shop/purchaseSuccess";
		}catch(FailToInsertException e) {
			logger.error("구매실패");
			return "exceptions/failToInsertEx";
		}
		
	}
}
