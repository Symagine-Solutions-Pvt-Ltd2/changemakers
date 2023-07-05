package com.lms.changemaker.repository;

import com.lms.changemaker.entity.Quiz;
import com.lms.changemaker.entity.QuizGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface QuizGameRepository extends JpaRepository<QuizGame, Integer> {


    @Modifying
    @Query("UPDATE QuizGame u set u.answer =:answer  WHERE u.quizGameId =:quizGameId")
    public int updateQuizAnswer(@Param("answer") String answer, @Param("quizGameId") int quizGameId);
	

	
}
