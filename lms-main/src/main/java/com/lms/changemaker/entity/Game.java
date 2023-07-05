package com.lms.changemaker.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "game")
@Data
@NoArgsConstructor
public class Game implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	@Column(name="game_id")
	private int gameId;

	@Column(name="game_type")
	private String gameType;

	
}