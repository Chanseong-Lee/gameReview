package com.game.review.game.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.review.game.dto.GenreDTO;

@Repository
public class AddGenreDAOImpl implements AddGenreDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void addGenre(GenreDTO genreDTO) {
		sqlSessionTemplate.insert("addGenre", genreDTO);

	}

	@Override
	public void delGenre(GenreDTO genreDTO) {
		sqlSessionTemplate.delete("delGenre", genreDTO);

	}

	@Override
	public List<GenreDTO> viewGenreAll() {
		return sqlSessionTemplate.selectList("viewGenreAll");
	}

}
