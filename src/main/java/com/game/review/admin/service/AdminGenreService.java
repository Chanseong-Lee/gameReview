package com.game.review.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.game.review.game.command.AddGenreCommnad;
import com.game.review.game.dao.AddGenreDAO;
import com.game.review.game.dto.GenreDTO;
import com.game.review.game.exception.AlreadyExistGenreException;

@Service
public class AdminGenreService {
	@Autowired
	private AddGenreDAO addGenreDAO;

	public List<GenreDTO> viewGenreAll() {
		return addGenreDAO.viewGenreAll();
	}

	public void addGenre(AddGenreCommnad ac) {

		List<GenreDTO> list = addGenreDAO.viewGenreAll();
		boolean isDup = false;
		for (GenreDTO dup : list) {
			isDup = ac.getGenName().equals(dup.getGenName());
			if(isDup == true) {
				break;
			}
		}
		System.out.println("중복임???"+isDup);
		if (!isDup) {
			GenreDTO addGenre = new GenreDTO();
			addGenre.setGenName(ac.getGenName());
			addGenreDAO.addGenre(addGenre);
		} else {
			throw new AlreadyExistGenreException();
		}

	}

	public void delGenre(AddGenreCommnad ac){
		GenreDTO delGenre = new GenreDTO();

		for (int i = 0; i < ac.getGenNum().size(); i++) {
			delGenre.setGenNum(Long.parseLong(ac.getGenNum().get(i)));
			if (delGenre != null) {
				addGenreDAO.delGenre(delGenre);
			}
		}
	}
}
