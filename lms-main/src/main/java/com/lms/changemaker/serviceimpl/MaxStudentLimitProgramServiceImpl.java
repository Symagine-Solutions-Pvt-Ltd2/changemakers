package com.lms.changemaker.serviceimpl;

import com.lms.changemaker.entity.MaxStudentLimitProgram;
import com.lms.changemaker.repository.GameRepository;
import com.lms.changemaker.repository.MaxStudentLimitProgramRepo;
import com.lms.changemaker.service.MaxStudentLimitProgramService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MaxStudentLimitProgramServiceImpl implements MaxStudentLimitProgramService {


    @Autowired
    private MaxStudentLimitProgramRepo maxStudentLimitProgramRepo;

    @Override
    public MaxStudentLimitProgram addMaxStudentLimitProgram(MaxStudentLimitProgram maxStudentLimitProgram) {
        return maxStudentLimitProgramRepo.save(maxStudentLimitProgram);
    }

    @Override
    public List<MaxStudentLimitProgram> findAllMaxStudentLimitProgram() {
        return maxStudentLimitProgramRepo.findAll();
    }

    @Override
    public MaxStudentLimitProgram findMaxStudentLimitProgramById(int id) {
        return maxStudentLimitProgramRepo.findById(id).get();
    }

    
}
