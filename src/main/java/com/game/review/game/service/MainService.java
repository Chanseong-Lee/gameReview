package com.game.review.game.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.review.game.dao.GameDAO;
import com.game.review.game.dto.GameFilesDTO;
import com.game.review.game.dto.GamesDTO;
import com.game.review.game.dto.GenreDTO;

@Service
public class MainService {
	
	@Autowired
	private GameDAO gameDAO;
	
	public List<GamesDTO> gameList() {
		return gameDAO.selectGameList();
	}
	
	public List<GameFilesDTO> gameFileList(){
		return gameDAO.selectGameFilesList();
	}
	
	public List<GenreDTO> gameGenreList(){
		return gameDAO.selectGenreListOfGame();
	}
	
	public List<GamesDTO> topThreeGameList() {
		return gameDAO.selectTopThreeGameList();
	}
	
	public List<GameFilesDTO> topThreeGameFileList(Long gNum){
		return gameDAO.selectGameFilesBySeq(gNum);
	}
	
	public List<GenreDTO> topThreeGameGenreList(Long gNum){
		return gameDAO.selectGenresBySeq(gNum);
	}
	
	
}
