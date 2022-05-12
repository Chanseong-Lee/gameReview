package com.game.review.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.game.review.admin.service.AdminGameModifyService;
import com.game.review.game.command.GameFileModifyCommand;
import com.game.review.game.command.GameModifyCommand;
import com.game.review.game.command.GenreModifyCommand;
import com.game.review.game.command.SpecModifyCommand;
import com.game.review.game.exception.AlreadyExistGameCodeException;
import com.game.review.game.exception.AlreadyExistGameNameException;
import com.game.review.game.exception.NoCheckFileException;
import com.game.review.game.exception.NoCheckGenreException;
import com.game.review.game.exception.NoFileException;
import com.game.review.game.exception.NoImageException;
import com.game.review.game.validate.GameModifyCommandValidator;

@Controller
public class AdminGameModifyController {
	
	@Autowired
	private AdminGameModifyService adminGameModifyService;
	
	// 게임 정보수정
	@RequestMapping(value = "/admin/game/gameModify/{gNum}", method = RequestMethod.GET)
	public String goGameModify(@ModelAttribute("modifyGame") GameModifyCommand gameModifyCommand,
			@PathVariable("gNum") Long gNum, Model model) {

		model.addAttribute("game", adminGameModifyService.selectGameBySeq(gNum));

		return "admin/game/gameModify";
	}

	@RequestMapping(value = "/admin/game/gameModify/{gNum}", method = RequestMethod.POST)
	public String gameModify(@ModelAttribute("modifyGame") GameModifyCommand gameModifyCommand, Errors errors,
			@PathVariable("gNum") Long gNum, Model model) {
		new GameModifyCommandValidator().validate(gameModifyCommand, errors);
		if(errors.hasErrors()) {
			return "admin/game/gameModify";
		}
		try {
			adminGameModifyService.updateGame(gameModifyCommand);
			
			return "redirect:/admin/game/gameDetail/{gNum}";
		}catch(AlreadyExistGameNameException e) {
			errors.rejectValue("gName", "dupGamename");
			return "admin/game/gameModify";
		}catch(AlreadyExistGameCodeException e) {
			errors.rejectValue("gCode", "dupGameCode");
			return "admin/game/gameModify";
		}
	}

	// 게임 사양 수정
	@RequestMapping(value = "/admin/game/specModify/{gNum}", method = RequestMethod.GET)
	public String goSpecModify(@ModelAttribute("modifySpec") SpecModifyCommand specModifyCommand,
			@PathVariable("gNum") Long gNum, Model model) {

		model.addAttribute("game", adminGameModifyService.selectGameBySeq(gNum));
		model.addAttribute("spec", adminGameModifyService.selectSpecBySeq(gNum));

		return "admin/game/specModify";
	}

	@RequestMapping(value = "/admin/game/specModify/{gNum}", method = RequestMethod.POST)
	public String specModify(@ModelAttribute("modifySpec") SpecModifyCommand specModifyCommand,
			@PathVariable("gNum") Long gNum, Model model) {

		adminGameModifyService.updateSpec(specModifyCommand);
		
		return "redirect:/admin/game/gameDetail/"+gNum;

	}

	// 게임 장르 수정
	@RequestMapping(value = "/admin/game/genreModify/{gNum}", method = RequestMethod.GET)
	public String goGenreModify(@ModelAttribute("modifyGenre") GenreModifyCommand genreModifyCommand,
			@PathVariable("gNum") Long gNum, Model model) {
		model.addAttribute("AllGenres", adminGameModifyService.selectAllGenres());
		model.addAttribute("genresOfGame", adminGameModifyService.selectGenresBySeq(gNum));
		model.addAttribute("game", adminGameModifyService.selectGameBySeq(gNum));

		return "admin/game/genreModify";
	}

	@RequestMapping(value = "/admin/game/genreModify/{gNum}", method = RequestMethod.POST)
	public String genreModify(@ModelAttribute("modifyGenre") GenreModifyCommand genreModifyCommand,
			@PathVariable("gNum") Long gNum, Model model) {
	
			adminGameModifyService.deleteGenre(genreModifyCommand);
			adminGameModifyService.updateGenre(genreModifyCommand);

		return "redirect:/admin/game/gameDetail/"+gNum;

	}

	@ExceptionHandler(NoCheckGenreException.class)
	public String noChexckGenre() {
		return "exceptions/noCheckGenre";
	}	
	
	//게임파일 수정 폼
	@RequestMapping(value = "/admin/game/gameFileModify/{gNum}", method = RequestMethod.GET)
	public String goGameFileModify(@ModelAttribute("modifyGenre") GameFileModifyCommand gameFileModifyCommand,
			@PathVariable("gNum") Long gNum, Model model) {

		model.addAttribute("gameFilesList", adminGameModifyService.selectGameFilesBySeq(gNum));
		model.addAttribute("game", adminGameModifyService.selectGameBySeq(gNum));

		return "admin/game/gameFileModify";
	}

	//메인 이미지 수정 로직
	@RequestMapping(value = "/admin/game/mainFileUpdate/{gNum}", method = RequestMethod.POST)
	public String mainFileUpdate(@ModelAttribute("modifyGameFile") GameFileModifyCommand gameFileModifyCommand, Errors errors,
			@PathVariable("gNum") Long gNum, Model model) {

		model.addAttribute("gameFilesList", adminGameModifyService.selectGameFilesBySeq(gNum));
		model.addAttribute("game", adminGameModifyService.selectGameBySeq(gNum));

		try {
			adminGameModifyService.updateMainFile(gameFileModifyCommand);
			
			return "redirect:/admin/game/gameFileModify/" + gNum;
		} catch (NoFileException e) {
			return "exceptions/noCheckAddFile";
		} catch(NoImageException e) {
			errors.rejectValue("imgFile", "noImage");
			return "admin/game/gameFileModify";
		}
	}
	
//	@RequestMapping(value = "/admin/game/mainFileDelete/{gNum}", method = RequestMethod.POST)
//	public String mainFileDelete(@ModelAttribute("modifyGameFile") GameFileModifyCommand gameFileModifyCommand,
//			@PathVariable("gNum") Long gNum, Model model) {
//
//		model.addAttribute("gameFilesList", adminGameModifyService.selectGameFilesBySeq(gNum));
//		model.addAttribute("game", adminGameModifyService.selectGameBySeq(gNum));
//
//		adminGameModifyService.deleteMainFile(gameFileModifyCommand);
//
//		return "redirect:/admin/game/gameFileModify/" + gNum;
//	}
	
	//슬라이더파일 추가로직
	@RequestMapping(value = "/admin/game/slideFileUpdate/{gNum}", method = RequestMethod.POST)
	public String slideFileUpdate(@ModelAttribute("modifyGameFile") GameFileModifyCommand gameFileModifyCommand, Errors errors,
			@PathVariable("gNum") Long gNum, Model model) {

		model.addAttribute("gameFilesList", adminGameModifyService.selectGameFilesBySeq(gNum));
		model.addAttribute("game", adminGameModifyService.selectGameBySeq(gNum));
		
		try {
			
			adminGameModifyService.updateSlideFile(gameFileModifyCommand);
			return "redirect:/admin/game/gameFileModify/" + gNum;
			
		} catch (NoFileException e) {
			return "exceptions/noCheckAddFile";
		} catch(NoImageException e) {
			errors.rejectValue("imgFile", "noImage");
			return "admin/game/gameFileModify";
		}

	}

	@RequestMapping(value = "/admin/game/slideFileDelete/{gNum}", method = RequestMethod.POST)
	public String slideFileDelete(@ModelAttribute("modifyGameFile") GameFileModifyCommand gameFileModifyCommand,
			@PathVariable("gNum") Long gNum, Model model) {

		model.addAttribute("gameFilesList", adminGameModifyService.selectGameFilesBySeq(gNum));
		model.addAttribute("game", adminGameModifyService.selectGameBySeq(gNum));
		System.out.println("파일번호" + gameFileModifyCommand.getGfLocation());
		
		try {
			
			adminGameModifyService.deleteSlideFile(gameFileModifyCommand);
			return "redirect:/admin/game/gameFileModify/" + gNum;
			
		} catch (NoCheckFileException e) {
			return "exceptions/NoCheckDelFile";
		}

	}
}
