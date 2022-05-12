package com.game.review.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.review.game.dto.ArticlesDTO;
import com.game.review.game.service.ArticlesService;


@Controller
//추천 컨트롤러
public class LikeController {
	
	@Autowired
	private ArticlesService artservice ;

	    //추천 컨트롤러   리뷰 넘버 aNum,  맴버 넘버 mNum
    @ResponseBody
	@RequestMapping( value= "/game/updateLike", method = RequestMethod.POST )
	  public String updateLike(Long aNum, Long gNum , Long mNum, Long loginUserNum) {
		
    	     //Like 테이블에 aNum과 mNum을 확인해서 있는지 없는지 0과 1로 확인
    	ArticlesDTO article = new ArticlesDTO();
    	article.setaNum(aNum);
    	article.setgNum(gNum);
    	article.setmNum(loginUserNum);
    	
    	int likeCheck = artservice.likeCheck(article);
		  System.out.println("좋아요카운터:" +likeCheck);
		  //Like 테이블이 0일 경우 Like테이블에 1이 인서트되고 Articles 테이블에 좋아요가 1 증가
		  if(likeCheck == 0) {
			  System.out.println(likeCheck);
			  //좋아요 처음 누름
			  artservice.insertLike(article); //테이블 삽입
 			  System.out.println("추천 ajax 인서트확인");
			  
 			  article.setmNum(mNum);
			  artservice.updateLikeCheck(article); //like테이블 구분자 1
			  System.out.println("추천 ajax 좋아요 +1 확인 끝");
			 System.out.println(" 맴버번호는: "+mNum);
			 
		  } else if(likeCheck == 1) {
			  artservice.deleteLike(article);
			  System.out.println("추천 취소ajax 좋아요테이블삭제 확인 끝") ;
			  
			  article.setmNum(mNum);
			  artservice.updateLikeCheckCancel(article); 
			  System.out.println("추천 취소ajax 좋아요 -1 확인 끝");
		  }
		  return Integer.toString(likeCheck); 
			  
	  }
	  
	
	
}
