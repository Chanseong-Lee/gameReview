package com.game.review.game.dao;

import java.util.List;

import com.game.review.game.dto.ArticlesDTO;

public interface ArticlesDAO {

	public void insertArticle(ArticlesDTO articlesdto) ;
	
	//리뷰 글 번호 순서대로 정렬해서 최신글 보여주기
	public List<ArticlesDTO> selectArticles() ;
	
	//게임당 리뷰글 내림차순
	public List<ArticlesDTO> selectArticlesByGameSeq(Long gNum) ;
	
	  // Like 컨트롤러
	   // Like 테이블에 aNum과 mNum 정보가 없으면 0에서 1로 인서트
	public void insertLike(ArticlesDTO articlesDTO) ;  // Like 컨트롤러
	
	 // Like 컨트롤러
	   // Like 테이블에 aNum과 mNum 정보가 있으면 행 딜리트
	public void deleteLike(ArticlesDTO articlesDTO) ;   // Like 컨트롤러
	
	  // Like 컨트롤러
	   // Like 테이블에 aNum과 mNum 정보가  있는지 없는지 확인
	public int likeCheck(ArticlesDTO articlesDTO) ;   // Like 컨트롤러
	
	   // Like 컨트롤러
	   // Articles 테이블에 좋아요 컬럼 1추가
	public void updateLikeCheck(ArticlesDTO articlesDTO) ;   // Like 컨트롤러
	
	   // Like 컨트롤러 
		// Articles 테이블에 좋아요 컬럼 1 감소
	public void updateLikeCheckCancel(ArticlesDTO articlesDTO) ;   // Like 컨트롤러
	
	  //리뷰글 수정하기
	public void ArtUpdate(ArticlesDTO articlesdto) ;
	
	  //리뷰글 삭제하기
	public void ArtDelete(ArticlesDTO articlesdto) ;

	   // 리뷰글 삭제하기전에 내가쓴 리뷰 글 보여주기
	public ArticlesDTO selectDel(ArticlesDTO articlesDTO);
	
	// 리뷰글 수정할때 내가 썻던 글 보여주기
	public List<ArticlesDTO> selectContent(ArticlesDTO articlesDTO);
	
	public int countArticles();
	public int countArticlesInGame(Long gNum);
}
