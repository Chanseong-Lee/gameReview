package com.game.review.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.review.game.dao.GameDAO;
import com.game.review.game.dto.GamesDTO;

@Service
public class AdminGameDeleteService {
	@Autowired
	private GameDAO gameDAO;

	public void deleteGame(Long gNum) {

		gameDAO.deleteGame(gNum);

	}
}