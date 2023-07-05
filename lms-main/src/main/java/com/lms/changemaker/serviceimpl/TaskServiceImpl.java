package com.lms.changemaker.serviceimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;

import com.lms.changemaker.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.changemaker.service.TaskService;
import com.lms.changemaker.repository.TaskRepository;

import static com.lms.changemaker.utilities.Utilities.distinctByKey;


@Service
public class TaskServiceImpl implements TaskService {

	Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);	

@Autowired
private TaskRepository taskRepository;

@Autowired
private VideoTaskServiceImpl videoTaskServiceImpl;

@Autowired
private GameTaskServiceImpl gameTaskServiceImpl;

@Autowired
private QuizTaskServiceImpl quizTaskServiceImpl;

@Autowired
private EntityManagerFactory   emf;

@Autowired
private PlasticItemServiceImpl plasticItemServiceImpl;

@Autowired
private QuizGameServiceImpl quizGameServiceImpl;

@Override
@Transactional
public Task addTask(Task task) {
	return taskRepository.save(task);
}

@Override
public List<Task> findAllTasks() {
	return taskRepository.findAll();
}

@Override
public List<Task> getTasksByModule(int moduleId) {
	return taskRepository.getTaskByModule(moduleId);
}

@Override
public ModuleTraining getTaskstraining(int moduleId) {

	System.out.println("moduleId = " + moduleId);
   List<Task> resultList = taskRepository.getTaskByModule(moduleId);
   System.out.println("TaskList :::::::///////"+resultList.toString());

 //  List<ModuleTraining> moduleTrainingList=new ArrayList<ModuleTraining>();
   ModuleTraining moduleTraining=new ModuleTraining();
	for (Task t : resultList) {
		if (t.getTaskType().equalsIgnoreCase("video")) {

			Video video = videoTaskServiceImpl.findVideoTaskByTaskId(t.getTaskId()).getVideo();

			System.out.println("Video :::::::///////" + video.toString());
			if (moduleTraining.getVideoUrl1() != null) {
				moduleTraining.setVideoUrl2(video.getVideoUrlEnglish().trim());
				moduleTraining.setTaskIdVid2(t.getTaskId());
			} else {
				moduleTraining.setVideoUrl1(video.getVideoUrlEnglish().trim());
				moduleTraining.setTaskIdVid(t.getTaskId());
			}


		} else if (t.getTaskType().equalsIgnoreCase("game")) {
			System.out.println(t.getTaskId());
			Game games = gameTaskServiceImpl.findGameTaskByTaskId(t.getTaskId()).getGames();
			moduleTraining.setGame(games);
			moduleTraining.setTaskIdGame(t.getTaskId());
			List<PlasticItem> plasticItem = plasticItemServiceImpl.findPlasticItemByGameId(moduleTraining.getGame().getGameId());
			if(games.getGameId()==1||games.getGameId()==4)
			Collections.shuffle(plasticItem);
			System.out.println(plasticItem.toString());
			moduleTraining.setDragItems(plasticItem);
			moduleTraining.setWeightCalculatorItems(plasticItem);
			moduleTraining.setSelectObjectItems(plasticItem);
			List<PlasticItem> collect = plasticItem.stream().filter(distinctByKey(PlasticItem::getCategoryId)).collect(Collectors.toList());
			if(games.getGameId()==1||games.getGameId()==4)
			Collections.shuffle(collect);
            moduleTraining.setQuizGameList(quizGameServiceImpl.findAllQuizGames());
			moduleTraining.setDropItems(collect);
		} else {

			List<Quiz> quizs = new ArrayList();
			quizTaskServiceImpl.findQuizTaskByTaskId(t.getTaskId()).forEach(new Consumer<QuizTask>() {

				@Override
				public void accept(QuizTask t) {
					quizs.add(t.getQuizs());

				}
			});
			moduleTraining.setQuiz(quizs);
			moduleTraining.setTaskIdQuiz(t.getTaskId());

		}

		System.out.println("moduleTraining :::::::///////" + moduleTraining.toString());
	}
   
    logger.error("An ERROR Message : "+moduleTraining);
	return moduleTraining;
}





}
