package com.lms.changemaker.service;

import java.util.List;
import java.util.Optional;

import com.lms.changemaker.entity.Module;
import com.lms.changemaker.entity.School;
import com.lms.changemaker.entity.Game;

public interface GameService {

	
	Game addGame(Game game);
		
    List<Game> findAllGames();
   
    Optional<Game> findGameById(int gameId);

    void editVideo(Game game);
}
