package com.lms.changemaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lms.changemaker.entity.GameTask;


@Repository
public interface GameTaskRepository extends JpaRepository<GameTask, Integer> {

	
	@Query("SELECT u FROM GameTask u WHERE u.taskId = :taskId")
	public	GameTask getGameTaskByTaskId(int taskId);
	
}
