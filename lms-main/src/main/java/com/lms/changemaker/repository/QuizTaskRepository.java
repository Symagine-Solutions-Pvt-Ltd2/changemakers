package com.lms.changemaker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lms.changemaker.entity.QuizTask;


@Repository
public interface QuizTaskRepository extends JpaRepository<QuizTask, Integer> {

	
	@Query("SELECT u FROM QuizTask u WHERE u.taskId = :taskId")
	public	List<QuizTask> getQuizTaskByTaskId(int taskId);
	
}
