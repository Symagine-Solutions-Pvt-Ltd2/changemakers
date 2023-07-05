package com.lms.changemaker.service;

import java.util.List;
import java.util.Optional;

import com.lms.changemaker.entity.Student;
import com.lms.changemaker.entity.Teacher;

public interface TeacherService {

	Teacher insertTeacher(Teacher teacher);
	
	List<Teacher> listAllTeachers();

	Optional<Teacher> findTeacherById(int teacherId);
	
	Teacher findTeacherByCode(String code);

	Teacher findTeacherByEmail(String email);
	
    void login(Teacher teacher);
	 
	int saveTeacherToken(String token,String email);
}
