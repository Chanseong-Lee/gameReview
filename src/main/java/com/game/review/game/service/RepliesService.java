package com.game.review.game.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.review.game.command.RepliesCommand;
import com.game.review.game.dao.RepliesDAO;
import com.game.review.game.dto.RepliesDTO;

@Service
public class RepliesService {
	
	@Autowired
	private RepliesDAO repliesDAO ;
	
	
	//댓글 등록
	public void insetRe(RepliesCommand rcmd) {
		RepliesDTO rdto = new RepliesDTO() ;
		
		Long i = (long) 1 ;
		rdto.setmNum(i);  // 맴버넘버
		
		rdto.setaNum(rcmd.getaNum());  //리뷰넘버
		rdto.setReContent(rcmd.getReContent());  //댓글내용
		
		repliesDAO.insertRe(rdto);
		
	}
	
	//최신 순서로 댓글 보여주기
	public List<RepliesDTO> selectRe(RepliesDTO rdto){
	    	List<RepliesDTO> rep = repliesDAO.selectRe(rdto);
	    	
	    	return rep ;
	}
	
	// Articles 컨트롤러에 사용 댓글이 몇개 달려있는지 보여주기 ex) 댓글 달기(3)
	public int selectCountForReview(Long aNum) {
		int result =  repliesDAO.selectCountForReview(aNum);
	     return result ;
	} 
	
	//댓글 수정하기
	public void updateRe(RepliesDTO rdto) {
	
		repliesDAO.updateRe(rdto);
		     
	}
	
	//댓글 삭제 전에 내가 쓴 댓글들 보여주기
	public List<RepliesDTO> selectdel(RepliesDTO rdto){
		List<RepliesDTO> rep = repliesDAO.selectdel(rdto);
		return rep ;
	}
	
	//댓글 삭제하기
	public void deleteRe(RepliesDTO rdto) {
	
		repliesDAO.deleteRe(rdto);
	}
	
	
	

}
