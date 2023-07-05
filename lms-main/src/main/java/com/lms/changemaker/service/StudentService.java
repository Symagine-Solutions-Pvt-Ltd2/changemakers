package com.lms.changemaker.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.lms.changemaker.entity.*;

public interface StudentService {

	Student insertStudent(Student teacherEntity);
	
	List<Student> listAllStudents();

	Optional<Student> findStudentById(int teacherId);
	
	List<Student> findStudentBySchoolId(int schoolId);
	
	Student findByEmail(String email);
	
	int saveStudentToken(String token,String email);
	
	RegisterStudentResponse registerAndAutoLoginStudent(Student studentForm);
	
	void autologin(Student student);
	


	int registerStudent(Student studentForm, School school);

	List<ProgramAssigment> getModuleList(Student student, int moduleId);

	List<Student> getStudentList(int schoolId);

	int changePassword(@Valid Student studentForm);


    int editStudent(Student studentForm, School schoolLogin);

    String updateGameScore(Student student, SaveGameScoreResponse gameScoreResponse);
    


	String updateGameQuizScore(Student student, SaveGameQuizScoreResponse gameScoreResponse);
}
