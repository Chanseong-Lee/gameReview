package com.game.review.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.game.review.admin.service.AdminGameService;
import com.game.review.game.command.GameRegCommand;
import com.game.review.game.command.GameSpecCommand;
import com.game.review.game.dto.GameFilesDTO;
import com.game.review.game.dto.GamesDTO;
import com.game.review.game.dto.GenreDTO;
import com.game.review.game.dto.SpecDTO;
import com.game.review.game.exception.AlreadyExistGameCodeException;
import com.game.review.game.exception.AlreadyExistGameNameException;
import com.game.review.game.exception.NoImageException;
import com.game.review.game.validate.GameRegValidator;

@Controller
public class AdminGameController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminGameController.class);
	
	@Autowired
	private AdminGameService adminGameService;
	
	//game list
	@RequestMapping(value="/admin/game/gameList", method = RequestMethod.GET)
	public String goGameList(Model model) {
		List<GenreDTO> genrelist = adminGameService.selectGenreList();
		List<GameFilesDTO> gameFileslist = adminGameService.selectGameFilesList();
		List<GamesDTO> gameList = adminGameService.selectGameList();
		
		model.addAttribute("genreList", genrelist);
		model.addAttribute("gameFilesList", gameFileslist);
		model.addAttribute("gameList", gameList);
		
		return "admin/game/gameList";
	}
	
	
	//game insert
	@RequestMapping(value = "/admin/game/gameReg", method = RequestMethod.GET)
	public String goGameRegForm(@ModelAttribute("gameRegCommand") GameRegCommand gameRegCommand, Model model) {
		model.addAttribute("genreList", adminGameService.selectGenre());
		
		return "admin/game/gameRegForm";
	}

	@RequestMapping(value = "/admin/game/gameReg", method = RequestMethod.POST)
	public String register(@ModelAttribute("gameRegCommand") GameRegCommand gameRegCommand, HttpServletRequest request,
			Model model, Errors errors) {
		model.addAttribute("genreList", adminGameService.selectGenre());
		
		new GameRegValidator().validate(gameRegCommand, errors);
		if (errors.hasErrors()) {
			return "admin/game/gameRegForm";
		}
		try {
			
			adminGameService.gameDupCheck(gameRegCommand);
			
			HttpSession session = request.getSession();
			session.setAttribute("gameRegCommandInSession", gameRegCommand);
			return "redirect:/admin/game/specReg";
		} catch (AlreadyExistGameNameException e) {
			errors.rejectValue("gName", "dupGamename");
			return "admin/game/gameRegForm";
		} catch (AlreadyExistGameCodeException e) {
			errors.rejectValue("gCode", "dupGameCode");
			return "admin/game/gameRegForm";
		}

	}
	
	
	//insert spec
	@RequestMapping(value = "/admin/game/specReg", method = RequestMethod.GET)
	public String gospecForm(@ModelAttribute("gameSpecCommand") GameSpecCommand gameSpecCommand, Model model,
			HttpServletRequest request) {
		GameRegCommand dd = (GameRegCommand) request.getSession().getAttribute("gameRegCommandInSession");
		model.addAttribute("gName", dd.getgName());
		return "admin/game/specRegForm";
	}
	
	@RequestMapping(value = "/admin/game/specReg", method = RequestMethod.POST)
	public String register1(@ModelAttribute("gameSpecCommand") GameSpecCommand gameSpecCommand, Errors errors, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			
			GameRegCommand gameInSession = (GameRegCommand) session.getAttribute("gameRegCommandInSession");
			logger.info(gameInSession.toString());
			
			adminGameService.insertGame(gameInSession);
			adminGameService.specRegist(gameSpecCommand, gameInSession.getgCode());
			
			//remove Session Attribute after get the value
			session.removeAttribute("gameRegCommandInSession");
			return "redirect:/admin/game/gameList";
			
		}catch (NoImageException e) {
			logger.error("이미지 파일이 아님");
			errors.rejectValue("slideImgFile", "noImage");
			return "admin/game/specRegForm";
		}
	}
	
	//detail
	@RequestMapping(value = "/admin/game/gameDetail/{gNum}", method = RequestMethod.GET)
	public String read(@PathVariable("gNum") Long gNum, Model model) {
		GamesDTO game = adminGameService.detailGame(gNum);
		List<GenreDTO> genrelist = adminGameService.detailGenres(gNum);
		List<GameFilesDTO> gameFileslist = adminGameService.detailGameFiles(gNum);
		SpecDTO spec = (SpecDTO) adminGameService.detailSpec(gNum);
		logger.info(game.toString());
		logger.info(spec.toString());
		model.addAttribute("genreList", genrelist);
		model.addAttribute("gameFilesList", gameFileslist);
		model.addAttribute("game", game);
		model.addAttribute("spec", spec);

		return "admin/game/gameDetail";

	}
}
