package com.game.review.game.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.game.review.admin.service.AdminGameService;
import com.game.review.game.command.ArtCommand;
import com.game.review.game.dto.ArticlesDTO;
import com.game.review.game.dto.GameFilesDTO;
import com.game.review.game.dto.GamesDTO;
import com.game.review.game.dto.GenreDTO;
import com.game.review.game.dto.SpecDTO;
import com.game.review.game.service.ArticlesService;
import com.game.review.game.service.RepliesService;
import com.game.review.game.validate.ArtCommandValidator;
import com.game.review.member.command.LoginUserDetails;

@Controller
//리뷰 등록 컨트롤러
public class ArticlesController {

	@Autowired
	private ArticlesService articlesservice;

	@Autowired
	private RepliesService repliesService;
	
	@Autowired
	private AdminGameService adminGameService;

	// 리뷰등록 할때
	@RequestMapping(value = "/game/gameDetail/{gNum}", method = RequestMethod.GET)
	public String art(@ModelAttribute("artCommand") ArtCommand artCommand, Errors errors, Model model,
			@PathVariable("gNum") Long gNum) {
		
		// 게임 디테일(정보, 장르, 사양, 파일)
		GamesDTO game = adminGameService.detailGame(gNum);
		List<GenreDTO> genres = adminGameService.detailGenres(gNum);
		SpecDTO spec = (SpecDTO) adminGameService.detailSpec(gNum);
		List<GameFilesDTO> files = adminGameService.detailGameFiles(gNum);
		
		// 리뷰글 리스트
		List<ArticlesDTO> articles = articlesservice.selectArticlesByGameSeq(gNum);
		int articlesCnt = articlesservice.countArticlesInGame(gNum);
		int replyCnt = 0;
		for (ArticlesDTO article : articles) {
			replyCnt = repliesService.selectCountForReview(article.getaNum());
		}
		
		model.addAttribute("game", game);
		model.addAttribute("genres", genres);
		model.addAttribute("spec", spec);
		model.addAttribute("files", files);
		model.addAttribute("artCommand", artCommand);
		model.addAttribute("articles", articles);
		model.addAttribute("replyCnt", replyCnt);
		model.addAttribute("articlesCnt", articlesCnt);

		return "game/review/gameDetail";
	}

	// 리뷰등록 할때
	@RequestMapping(value = "/game/gameDetail/{gNum}", method = RequestMethod.POST)
	public String art2(@ModelAttribute("artCommand") ArtCommand artCommand, Errors errors, Model model,
			@PathVariable("gNum") Long gNum, @AuthenticationPrincipal LoginUserDetails loginUserDetails) {
		
		// 게임 디테일(정보, 장르, 사양, 파일)
				GamesDTO game = adminGameService.detailGame(gNum);
				List<GenreDTO> genres = adminGameService.detailGenres(gNum);
				SpecDTO spec = (SpecDTO) adminGameService.detailSpec(gNum);
				List<GameFilesDTO> files = adminGameService.detailGameFiles(gNum);
					
				// 리뷰글 리스트
				List<ArticlesDTO> articles = articlesservice.selectArticlesByGameSeq(gNum);
				int articlesCnt = articlesservice.countArticles();
				int replyCnt = 0;
				for (ArticlesDTO article : articles) {
					replyCnt = repliesService.selectCountForReview(article.getaNum());
				}		
				
				model.addAttribute("game", game);
				model.addAttribute("genres", genres);
				model.addAttribute("spec", spec);
				model.addAttribute("files", files);
				model.addAttribute("articles", articles);
				model.addAttribute("replyCnt", replyCnt);
				model.addAttribute("articlesCnt", articlesCnt);
		
		new ArtCommandValidator().validate(artCommand, errors);
		if (errors.hasErrors()) {
			return "game/review/gameDetail";
		}
		artCommand.setgNum(gNum);
		articlesservice.artregist(artCommand, loginUserDetails);
		model.addAttribute("artCommand", artCommand);
		
		
		
		return "redirect:/game/gameDetail/{gNum}";

	}

	// 리뷰 수정하기
	@RequestMapping(value = "/artupdate/{gNum}/{aNum}", method = RequestMethod.GET)
	public String ArtUpdate(@ModelAttribute("ArtUpdateData") ArtCommand acmd, Errors errors,
			@PathVariable("gNum") Long gNum, @PathVariable("aNum") Long aNum, Model model) {
		System.out.println("리뷰 업데이트 겟");
		Long mNum = (long) 2;

		// 내가 작성했던 리뷰글 보여주기
		List<ArticlesDTO> art = articlesservice.selectContent(gNum, mNum, aNum);
		System.out.println("art :" + art);
		model.addAttribute("art", art);

		return "/artupdate";

	}

	// 리뷰 수정하기
	@RequestMapping(value = "/artupdate/{gNum}/{aNum}", method = RequestMethod.POST)
	public String ArtUpdate2(@ModelAttribute("ArtUpdateData") ArtCommand acmd, Errors errors,
			@PathVariable("gNum") Long gNum, @PathVariable("aNum") Long aNum) {

		System.out.println("리뷰 업데이트 포스트");
		ArticlesDTO adto = new ArticlesDTO();
		adto.setaContent(acmd.getaContent());
		adto.setgNum(gNum);
		adto.setaNum(aNum);
		adto.setmNum((long) 2);
		adto.setaScore(acmd.getaScore());

		// 리뷰 수정 업데이트문
		articlesservice.ArtUpdate(adto);

		return "redirect:/art/{gNum}";

	}

	// 리뷰글 삭제하기
	@RequestMapping(value = "/game/artdelete/{aNum}", method = RequestMethod.GET)
	public String ArtDelete(@PathVariable("aNum") Long aNum, Model model, @AuthenticationPrincipal LoginUserDetails loginUserDetails) {

		// 리뷰글 삭제하기 전에 내가 쓴 리뷰글 확인하기
		Long mNum = loginUserDetails.getNum();
		Long gNum = articlesservice.deleteArticle(mNum, aNum);
		model.addAttribute("gNum", gNum);
		return "game/review/articleDeleteSuccess";
	}


}
