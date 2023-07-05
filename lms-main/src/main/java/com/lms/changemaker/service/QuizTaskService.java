package com.lms.changemaker.service;

import java.util.List;
import java.util.Optional;
import com.lms.changemaker.entity.QuizTask;

public interface QuizTaskService {

	
	QuizTask addQuizTask(QuizTask quizTask);
		
    List<QuizTask> findAllQuizTask();
   
    List<QuizTask> findQuizTaskByTaskId(int taskId);
 
    Optional<QuizTask> findQuizTaskById(int quizTaskId);
    
}
