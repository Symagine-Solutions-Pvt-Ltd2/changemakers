package com.lms.changemaker.service;

import java.util.List;
import java.util.Optional;

import com.lms.changemaker.entity.*;

import javax.transaction.Transactional;

public interface SchoolService {

    School addSchool(School school);
	
	List<School> getSchools();
	
	Optional<School> getSchoolById(int schoolId);
	
	public	School findSchoolByEmail(String email);

	public int saveSchoolToken(String token,String email);
	public School autologinSchool(School school);



    int editSchool(School schoolForm, User user);

    String registerSchool(School schoolForm, User user);


	List<School> getSchoolDashBoardList(int userId);

	
	public	List<School> findSchoolByUserId(int userId);
	
	public School findSchoolByCode(String code);

	int saveStudentCount(int studentRegisterCount, int schoolId);

    int changePassword(School school);


	List<ProgramProgress> getSchoolProgressByModule(int studentSchoolId);


	String getAssignedPrograms(School school);
}
