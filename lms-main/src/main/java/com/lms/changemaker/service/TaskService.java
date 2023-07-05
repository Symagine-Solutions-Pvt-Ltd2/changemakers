package com.lms.changemaker.service;

import java.util.List;

import com.lms.changemaker.entity.ModuleTraining;
import com.lms.changemaker.entity.Task;


public interface TaskService {

	
	Task addTask(Task task);
		
    List<Task> findAllTasks();
   
    
    List<Task> getTasksByModule(int moduleId);
    
    ModuleTraining  getTaskstraining(int moduleId);
}
