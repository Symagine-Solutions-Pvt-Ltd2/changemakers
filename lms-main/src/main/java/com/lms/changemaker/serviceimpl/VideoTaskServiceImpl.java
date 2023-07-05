package com.lms.changemaker.serviceimpl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lms.changemaker.repository.VideoTaskRepository;
import com.lms.changemaker.entity.VideoTask;
import com.lms.changemaker.service.VideoTaskService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class VideoTaskServiceImpl implements VideoTaskService {

	

@Autowired
private VideoTaskRepository videoTaskRepository;

@Autowired
private EntityManagerFactory entityManagerFactory;


@Override
@Transactional
public VideoTask addVideoTask(VideoTask videoTask) {
	return videoTaskRepository.save(videoTask);
}

@Override
public List<VideoTask> findAllVideoTask() {
	return videoTaskRepository.findAll();
}

@Override
public VideoTask findVideoTaskByTaskId(int taskId) {    
	return videoTaskRepository.getVideoTaskByTaskId(taskId);
}

@Override
public Optional<VideoTask> findVideoTaskById(int videoTaskId) {
	return videoTaskRepository.findById(videoTaskId);
}






}
