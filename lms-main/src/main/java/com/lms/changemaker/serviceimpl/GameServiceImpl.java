package com.lms.changemaker.serviceimpl;

import java.util.List;
import java.util.Optional;

import com.lms.changemaker.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lms.changemaker.repository.GameRepository;
import com.lms.changemaker.service.GameService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class GameServiceImpl implements GameService {

	

@Autowired
private GameRepository gameRepository;

@Override
@Transactional
public Game addGame(Game game) {
	return gameRepository.save(game);
}

@Override
public List<Game> findAllGames() {

	return gameRepository.findAll();
}

@Override
public Optional<Game> findGameById(int gameId) {
	return gameRepository.findById(gameId);
}


	@Override
	public void editVideo(Game game) {
		Game game1 = findGameById(game.getGameId()).get();
		game1.setGameType(game.getGameType());
		addGame(game1);
	}

}
