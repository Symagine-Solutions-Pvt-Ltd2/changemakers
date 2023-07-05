package com.lms.changemaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lms.changemaker.entity.Teacher;


@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

	
	@Query("SELECT u FROM Teacher u WHERE u.teacherCode = :code")
	Teacher findTeacherByCode(@Param("code") String code);
	
	
	@Query("SELECT u FROM Teacher u WHERE u.teacherEmail = :email")
	public	Teacher findTeacherByEmail(@Param("email") String email);
	
	@Modifying
	@Query(value="UPDATE Teacher u set u.token =:token  WHERE u.teacherEmail =:email")
	public int saveTeacherToken(@Param("token") String token,@Param("email") String email);
}
