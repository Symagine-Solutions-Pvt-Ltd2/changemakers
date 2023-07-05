package com.lms.changemaker.service;

import java.util.List;
import java.util.Optional;

import com.lms.changemaker.entity.Module;
import com.lms.changemaker.entity.School;
import com.lms.changemaker.entity.VideoTask;

public interface VideoTaskService {

	
	VideoTask addVideoTask(VideoTask videoTask);
		
    List<VideoTask> findAllVideoTask();
   
    VideoTask findVideoTaskByTaskId(int taskId);
 
    Optional<VideoTask> findVideoTaskById(int videoTaskId);
    
}
