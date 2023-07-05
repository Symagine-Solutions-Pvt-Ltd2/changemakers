package com.lms.changemaker.service;

import com.lms.changemaker.entity.MaxStudentLimitProgram;

import java.util.List;

public interface MaxStudentLimitProgramService {

    MaxStudentLimitProgram addMaxStudentLimitProgram(MaxStudentLimitProgram maxStudentLimitProgram);

    List<MaxStudentLimitProgram> findAllMaxStudentLimitProgram();

    MaxStudentLimitProgram findMaxStudentLimitProgramById(int id);
}
