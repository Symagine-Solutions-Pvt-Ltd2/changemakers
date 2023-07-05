package com.lms.changemaker.repository;

import java.util.List;

import com.lms.changemaker.entity.ProgramProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lms.changemaker.entity.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Integer> {
	
	
	@Query("SELECT u FROM School u WHERE u.contactEmail = :email")
	public	School findSchoolByEmail(@Param("email") String email);

	
	@Query("SELECT u FROM School u WHERE u.userId = :userId")
	public	List<School> findSchoolByUserId(@Param("userId") int userId);

	
	@Modifying
	@Query("UPDATE School u set u.token =:token  WHERE u.contactEmail =:email")
	public int saveSchoolToken(@Param("token") String token,@Param("email") String email);

	
	@Modifying
	@Query("UPDATE School u set u.studentRegisterCount =:studentRegisterCount  WHERE u.schoolId =:schoolId")
	public int saveStudentCount(@Param("studentRegisterCount") int studentRegisterCount,@Param("schoolId") int schoolId);
	
	@Query("SELECT u FROM School u WHERE u.schoolCode = :code")
	public School findSchoolByCode(@Param("code") String code);


	@Query("select pr from ProgramProgress pr where pr.studentId in (select st.studentId from Student st where st.studentSchoolId = :studentSchoolId)")
	public List<ProgramProgress> getSchoolProgressByModule(@Param("studentSchoolId") int studentSchoolId);

}
