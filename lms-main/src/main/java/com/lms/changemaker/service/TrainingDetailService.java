package com.lms.changemaker.service;

import java.util.List;
import com.lms.changemaker.entity.Module;
import com.lms.changemaker.entity.School;
import com.lms.changemaker.entity.TrainingDetail;

public interface TrainingDetailService {

	
	TrainingDetail addTraining(TrainingDetail trainingDetail);
		
    List<TrainingDetail> findAllTrainings();
    
    List<TrainingDetail> getCompletedTrainingDetailByStudent(int studentId);

}
