package com.lms.changemaker.service;

import java.util.List;
import java.util.Optional;

import com.lms.changemaker.entity.ProgramAssigment;
import com.lms.changemaker.entity.ProgramAssignedDetail;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProgramAssigmentService {



	void  saveAssignProgress(ProgramAssigment programAssigment, int taskId, double progress, int studentId, int moduleId, int progId);

	ProgramAssigment saveProgramAssigment(ProgramAssigment programAssigment);
		
    List<ProgramAssigment> findAllProgramAssigment();
   
   	List<ProgramAssigment> getProgramAssigmentByStudentId(int studentId);


    
	Optional<ProgramAssigment> findProgramAssigmentById(int assignId);
    
    //int saveProgramProgress(int moduleId,int studentId, double progress);

	//ProgramAssigment updateAssignProgramProgress(int moduleId, int studentId, double progress);
	
	int updateProgramAssignToSchool(int assignId,int schoolId);
	
	int updateProgramAssignToStudent(int assignId,int studentId);

	List<ProgramAssigment> getProgramAssigmentByUserId(int userId);

	 int updateProgramAssignByUserId(int schoolId,int userId);
	 int updateProgramAssignByUserIdSchoolId(int studentId, int schoolId,int userId);
	List<ProgramAssigment> getModAssigmentBySchoolIdUserId(@Param("userId") int userId, @Param("schoolId") int schoolId);
	void deleteByProgAssign(List<ProgramAssigment> userIds);
	public ProgramAssigment getModAssigmentByProgUserId(int userId, int progId);
	public int assignProgramToSchool(int schoolId,int userId,int progId);

	public int updateAssignProgramToStudent(int studentId,int schoolId,int userId,int progId);


    @Transactional
    List<ProgramAssigment> setProgramColor(List<ProgramAssigment> programAssigments);
}
