package com.lms.changemaker.serviceimpl;


import com.lms.changemaker.entity.QuizGame;
import com.lms.changemaker.repository.QuizGameRepository;
import com.lms.changemaker.service.QuizGameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class QuizGameServiceImpl implements QuizGameService {

	

@Autowired
private QuizGameRepository quizGameRepository;

@Override
@Transactional
public QuizGame addQuizGame(QuizGame quiz) {
	return quizGameRepository.save(quiz);
}

@Override
public List<QuizGame> findAllQuizGames() {
	return quizGameRepository.findAll();
}

@Override
public QuizGame findQuizGameById(int quizId) {
	return quizGameRepository.findById(quizId).get();
}

	@Override
	public int updateQuizGameAnswer(String answer, int quizId) {
		return quizGameRepository.updateQuizAnswer(answer, quizId);
	}




	
	

}
