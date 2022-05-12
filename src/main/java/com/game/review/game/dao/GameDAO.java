package com.game.review.game.dao;

import java.util.List;
import java.util.Map;

import com.game.review.game.dto.GameFilesDTO;
import com.game.review.game.dto.GamesDTO;
import com.game.review.game.dto.GenreDTO;
import com.game.review.game.dto.SpecDTO;

public interface GameDAO {
	public int insertGame(GamesDTO gamesDTO);

	public int insertGr(GenreDTO genreDTO);

	public int insertMainFile(GameFilesDTO gamefilesDTO);

	public int insertSpec(SpecDTO specDTO);

	public int insertSlideFile(GameFilesDTO gamefilesDTO);

	public int deleteGame(Long gNum);
	
	//전체 장르 목록
	public List<GenreDTO> selectGenres();
	
	//한 게임이 갖고 있는 게임목록
	public List<GenreDTO> selectGenreListOfGame();
	
	//게임이미지목록
	public List<GameFilesDTO> selectGameFilesList();
	
	//top3 게임리스트
	public List<GamesDTO> selectTopThreeGameList();
	
	//게임목록
	public List<GamesDTO> selectGameList();
	
	//게임등록시 중복체크용
	public int countGameNameForDup(String gName);
	public int countGameCodeForDupExItSelf(GamesDTO gamesDTO);
	public int countGameCodeForDup(String gCode);
	public int countGameNameForDupExItSelf(GamesDTO gamesDTO);
	
	//게임 디테일
	public GamesDTO selectGameBySeq(Long gNum);

	public SpecDTO selectSpecBySeq(Long gNum);
	
	public List<GenreDTO> selectGenresBySeq(Long gNum);
	public List<GameFilesDTO> selectGameFilesBySeq(Long gNum);
	
	//수정
	public int upinsertSpec(SpecDTO specDTO);
	
	public int updateGame(GamesDTO gamesDTO);
	
	public int updateSpec(SpecDTO specDTO);
	
	public int updateGenre(GenreDTO genreDTO);
	
	public int deleteGenre(Long gNum);
	
	//????
	public GameFilesDTO selectMainFile(GameFilesDTO gameFilesDTO);

	public GameFilesDTO selectSliderFile(Long gfNum);
	
	public int deleteMainFile(GameFilesDTO gameFilesDTO);

	public int updateMainFile(GameFilesDTO gamefilesDTO);

	public int updateSlideFile(GameFilesDTO gamefilesDTO);

	public int deleteSlideFile(GameFilesDTO gameFilesDTO);
	
	//전체 게임수
	public int countGame();

}
