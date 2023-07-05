package com.lms.changemaker.service;

import java.util.List;
import java.util.Optional;

import com.lms.changemaker.entity.Module;
import com.lms.changemaker.entity.School;
import com.lms.changemaker.entity.Quiz;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface QuizService {

	
	Quiz addQuiz(Quiz quiz);
		
    List<Quiz> findAllQuizs();
   
    Optional<Quiz> findQuizById(int quizId);

    public int updateQuizAnswer(int answer,int quizId);
    
    void addNewQuiz(Quiz quiz);

    void editNewQuiz(Quiz quiz);
}
