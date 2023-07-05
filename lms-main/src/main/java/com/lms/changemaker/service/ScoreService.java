package com.lms.changemaker.service;

import java.util.List;

import com.lms.changemaker.entity.ModuleTraining;
import com.lms.changemaker.entity.Score;
import com.lms.changemaker.entity.Student;
import com.lms.changemaker.entity.TrainingDetail;


public interface ScoreService {

	
	Score addScore(Score task);
		
    List<Score> findAllScores();
    
    Score  getScoreByStudentModule(int studentId,int module);




    Score saveStudentModuleScore(int studentId, int moduleId, int scoreQuiz, int scoreGame);

    Score completeModule(int studentId, int moduleId, int score, Student student);

    public	List<Score> getQuizScoreByStudent(int studentId);
}
