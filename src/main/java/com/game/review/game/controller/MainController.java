package com.game.review.game.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.game.review.game.dto.GameFilesDTO;
import com.game.review.game.dto.GamesDTO;
import com.game.review.game.dto.GenreDTO;
import com.game.review.game.service.MainService;

@Controller
public class MainController {
	
	@Autowired
	private MainService mainService;
	
	@RequestMapping(value="/game/main", method=RequestMethod.GET)
	public String goMain(Model model) {
		
		List<GamesDTO> games = mainService.gameList();
		List<GameFilesDTO> files = mainService.gameFileList();
		List<GenreDTO> genres = mainService.gameGenreList();
		
		model.addAttribute("games", games);
		model.addAttribute("files", files);
		model.addAttribute("genres", genres);
		
		List<GamesDTO> ttGames = mainService.topThreeGameList();
		model.addAttribute("ttGames", ttGames);
		return "game/main";
	}
	
}
