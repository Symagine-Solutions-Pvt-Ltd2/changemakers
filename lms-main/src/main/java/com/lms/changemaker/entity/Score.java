package com.lms.changemaker.entity;

import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import javax.persistence.*;



@Entity
@DynamicUpdate
public class Score implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	@Column(name="score_id")
	private int scoreId;

	@Column(name="module_id")
	private int moduleId;

	@Column(name="score_game")
	private int scoreGame;

	@Column(name="score_quiz")
	private int scoreQuiz;

	@Column(name="student_id")
	private int studentId;

	
	
	
	
	public Score(int moduleId, int studentId, int scoreGame, int scoreQuiz) {
		this.moduleId = moduleId;
		this.scoreGame = scoreGame;
		this.scoreQuiz = scoreQuiz;
		this.studentId = studentId;
	}

	public Score() {
	}

	public int getScoreId() {
		return this.scoreId;
	}

	public void setScoreId(int scoreId) {
		this.scoreId = scoreId;
	}

	public int getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public int getScoreGame() {
		return this.scoreGame;
	}

	public void setScoreGame(int scoreGame) {
		this.scoreGame = scoreGame;
	}

	public int getScoreQuiz() {
		return this.scoreQuiz;
	}

	public void setScoreQuiz(int scoreQuiz) {
		this.scoreQuiz = scoreQuiz;
	}

	public int getStudentId() {
		return this.studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

}