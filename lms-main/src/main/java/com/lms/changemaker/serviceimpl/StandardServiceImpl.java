package com.lms.changemaker.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.changemaker.repository.StandardRepository;
import com.lms.changemaker.entity.Standard;
import com.lms.changemaker.service.StandardService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class StandardServiceImpl implements StandardService {

	

@Autowired
private StandardRepository standardRepository;

@Override
@Transactional
public Standard addStandard(Standard standard) {
	return standardRepository.save(standard);
}

@Override
public List<Standard> findAllStandards() {

	return standardRepository.findAll();
}

@Override
public Optional<Standard> findStandardById(int standardId) {
	return standardRepository.findById(standardId);
}







}
