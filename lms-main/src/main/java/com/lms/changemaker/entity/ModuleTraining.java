package com.lms.changemaker.entity;

import java.util.HashMap;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ModuleTraining {

	
	private String videoUrl1;
	private Game game;
	
	private String videoUrl2;
	private List<Quiz> quiz;
	
	private int taskIdVid;
	private int taskIdGame;
	private int taskIdVid2;
	private int taskIdQuiz;
	
	
	private List<PlasticItem> dropItems;
	private List<QuizGame> quizGameList;
	private List<PlasticItem> dragItems;
	private List<PlasticItem> weightCalculatorItems;
	private List<PlasticItem> selectObjectItems;
}
