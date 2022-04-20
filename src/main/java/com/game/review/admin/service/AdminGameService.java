package com.game.review.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.review.admin.dao.AdminDAO;

@Service
public class AdminGameService {
	@Autowired
	private AdminDAO adminDAO;
}
