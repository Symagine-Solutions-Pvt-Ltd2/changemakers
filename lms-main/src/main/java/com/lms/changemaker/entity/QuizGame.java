package com.lms.changemaker.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "quiz_game")
@Data
@NoArgsConstructor
public class QuizGame implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="quiz_game_id")
	private int quizGameId;

	@Column(name="question")
	private String question;
	
	@Column(name="answer")
	private String answer;

	@Column(name="score")
	private int score;
	
	@OneToMany(
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
 	  @JoinColumn(name = "quiz_game_id")
	    public List<MultipleChoiceItem> multipleChoiceItems = new ArrayList<>();
	
	@Transient
	private String option1,option2,option3,option4;


}