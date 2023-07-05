package com.lms.changemaker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lms.changemaker.entity.ProgramAssigment;

import javax.transaction.Transactional;


@Repository
public interface ProgramAssignmentRepository extends JpaRepository<ProgramAssigment, Integer> {

	
	
	@Query("SELECT u FROM ProgramAssigment u WHERE u.studentId = :studentId")
	public	List<ProgramAssigment> getProgramAssigmentByStudentId(int studentId);
	
	
	@Query("SELECT u FROM ProgramAssigment u WHERE u.userId = :userId")
	public	List<ProgramAssigment> getProgramAssigmentByUserId(int userId);
	
	
	@Query("SELECT u FROM ProgramAssigment u WHERE u.progId = :progId")
	public	List<ProgramAssigment> getProgramAssigmentByProgramId(int progId);

	
	@Modifying
	@Query("UPDATE ProgramAssigment u set u.schoolId =:schoolId  WHERE u.assignId =:assignId")
	public int updateProgramAssignToSchool(@Param("assignId") int assignId,@Param("schoolId") int schoolId);


	@Modifying
	@Query("UPDATE ProgramAssigment u set u.schoolId =:schoolId  WHERE u.userId =:userId")
	public int updateProgramAssignByUserId(@Param("schoolId") int schoolId,@Param("userId") int userId);

	@Modifying
	@Query("UPDATE ProgramAssigment u set u.schoolId =:schoolId  WHERE u.userId =:userId and u.progId =:progId")
	public int assignProgramToSchool(@Param("schoolId") int schoolId,@Param("userId") int userId,@Param("progId") int progId);

	@Modifying
	@Query("UPDATE ProgramAssigment u set u.schoolId =:schoolId,u.studentId =:studentId  WHERE u.userId =:userId and u.progId =:progId")
	public int updateAssignProgramToStudent(@Param("studentId") int studentId,@Param("schoolId") int schoolId,@Param("userId") int userId,@Param("progId") int progId);


	@Modifying
	@Query("UPDATE ProgramAssigment u set u.studentId =:studentId, u.startDate= :startDate  WHERE u.userId =:userId and u.schoolId =:schoolId")
	public int updateProgramAssignByUserIdSchoolId(@Param("studentId") int studentId, @Param("schoolId") int schoolId, @Param("userId") int userId,@Param("startDate") String startDate);

	@Modifying                                                                                       
	@Query("UPDATE ProgramAssigment u set u.studentId =:studentId  WHERE u.assignId =:assignId")
	public int updateProgramAssignToStudent(@Param("assignId") int assignId,@Param("studentId") int studentId);
	
	
	@Query("SELECT u FROM ProgramAssigment u WHERE u.progId =:progId and u.studentId =:studentId")
	public int getModAssigmentByStuMod(@Param("progId") int progId,@Param("studentId") int studentId);


	@Query("SELECT u FROM ProgramAssigment u WHERE u.userId =:userId and u.progId =:progId")
	 ProgramAssigment getModAssigmentByProgUserId(@Param("userId") int userId,@Param("progId") int progId);

	@Query("SELECT u FROM ProgramAssigment u WHERE u.userId =:userId and u.schoolId =:schoolId")
	List<ProgramAssigment> getModAssigmentBySchoolIdUserId(@Param("userId") int userId,@Param("schoolId") int schoolId);
	

	@Modifying
	@Query("delete from ProgramAssigment b where b.userId in (:useridList)")
	void deleteByProgAssign(List<Integer> useridList);
	
}
