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
@Table(name="game_task")
@Data
@NoArgsConstructor
public class GameTask {
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	@Column(name="game_task_id")
	private int gameTaskId ;

	@Column(name="game_id")
	private int game;
	
	@Column(name="task_id")
	private int taskId;

	

	@OneToOne(
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
 	  @JoinColumn(name = "game_id",insertable = false,updatable = false)
	    private Game games = new Game();
	
	
	
	public GameTask(int game, int taskId, Game games) {
		super();
		this.game = game;
		this.taskId = taskId;
		this.games = games;
	}



}
