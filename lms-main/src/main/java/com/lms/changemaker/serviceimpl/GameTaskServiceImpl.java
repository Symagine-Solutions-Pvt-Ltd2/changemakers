package com.lms.changemaker.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.changemaker.repository.GameTaskRepository;
import com.lms.changemaker.entity.GameTask;
import com.lms.changemaker.service.GameTaskService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class GameTaskServiceImpl implements GameTaskService {

	

@Autowired
private GameTaskRepository gameTaskRepository;



@Override
@Transactional
public GameTask addGameTask(GameTask gameTask) {
	return gameTaskRepository.save(gameTask);
}

@Override
public List<GameTask> findAllGameTask() {
	return gameTaskRepository.findAll();
}

@Override
public GameTask findGameTaskByTaskId(int taskId) {    
	return gameTaskRepository.getGameTaskByTaskId(taskId);
}

@Override
public Optional<GameTask> findGameTaskById(int gameTaskId) {
	return gameTaskRepository.findById(gameTaskId);
}






}
