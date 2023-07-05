package com.lms.changemaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.lms.changemaker.entity.VideoTask;


@Repository
public interface VideoTaskRepository extends JpaRepository<VideoTask, Integer> {

	
	@Query("SELECT u FROM VideoTask u WHERE u.taskId = :taskId")
	public	VideoTask getVideoTaskByTaskId(int taskId);
	
}
