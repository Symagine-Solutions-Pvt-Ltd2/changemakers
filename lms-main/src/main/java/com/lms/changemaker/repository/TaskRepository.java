package com.lms.changemaker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.lms.changemaker.entity.Task;



public interface TaskRepository extends JpaRepository<Task, Integer> {

	
	@Query("SELECT u FROM Task u WHERE u.moduleId = :moduleId")
	public	List<Task> getTaskByModule(int moduleId);
	
	
	
}
