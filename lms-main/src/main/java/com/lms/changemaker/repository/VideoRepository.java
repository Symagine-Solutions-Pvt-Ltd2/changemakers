package com.lms.changemaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lms.changemaker.entity.Video;


@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {
    
	

	
}
