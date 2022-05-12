package com.game.review.game.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.review.game.dto.ArticlesDTO;

@Repository
public class ArticlesDAOImpl implements ArticlesDAO{
	
	 @Autowired
	 private SqlSessionTemplate sqlsessiontemplate ;
	 
	 //리뷰 글 등록 
	 @Override
	 public void insertArticle(ArticlesDTO articlesdto) {
		 sqlsessiontemplate.insert("insertArticle", articlesdto) ;
	 }
	 
	//리뷰 글 번호 순서대로 정렬해서 최신글 보여주기
	 @Override
	 public List<ArticlesDTO> selectArticles() {
		 return sqlsessiontemplate.selectList("selectArticles") ;
	 }
	 @Override
	 public List<ArticlesDTO> selectArticlesByGameSeq(Long gNum) {
		 return sqlsessiontemplate.selectList("selectArticlesByGameSeq", gNum) ;
	 }
	 
	 // Like 컨트롤러
	   // Like 테이블에 aNum과 mNum 정보가 없으면 0에서 1로 인서트
	 @Override
	 public void insertLike(ArticlesDTO articlesDTO)  {  
	    sqlsessiontemplate.insert("insertLike", articlesDTO) ;
	    
	}
	 
	 // Like 컨트롤러
	   // Like 테이블에 aNum과 mNum 정보가 있으면 행 딜리트
	 @Override
	 public void deleteLike(ArticlesDTO articlesDTO)  {
	    sqlsessiontemplate.delete("deleteLike", articlesDTO) ;
	    
			}
	 
	   // Like 컨트롤러
	   // Like 테이블에 aNum과 mNum 정보가  있는지 없는지 확인
	 @Override
	 public int likeCheck(ArticlesDTO articlesDTO)  {
	    return sqlsessiontemplate.selectOne("likeCheck", articlesDTO) ;
	    
			}
	 
	// Like 컨트롤러
	   // Articles 테이블에 좋아요 컬럼 1추가인
	 @Override
	 public void updateLikeCheck(ArticlesDTO articlesDTO)  {
	    sqlsessiontemplate.update("updateLikeCheck", articlesDTO) ;
	    
			}
	 
	   // Like 컨트롤러 
		// Articles 테이블에 좋아요 컬럼 1 감소
	 @Override
	 public void updateLikeCheckCancel(ArticlesDTO articlesDTO)  {
	    sqlsessiontemplate.update("updateLikeCheckCancel", articlesDTO) ;
	    
			}
	
	//리뷰글 수정하기
	 @Override
	 public void ArtUpdate(ArticlesDTO articlesdto) {
		 sqlsessiontemplate.update("artupdate", articlesdto) ;
	 }
	   //리뷰글 삭제하기
	 @Override
	 public void ArtDelete(ArticlesDTO articlesdto) {
		 sqlsessiontemplate.delete("artdelete", articlesdto) ;
	 }
	 
	// 리뷰글 삭제하기전에 내가쓴 리뷰 글 보여주기
	 @Override
	 public ArticlesDTO selectDel(ArticlesDTO articlesDTO) {
		 return sqlsessiontemplate.selectOne("selectDel", articlesDTO) ;
	 }
	 
	// 리뷰글 수정할때 내가 썻던 글 보여주기
	 @Override
	 public List<ArticlesDTO> selectContent(ArticlesDTO articlesDTO) {
		 return sqlsessiontemplate.selectList("selectContent", articlesDTO) ;
	 }

	@Override
	public int countArticles() {
		return sqlsessiontemplate.selectOne("countArticles");
	}
	@Override
	public int countArticlesInGame(Long gNum) {
		return sqlsessiontemplate.selectOne("countArticlesInGame", gNum);
	}
	 
	 
	 
}
