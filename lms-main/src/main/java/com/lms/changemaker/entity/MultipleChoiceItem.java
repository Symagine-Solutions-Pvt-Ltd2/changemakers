package com.lms.changemaker.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="multiple_choice_items")
@NoArgsConstructor
@Data
public class MultipleChoiceItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="choice_id")
	private int choiceId;
	
	private String option;
	private String image;
	
	@Column(name="quiz_game_id")
	private int quizGameId;

	public MultipleChoiceItem(String option, String image, int quizGameId) {
		this.option = option;
		this.image = image;
		this.quizGameId = quizGameId;
	}
}