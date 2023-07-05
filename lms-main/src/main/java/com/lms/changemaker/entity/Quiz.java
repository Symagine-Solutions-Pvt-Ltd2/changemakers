package com.lms.changemaker.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;


@Entity
@Table(name = "quiz")
@Data
@NoArgsConstructor
@DynamicInsert
public class Quiz implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	@Column(name="quiz_id")
	private int quizId;

	@Column(name="question")
	private String question;
	
	@Column(name="answer")
	private int answer; 

	@Column(name="score")
	private int score;
	
	@OneToMany(
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
 	  @JoinColumn(name = "quiz_id")
	    public List<QuizOption> quizOptions = new ArrayList<>();

	@Transient
	private List<String> listOptions=new ArrayList<>();

	@Transient
	private String ans;

	@Transient
	private String answerNumber;

	@Transient
	private String option1,option2,option3,option4;


}