package com.lms.changemaker.service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.validation.Valid;

import com.lms.changemaker.entity.ProgramAssignedDetail;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lms.changemaker.entity.Program;


public interface ProgramService {

	
	Program addProgram(Program program);
		
    List<Program> findAllPrograms();
   
    Optional<Program> findProgramById(int programId);

	String addnewProgram(MultipartFile multipartProgramFile, @Valid Program addProgramForm);

    public List<ProgramAssignedDetail> getAssignedProgramDetails(int userId, int schoolId);

    public List<Program> getAssignedProgramDetails2(int userId, int schoolId);


    public List<ProgramAssignedDetail> getAssignedProgramDetailsByUserID(int userId);

    public List<ProgramAssignedDetail> getAssignedProgramDetailsByStudentId(@Param("studentId") int studentId, @Param("userId") int userId, @Param("schoolId") int schoolId);

    List<ProgramAssignedDetail> getProgramAssigntoStudent(int studentId, int userId, int studentSchoolId);

    String editProgram(MultipartFile multipartProgramFile, @Valid Program addProgramForm);
}
