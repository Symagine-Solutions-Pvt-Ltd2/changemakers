package com.lms.changemaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.changemaker.entity.Game;


@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

	
	
	

	
}
