package com.game.review.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.game.review.admin.service.AdminGenreService;
import com.game.review.game.command.AddGenreCommnad;
import com.game.review.game.exception.AlreadyExistGenreException;
import com.game.review.game.exception.NoCheckGenreException;
import com.game.review.game.validate.AddGenreValidator;

@Controller
public class AdminGenreController {
	private static final Logger logger = LoggerFactory.getLogger(AdminGenreController.class);

	@Autowired
	AdminGenreService addGenreService;

	@RequestMapping(value = "/admin/game/addGenre", method = RequestMethod.GET)
	public String goAddGenre(@ModelAttribute("addGenre") AddGenreCommnad addgenreCommand, Model model) {
		model.addAttribute("genreList", addGenreService.viewGenreAll());

		return "admin/game/addGenre";
	}

	@RequestMapping(value = "/admin/game/addGenre", method = RequestMethod.POST)
	public String addGenre(@ModelAttribute("addGenre") AddGenreCommnad addgenreCommand, Model model, Errors errors) {
		model.addAttribute("genreList", addGenreService.viewGenreAll());
		new AddGenreValidator().validate(addgenreCommand, errors);
		if (errors.hasErrors()) {
			return "admin/game/addGenre";
		}
		
		try {
			addGenreService.addGenre(addgenreCommand);
			
		}catch(AlreadyExistGenreException e) {
			logger.error("장르명 중복");
			errors.rejectValue("genName", "dupGenreName");
			return "admin/game/addGenre";
		}
		return "redirect:/admin/game/addGenre";

	}

	@RequestMapping(value = "/admin/game/delGenre", method = RequestMethod.GET)
	public String godelGenre(@ModelAttribute("delGenre") AddGenreCommnad addgenreCommand, Model model) {
		model.addAttribute("genreList", addGenreService.viewGenreAll());

		return "admin/game/delGenre";
	}

	@RequestMapping(value = "/admin/game/delGenre", method = RequestMethod.POST)
	public String delGenre(@ModelAttribute("delGenre") AddGenreCommnad addgenreCommand, Model model) throws NoCheckGenreException {
		try {
			model.addAttribute("genreList", addGenreService.viewGenreAll());
			addGenreService.delGenre(addgenreCommand);
			
		} catch (Exception e) {
			
			return "exceptions/noCheckDelGenre";
		}
		return "redirect:/admin/game/delGenre";
	}

//	@ExceptionHandler(DuplicateKeyException.class)
//	public String dupName() {
//		return "exceptions/dupNameError";
//	}

}
