package com.lms.changemaker.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lms.changemaker.repository.TrainingDetailRepository;
import com.lms.changemaker.entity.TrainingDetail;
import com.lms.changemaker.service.TrainingDetailService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TrainingDetailServiceImpl implements TrainingDetailService {

	

@Autowired
private TrainingDetailRepository trainingDetailRepository;



@Override
@Transactional
public TrainingDetail addTraining(TrainingDetail trainingDetail) {
	return trainingDetailRepository.save(trainingDetail);
}


@Override
public List<TrainingDetail> findAllTrainings() {
	return trainingDetailRepository.findAll();
}


@Override
public List<TrainingDetail> getCompletedTrainingDetailByStudent(int studentId) {

	return trainingDetailRepository.getCompletedTrainingDetailByStudent(studentId);
}




}
