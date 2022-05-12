package com.game.review.game.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.review.game.dto.GameFilesDTO;
import com.game.review.game.dto.GamesDTO;
import com.game.review.game.dto.GenreDTO;
import com.game.review.game.dto.SpecDTO;

@Repository
public class GameDAOImpl implements GameDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int insertGame(GamesDTO gamesDTO) {
		return sqlSessionTemplate.insert("insertGame", gamesDTO);

	}

	@Override
	public int insertMainFile(GameFilesDTO gamefilesDTO) {
		return sqlSessionTemplate.insert("insertMainFile", gamefilesDTO);

	}

	@Override
	public int insertGr(GenreDTO genreDTO) {
		return sqlSessionTemplate.insert("insertGr", genreDTO);

	}

	@Override
	public int insertSpec(SpecDTO specDTO) {
		return sqlSessionTemplate.insert("insertSpec", specDTO);

	}

	@Override
	public int insertSlideFile(GameFilesDTO gamefilesDTO) {
		return sqlSessionTemplate.insert("insertSlideFile", gamefilesDTO);

	}

	@Override
	public List<GenreDTO> selectGenres() {

		return sqlSessionTemplate.selectList("selectGenres");
	}

	@Override
	public List<GenreDTO> selectGenreListOfGame() {
		return sqlSessionTemplate.selectList("selectGenreListOfGame");
	}

	@Override
	public List<GameFilesDTO> selectGameFilesList() {

		return sqlSessionTemplate.selectList("selectGameFilesList");
	}

	@Override
	public List<GamesDTO> selectGameList() {

		return sqlSessionTemplate.selectList("selectGameList");
	}

	@Override
	public int deleteGame(Long gNum) {
		return sqlSessionTemplate.delete("deleteGame", gNum);

	}

	@Override
	public int countGameCodeForDup(String gCode) {
		return sqlSessionTemplate.selectOne("countGameCodeForDup", gCode);
	}
	
	@Override
	public int countGameNameForDup(String gName) {
		return sqlSessionTemplate.selectOne("countGameNameForDup", gName);
	}

	@Override
	public GamesDTO selectGameBySeq(Long gNum) {
		return sqlSessionTemplate.selectOne("selectGameBySeq", gNum);
	}

	@Override
	public SpecDTO selectSpecBySeq(Long gNum) {
		return sqlSessionTemplate.selectOne("selectSpecBySeq", gNum);
	}

	@Override
	public List<GenreDTO> selectGenresBySeq(Long gNum) {
		return sqlSessionTemplate.selectList("selectGenresBySeq", gNum);
	}

	@Override
	public List<GameFilesDTO> selectGameFilesBySeq(Long gNum) {
		return sqlSessionTemplate.selectList("selectGameFilesBySeq", gNum);
	}

	@Override
	public int upinsertSpec(SpecDTO specDTO) {
		return sqlSessionTemplate.insert("upinsertSpec", specDTO);
	}

	@Override
	public int updateGame(GamesDTO gamesDTO) {
		return sqlSessionTemplate.update("updateGame", gamesDTO);
	}

	@Override
	public int updateSpec(SpecDTO specDTO) {
		return sqlSessionTemplate.update("updateSpec", specDTO);
	}

	@Override
	public int updateGenre(GenreDTO genreDTO) {
		return sqlSessionTemplate.update("updateGenre", genreDTO);
	}

	@Override
	public int deleteGenre(Long gNum) {
		return sqlSessionTemplate.delete("deleteGenre", gNum);
	}

	@Override
	public int countGameCodeForDupExItSelf(GamesDTO gamesDTO) {
		return sqlSessionTemplate.selectOne("countGameCodeForDupExItSelf", gamesDTO);
	}

	@Override
	public int countGameNameForDupExItSelf(GamesDTO gamesDTO) {
		return sqlSessionTemplate.selectOne("countGameNameForDupExItSelf", gamesDTO);
	}

	@Override
	public int countGame() {
		return sqlSessionTemplate.selectOne("countGame");
	}

	@Override
	public List<GamesDTO> selectTopThreeGameList() {
		return sqlSessionTemplate.selectList("selectTopThreeGameList");
	}

	@Override
	public GameFilesDTO selectMainFile(GameFilesDTO gameFilesDTO) {
		return sqlSessionTemplate.selectOne("selectMainFile", gameFilesDTO);
	}

	@Override
	public GameFilesDTO selectSliderFile(Long gfNum) {
		return sqlSessionTemplate.selectOne("selectSliderFile", gfNum);
	}

	@Override
	public int deleteMainFile(GameFilesDTO gameFilesDTO) {
		return sqlSessionTemplate.delete("deleteMainFile", gameFilesDTO);
	}

	@Override
	public int updateMainFile(GameFilesDTO gamefilesDTO) {
		return sqlSessionTemplate.update("updateMainFile", gamefilesDTO);
		
	}

	@Override
	public int updateSlideFile(GameFilesDTO gamefilesDTO) {
		return sqlSessionTemplate.update("updateSlideFile", gamefilesDTO);
	}

	@Override
	public int deleteSlideFile(GameFilesDTO gameFilesDTO) {
		return sqlSessionTemplate.delete("deleteSlideFile", gameFilesDTO);
	}
	
	

}
