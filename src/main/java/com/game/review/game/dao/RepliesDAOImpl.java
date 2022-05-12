package com.game.review.game.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.review.game.dto.RepliesDTO;

@Repository
public class RepliesDAOImpl implements RepliesDAO{
	
	   @Autowired
	   private SqlSessionTemplate sst ;
	   
	 //댓글 등록
	   @Override
	   public void insertRe(RepliesDTO rdto) {
		   sst.insert("insertRe", rdto) ;
	   }
	   
		//최신 순서로 댓글 보여주기
	   @Override
	   public List<RepliesDTO> selectRe(RepliesDTO rdto){
		   Map<String,Long> map = new HashMap<String,Long>();
		   map.put("kk", rdto.getaNum());
		   
		   
		   return sst.selectList("selectRe" , map);
	   }
	   
	   
	// Articles 컨트롤러에 사용 댓글이 몇개 달려있는지 보여주기 ex) 댓글 달기(3)
	   @Override
	   public int selectCountForReview(Long aNum) {
		   return sst.selectOne("selectCountForReview", aNum) ;
	   }
	   
	 //댓글 수정하기
	   @Override
	   public void updateRe(RepliesDTO rdto) {
		   sst.update("updateRe", rdto) ;
	   }
	   
	 //댓글 삭제 전에 내가 쓴 댓글들 보여주기
	   @Override
	   public List<RepliesDTO> selectdel(RepliesDTO rdto){
		   Map<String,Long> map2 = new HashMap<String,Long>();
		   map2.put("kk", rdto.getaNum());
		   map2.put("kk2", rdto.getmNum());
		   
		   return sst.selectList("selectdel" , map2);
	   }
	   
		//댓글 삭제하기
	   @Override
	   public void deleteRe(RepliesDTO rdto) {
		   sst.delete("deleteRe", rdto) ;
	   }
	
	  
}
