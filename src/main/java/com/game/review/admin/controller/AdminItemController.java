package com.game.review.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.game.review.admin.command.AddItemCommand;
import com.game.review.admin.service.AdminItemService;
import com.game.review.admin.validate.AddItemCommandValidator;
import com.game.review.member.exception.AlreadyExistItemnameException;
import com.game.review.member.exception.NoImageException;
import com.game.review.member.exception.NoValueException;
import com.game.review.pointshop.dto.ItemsDTO;

@Controller
public class AdminItemController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminItemController.class);
	
	@Autowired
	private AdminItemService adminItemService;
	
	//목록
	@RequestMapping(value="/admin/item/itemList", method=RequestMethod.GET)
	public String goItemList(Model model) {
		List<ItemsDTO> items = adminItemService.itemList();
		model.addAttribute("items", items);
		return "admin/item/itemList";
	}
	
	//아이템 추가 폼
	@RequestMapping(value="/admin/item/addItem", method=RequestMethod.GET)
	public String addItemForm(@ModelAttribute("addItemCommand") AddItemCommand addItemCommand) {
		
		return "admin/item/addItemForm";
	}
	
	//아이템 추가
	@RequestMapping(value="/admin/item/addItem", method=RequestMethod.POST)
	public String addItem(
			@ModelAttribute("addItemCommand") 
			AddItemCommand addItemCommand,
			BindingResult errors
			) {
		logger.info(addItemCommand.toString());
		new AddItemCommandValidator().validate(addItemCommand, errors);
		if(errors.hasErrors()) {
			return "admin/item/addItemForm";
		}
		try {
			adminItemService.addItem(addItemCommand);
			
			return "redirect:/admin/item/itemList";
			
		}catch(NoValueException e) {
			logger.error("파일업로드 안함!");
			errors.rejectValue("itemImg", "required");
			return "admin/item/addItemForm";
		}catch(NoImageException e) {
			logger.error("이미지파일이 아님!");
			errors.rejectValue("itemImg", "noImage");
			return "admin/item/addItemForm";
		}catch(AlreadyExistItemnameException e) {
			logger.error("아이콘 이름 중복!");
			errors.rejectValue("itemName", "dupItemname");
			return "admin/item/addItemForm";
		}
	}
	
	//디테일
	@RequestMapping(value="/admin/item/detail/{num}")
	public String detail(@PathVariable Long num, Model model) {
		
		ItemsDTO item = adminItemService.detail(num);
		model.addAttribute("item", item);
		return "admin/item/itemDetail";
	}
	
	//아이템 수정 폼
	@RequestMapping(value="/admin/item/itemUpdate/{num}", method=RequestMethod.GET)
	public String ItemUpdateForm(@PathVariable Long num, @ModelAttribute("addItemCommand") AddItemCommand addItemCommand, Model model) {
		ItemsDTO item = adminItemService.detail(num);
		model.addAttribute("item", item);
		return "admin/item/itemUpdateForm";
	}
	
	
	//아이템 수정
	@RequestMapping(value="/admin/item/itemUpdate/{num}", method=RequestMethod.POST)
	public String ItemUpdate(@PathVariable Long num, @ModelAttribute("addItemCommand") AddItemCommand addItemCommand, BindingResult errors, Model model) {
		logger.info(addItemCommand.toString());
		try {
			ItemsDTO item = adminItemService.detail(num);
			model.addAttribute("item", item);
		
			new AddItemCommandValidator().validate(addItemCommand, errors);
			if(errors.hasErrors()) {
				return "admin/item/itemUpdateForm";
			}
			
			adminItemService.itemUpdate(num, addItemCommand);
			return "redirect:/admin/item/detail/"+num;
			
		}catch(NoImageException e) {
			logger.error("이미지파일이 아님!");
			errors.rejectValue("itemImg", "noImage");
			return "admin/item/itemUpdateForm";
		}catch(AlreadyExistItemnameException e) {
			logger.error("아이콘 이름 중복!");
			errors.rejectValue("itemName", "dupItemname");
			return "admin/item/itemUpdateForm";
		}
		
	}
	
	
}
