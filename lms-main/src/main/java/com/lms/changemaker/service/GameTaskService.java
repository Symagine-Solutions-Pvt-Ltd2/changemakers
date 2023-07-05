package com.lms.changemaker.service;

import java.util.List;
import java.util.Optional;
import com.lms.changemaker.entity.GameTask;

public interface GameTaskService {

	
	GameTask addGameTask(GameTask gameTask);
		
    List<GameTask> findAllGameTask();
   
    GameTask findGameTaskByTaskId(int taskId);
 
    Optional<GameTask> findGameTaskById(int gameTaskId);
    
}
