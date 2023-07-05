package com.lms.changemaker.entity;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name="quiz_option")
public class QuizOption implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	@Column(name="quiz_option_id")
	private int quizOptionId;

	@Column(name="quiz_choice")
	private String quizChoice;

	

	@Column(name="quiz_id")
	private int quizId;

	public QuizOption(String quizChoice, int quizId) {
		this.quizChoice = quizChoice;
		this.quizId = quizId;
	}

	public QuizOption() {
	}

	public int getQuizOptionId() {
		return this.quizOptionId;
	}

	public void setQuizOptionId(int quizOptionId) {
		this.quizOptionId = quizOptionId;
	}

	public String getQuizChoice() {
		return this.quizChoice;
	}

	public void setQuizChoice(String quizChoice) {
		this.quizChoice = quizChoice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



}