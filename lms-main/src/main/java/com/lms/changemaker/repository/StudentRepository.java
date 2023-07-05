package com.lms.changemaker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lms.changemaker.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	@Query("SELECT u FROM Student u WHERE u.studentEmail = :email")
	public	Student findStudentByEmail(@Param("email") String email);

	


	@Modifying
	@Query(value="UPDATE Student u set u.studentToken =:token  WHERE u.studentEmail =:email")
	public int saveStudentToken(@Param("token") String token,@Param("email") String email);



	@Query("SELECT u FROM Student u WHERE u.studentSchoolId = :schoolId")
	public List<Student> findStudentBySchoolId(@Param("schoolId") int schoolId);
	
	
}
