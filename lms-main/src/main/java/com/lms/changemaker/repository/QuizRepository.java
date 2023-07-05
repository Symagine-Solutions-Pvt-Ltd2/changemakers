package com.lms.changemaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.lms.changemaker.entity.Quiz;


@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {


    @Modifying
    @Query("UPDATE Quiz u set u.answer =:answer  WHERE u.quizId =:quizId")
    public int updateQuizAnswer(@Param("answer") int answer, @Param("quizId") int quizId);
	

	
}
