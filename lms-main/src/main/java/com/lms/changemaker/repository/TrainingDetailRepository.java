package com.lms.changemaker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.lms.changemaker.entity.TrainingDetail;


@Repository
public interface TrainingDetailRepository extends JpaRepository<TrainingDetail, Integer> {

	@Query("SELECT u FROM TrainingDetail u WHERE u.studentId = :studentId and status=1")
	public	List<TrainingDetail> getCompletedTrainingDetailByStudent(int studentId);
	
	
}
