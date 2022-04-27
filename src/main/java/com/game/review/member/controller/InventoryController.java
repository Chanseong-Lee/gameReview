package com.game.review.member.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.review.member.command.LoginUserDetails;
import com.game.review.member.command.SelectItemCommand;
import com.game.review.member.exception.NoValueException;
import com.game.review.member.exception.NowUsingItemException;
import com.game.review.member.service.InventoryService;
import com.game.review.pointshop.dto.InventoryDTO;

@Controller
public class InventoryController {
	
	private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);
	
	@Autowired
	private InventoryService inventoryService;
	
	@RequestMapping(value="member/inventory", method=RequestMethod.GET)
	public String inventory(@AuthenticationPrincipal LoginUserDetails loginUserDetails, Model model){
		
//		List<InventoryDTO> items = inventoryService.inventoryList(loginUserDetails);
//		model.addAttribute("items", items);
		return "member/inventory/inventory";
	}
	
	@RequestMapping(value="member/inventory/inventoryList", method=RequestMethod.GET)
	@ResponseBody
	public List<InventoryDTO> inventoryList(@AuthenticationPrincipal LoginUserDetails loginUserDetails) throws Exception{
		List<InventoryDTO> items = inventoryService.inventoryList(loginUserDetails);
		return items;
	}
	
	@RequestMapping(value="member/inventory/selectItem", method=RequestMethod.POST)
	@ResponseBody
	public String selectItem(@RequestBody SelectItemCommand selectItemCommand, @AuthenticationPrincipal LoginUserDetails loginUserDetails) throws Exception {
		logger.info(""+selectItemCommand.getItemNum());
		try {
			inventoryService.selectItem(selectItemCommand, loginUserDetails);
			return "1";
		}catch(NoValueException e) {
			return "2";
		}
	}
	
	@RequestMapping(value="member/inventory/deleteItem", method=RequestMethod.POST)
	@ResponseBody
	public String deleteItemInInvent(@RequestBody SelectItemCommand selectItemCommand, @AuthenticationPrincipal LoginUserDetails loginUserDetails) throws Exception {
		logger.info(""+selectItemCommand.getItemNum());
		try {
			inventoryService.deleteItem(selectItemCommand, loginUserDetails);
			return "1";
		}catch(NoValueException e) {
			return "2";
		}catch(NowUsingItemException e) {
			return "3";
		}
	}
}
