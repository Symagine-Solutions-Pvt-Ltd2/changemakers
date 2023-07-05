package com.lms.changemaker.service;

import java.util.List;
import java.util.Optional;

import com.lms.changemaker.entity.ProgramProgress;
import org.springframework.transaction.annotation.Transactional;

public interface ProgramProgressService {

	
	ProgramProgress addProgramProgressProg(ProgramProgress programProgress);
		
    List<ProgramProgress> findAllProgramProgress();
   
   	List<ProgramProgress> getProgramProgressByModuleId(int moduleId);

	List<ProgramProgress> getProgramProgressByProgramId(int progId);
 
	List<ProgramProgress> getProgramProgressByStudentId(int studentId);

	List<ProgramProgress> getProgramProgressByStudentIdModuleId(int studentId,int moduleId);
	
    Optional<ProgramProgress> findProgramProgressById(int taskProgressId);

    @Transactional
    int saveTaskProgress(int taskId, int studentId, double progress);

    ProgramProgress updateorinsertTaskProgress(int studentId, int progId, int moduleId, int taskId, double progress);

	ProgramProgress findProgramProgressByTaskId(int taskId);

	ProgramProgress getProgramProgressByStudentIdTaskId(int studentId, int taskId);



	List<ProgramProgress> getProgramProgressByStuProg(int progId, int studentId, double progress);
}
