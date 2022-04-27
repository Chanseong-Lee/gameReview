package com.game.review.admin.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.game.review.admin.command.AddItemCommand;
import com.game.review.member.exception.AlreadyExistItemnameException;
import com.game.review.member.exception.NoImageException;
import com.game.review.member.exception.NoValueException;
import com.game.review.member.validate.FileTypeByTika;
import com.game.review.pointshop.dao.PointshopDAO;
import com.game.review.pointshop.dto.ItemsDTO;

@Service
public class AdminItemService {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminItemService.class);

	@Autowired
	private PointshopDAO pointshopDAO;
	
	public List<ItemsDTO> itemList(){
		
		return pointshopDAO.selectItems();
	}
	
	@Transactional
	public void addItem(AddItemCommand addItemCommand) throws AlreadyExistItemnameException, NoImageException, NoValueException {
		
		//아이템 이름 중복체크
		int dup = pointshopDAO.countByItemName(addItemCommand.getItemName());
		if(dup == 1) {
			throw new AlreadyExistItemnameException();
		}
		
		//아이템 이미지 처리
		String path = "C:\\stsproject\\upload\\images\\icons";
		MultipartFile itemImgfile = addItemCommand.getItemImg();
		InputStream inputStream = null;
		File file = null;
		String savedFilename = null;
		try {
			if(itemImgfile != null && !itemImgfile.isEmpty()) {//파일이 있다면
				savedFilename = UUID.randomUUID().toString().replaceAll("-", "") + itemImgfile.getOriginalFilename();
				inputStream = itemImgfile.getInputStream();
				boolean isImage = FileTypeByTika.validItemImgFile(inputStream);
				if(!isImage) { //이미지파일이 아니면 예외
					inputStream.close();
					throw new NoImageException();
				}else {
					file = new File(path, savedFilename);
					inputStream.close();
					itemImgfile.transferTo(file);
				}
			}else {//파일 없다면
				throw new NoValueException();
			}
			
			ItemsDTO itemsDTO = new ItemsDTO();
			itemsDTO.setItemFilename(savedFilename);
			itemsDTO.setItemName(addItemCommand.getItemName());
			itemsDTO.setItemPrice(addItemCommand.getItemPrice());
			int res = pointshopDAO.insertIconByAdmin(itemsDTO);
			logger.info("인서트 성공? : " + res);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//디테일
	public ItemsDTO detail(Long num) {
		return (ItemsDTO) pointshopDAO.selectItemsBySeq(num);
	}
	
	//아이템 수정
	@Transactional
	public void itemUpdate(Long num, AddItemCommand addItemCommand) throws AlreadyExistItemnameException, NoImageException {
		
		ItemsDTO preItem = detail(num);
		//기존 이름과 같으면 굳이 중복체크 안함, 다르면 중복체크함
		if(!addItemCommand.getItemName().equals(preItem.getItemName())) {
			int dup = pointshopDAO.countByItemName(addItemCommand.getItemName());
			if(dup == 1) {
				throw new AlreadyExistItemnameException();
			}
		}
		
		String path = "C:\\stsproject\\upload\\images\\icons";
		MultipartFile itemImgfile = addItemCommand.getItemImg();
		InputStream inputStream = null;
		File file = null;
		File oldFile = null;
		String savedFilename = null;
		String DEFAULT_ICON_NAME = "default_icon.png";
		
		try {
			ItemsDTO newItem = new ItemsDTO();
			
			if(itemImgfile != null && !itemImgfile.isEmpty()) {//파일이 있다면
				
				savedFilename = UUID.randomUUID().toString().replaceAll("-", "") + itemImgfile.getOriginalFilename();
				inputStream = itemImgfile.getInputStream();
				boolean isImage = FileTypeByTika.validItemImgFile(inputStream);
				String oldName = preItem.getItemFilename();
				
				if(!isImage) { //이미지파일이 아니면 예외
					inputStream.close();
					throw new NoImageException();
				}else {//이미지 파일일경우 DB수정
					file = new File(path, savedFilename);
					inputStream.close();
					itemImgfile.transferTo(file); //파일저장
					newItem.setItemFilename(savedFilename);
					newItem.setItemNum(num);
					int resForNewImg = pointshopDAO.updateItemFilename(newItem);//db업데이트
					logger.info("새 아이콘 사진 업데이트 성공? : " + resForNewImg);
				}
				
				//기존 파일이 디폴트아이콘이 아니면 삭제
				if(!oldName.equals(DEFAULT_ICON_NAME)) {
					oldFile = new File(path, oldName);
					if(oldFile.exists()) {
						if(oldFile.delete()) {
							logger.info("기존 아이콘사진 삭제 성공!");
						}else {
							logger.error("기존 아이콘사진 삭제 실패!");
						}
					}
				}
			}
			
			//파일이 없을경우는 이름과 가격만 수정
			newItem.setItemName(addItemCommand.getItemName());
			newItem.setItemPrice(addItemCommand.getItemPrice());
			newItem.setItemNum(num);
			pointshopDAO.updateItemNamePrice(newItem);
			
		}catch(IOException e) {
			logger.error("IO예외발생!");
			e.printStackTrace();
		}
	}
	
	@Transactional
	public int itemDelete(Long num) {
		//아이콘 삭제할때 서버 이미지도 삭제
		ItemsDTO item = (ItemsDTO) pointshopDAO.selectItemsBySeq(num);
		String imgName = item.getItemFilename();
		String path = "C:\\stsproject\\upload\\images\\icons";
		String DEFAULT_ICON_NAME = "default_icon.png";
		File file = new File(path, imgName);
		
		//기본아이콘이 아니면 삭제
		if(!imgName.equals(DEFAULT_ICON_NAME)) {
			if(file.exists()) {
				if(file.delete()) {
					logger.info("기존 아이콘사진 삭제 성공!");
				}else {
					logger.error("기존 아이콘사진 삭제 실패!");
				}
			}
		}
		//DB데이터 삭제
		int resDelete = pointshopDAO.deleteItemBySeq(num);
		
		return resDelete;
	}
	
	
}
