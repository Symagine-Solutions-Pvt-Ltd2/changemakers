package com.lms.changemaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.changemaker.entity.Standard;


@Repository
public interface StandardRepository extends JpaRepository<Standard, Integer> {

	
	
	

	
}
