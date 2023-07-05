package com.lms.changemaker.serviceimpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lms.changemaker.repository.QuizTaskRepository;
import com.lms.changemaker.entity.QuizTask;
import com.lms.changemaker.service.QuizTaskService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class QuizTaskServiceImpl implements QuizTaskService {

	

@Autowired
private QuizTaskRepository quizTaskRepository;



@Override
@Transactional
public QuizTask addQuizTask(QuizTask quizTask) {
	return quizTaskRepository.save(quizTask);
}

@Override
public List<QuizTask> findAllQuizTask() {
	return quizTaskRepository.findAll();
}

@Override
public List<QuizTask> findQuizTaskByTaskId(int taskId) {    
	return quizTaskRepository.getQuizTaskByTaskId(taskId);
}

@Override
public Optional<QuizTask> findQuizTaskById(int quizTaskId) {
	return quizTaskRepository.findById(quizTaskId);
}






}
