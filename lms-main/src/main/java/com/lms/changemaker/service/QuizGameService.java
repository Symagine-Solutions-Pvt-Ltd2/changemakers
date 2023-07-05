package com.lms.changemaker.service;

import com.lms.changemaker.entity.QuizGame;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface QuizGameService {

	
	QuizGame addQuizGame(QuizGame quizGame);
		
    List<QuizGame> findAllQuizGames();
   
    QuizGame findQuizGameById(int quizGameId);

     int updateQuizGameAnswer(String answer,int quizGameId);
    
}
