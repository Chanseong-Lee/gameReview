package com.game.review.game.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.review.game.command.ArtCommand;
import com.game.review.game.dao.ArticlesDAO;
import com.game.review.game.dto.ArticlesDTO;
import com.game.review.member.command.LoginUserDetails;
import com.game.review.member.dao.MemberDAO;
import com.game.review.member.dto.MemberDTO;

@Service
public class ArticlesService {

	@Autowired
	private ArticlesDAO articlesDAO;
	@Autowired
	private MemberDAO memberDAO;
	// 리뷰 글 등록
	public void artregist(ArtCommand artCommand, LoginUserDetails loginUserDetails)  {
		ArticlesDTO adto = new ArticlesDTO();

		adto.setaContent(artCommand.getaContent());
		adto.setaScore(artCommand.getaScore());
		adto.setgNum(artCommand.getgNum());
		adto.setmNum(loginUserDetails.getNum());
		//리뷰등록
		articlesDAO.insertArticle(adto);
		
		//리뷰등록하면 포인트 오름
		MemberDTO memberPointInfo = new MemberDTO();
		memberPointInfo.setmNum(loginUserDetails.getNum());
		memberPointInfo.setmPoint(50L);
		memberDAO.updatePointIncrease(memberPointInfo);
		//포인트 세션반영
		Long pointAfterWrite = loginUserDetails.getPoint()+50;
		loginUserDetails.setPoint(pointAfterWrite);
	}
	
	public int countArticles() {
		return articlesDAO.countArticles();
	}
	
	public int countArticlesInGame(Long aNum) {
		return articlesDAO.countArticlesInGame(aNum);
	}
	
	// 리뷰 글 번호 순서대로 정렬해서 최신글 보여주기
	public List<ArticlesDTO> selectArticles() {
		List<ArticlesDTO> articles = articlesDAO.selectArticles();
		return articles;
	}
	// 게임당 리뷰글
	public List<ArticlesDTO> selectArticlesByGameSeq(Long gNum) {
		List<ArticlesDTO> articles = articlesDAO.selectArticlesByGameSeq(gNum);
		return articles;
	}

	// Like 컨트롤러
	// Like 테이블에 aNum과 mNum 정보가 없으면 0에서 1로 인서트
	public void insertLike(ArticlesDTO article) {
		articlesDAO.insertLike(article);
	}

	// Like 컨트롤러
	// Like 테이블에 aNum과 mNum 정보가 있으면 행 딜리트
	public void deleteLike(ArticlesDTO article) {
		articlesDAO.deleteLike(article);
	}

	// Like 컨트롤러
	// Like 테이블에 aNum과 mNum 정보가 있는지 없는지 확인
	public int likeCheck(ArticlesDTO article) {
		return articlesDAO.likeCheck(article);
	}

	// Like 컨트롤러
	// Articles 테이블에 좋아요 컬럼 1추가
	public void updateLikeCheck(ArticlesDTO article) {
		articlesDAO.updateLikeCheck(article);
	}

	// Like 컨트롤러
	// Articles 테이블에 좋아요 컬럼 1 감소
	public void updateLikeCheckCancel(ArticlesDTO article) {
		articlesDAO.updateLikeCheckCancel(article);
	}

	// 리뷰글 수정하기
	public void ArtUpdate(ArticlesDTO articlesdto) {
		articlesDAO.ArtUpdate(articlesdto);

	}

	public Long deleteArticle(Long mNum, Long aNum) {
		ArticlesDTO article = new ArticlesDTO();
		article.setmNum(mNum);
		article.setaNum(aNum);
		
		ArticlesDTO isExistDTO = articlesDAO.selectDel(article);
		if(isExistDTO != null) {
			articlesDAO.ArtDelete(isExistDTO);
		}
		return isExistDTO.getgNum();
	}

	// 리뷰글 수정할때 내가 썻던 글 보여주기
	public List<ArticlesDTO> selectContent(Long gNum, Long mNum, Long aNum) {
		ArticlesDTO article = new ArticlesDTO();
		article.setmNum(mNum);
		article.setaNum(aNum);
		article.setgNum(gNum);
		return articlesDAO.selectContent(article);
	}

}
