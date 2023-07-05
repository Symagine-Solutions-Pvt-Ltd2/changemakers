package com.lms.changemaker.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name="quiz_task")
@Data
@NoArgsConstructor
public class QuizTask {
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	@Column(name="quiz_task_id")
	private int quizTaskId ;

	@Column(name="quiz_id")
	private int quiz;
	
	@Column(name="task_id")
	private int taskId;

	
	
	
	@OneToOne(
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
 	  @JoinColumn(name = "quiz_id",insertable = false,updatable = false)
	   private Quiz quizs = new Quiz();
	
	
	
	
	public QuizTask(int quiz, int taskId, Quiz quizs) {
		super();
		this.quiz = quiz;
		this.taskId = taskId;
		this.quizs = quizs;
	}


	
}
