package com.lms.changemaker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.lms.changemaker.entity.ProgramProgress;

public interface ProgramProgressRepo extends JpaRepository<ProgramProgress, Integer> {

	
	@Query("SELECT u FROM ProgramProgress u WHERE u.studentId = :studentId")
	public	List<ProgramProgress> getProgramProgressByStudentId(int studentId);

	@Query("SELECT u FROM ProgramProgress u WHERE u.studentId = :studentId and u.taskId=:taskId")
	public	ProgramProgress getProgramProgressByStudentIdTaskId(int studentId,int taskId);

	
	@Query("SELECT u FROM ProgramProgress u WHERE u.progId = :progId")
	public	List<ProgramProgress> getProgramProgressByProgId(int progId);
	

	@Query("SELECT u FROM ProgramProgress u WHERE u.taskId = :taskId")
	public	ProgramProgress getProgramProgressByTaskId(int taskId);
	
	@Query("SELECT u FROM ProgramProgress u WHERE u.moduleId = :moduleId")
	public	List<ProgramProgress> getProgramProgressByModuleId(int moduleId);

	@Query("SELECT u FROM ProgramProgress u WHERE u.moduleId = :moduleId and u.studentId= :studentId")
	public	List<ProgramProgress> getProgramProgressByStudentIdModuleId(int studentId, int moduleId);

	
	@Query("SELECT u FROM ProgramProgress u WHERE u.progId = :progId and u.studentId= :studentId and u.progress= :progress")
	public	List<ProgramProgress> getProgramProgressByStuProg(int progId,int studentId,double progress);

	
	
	@Modifying
	@Query(value="UPDATE ProgramProgress u set u.progress =:progress  WHERE u.taskId = :taskId and u.studentId=:studentId")
	public int saveTaskProgress(int taskId,int studentId,double progress);
	
	
}

