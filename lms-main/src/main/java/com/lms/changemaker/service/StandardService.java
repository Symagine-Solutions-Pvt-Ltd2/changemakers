package com.lms.changemaker.service;

import java.util.List;
import java.util.Optional;

import com.lms.changemaker.entity.Standard;

public interface StandardService {

	
	Standard addStandard(Standard standard);
	
    List<Standard> findAllStandards();
   
    Optional<Standard> findStandardById(int standardId);
	
}
