package com.game.review.game.dao;

import java.util.List;

import com.game.review.game.dto.GenreDTO;

public interface AddGenreDAO {

	public void addGenre(GenreDTO genreDTO);
	
	public void delGenre(GenreDTO genreDTO);
	
	public List<GenreDTO> viewGenreAll();
}
