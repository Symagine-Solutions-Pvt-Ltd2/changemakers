package com.lms.changemaker.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lms.changemaker.repository.TeacherRepository;
import com.lms.changemaker.entity.Teacher;
import com.lms.changemaker.service.JwtTeacherDetailsService;
import com.lms.changemaker.service.TeacherService;
import com.lms.changemaker.utilities.JwtTokenUtil;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TeacherServiceImpl implements TeacherService {

	

@Autowired
private TeacherRepository teacherRepository;

@Autowired
private PasswordEncoder bcryptEncoder;



@Autowired
private JwtTokenUtil jwtTokenUtil;

@Autowired
private JwtTeacherDetailsService userDetailsService;


@Override
@Transactional
public Teacher insertTeacher(Teacher teacher) {
	teacher.setTeacherPassword(bcryptEncoder.encode(teacher.getTeacherPassword()));
	return teacherRepository.save(teacher);
}

@Override
public List<Teacher> listAllTeachers() {
	return teacherRepository.findAll();
}

@Override
public Optional<Teacher> findTeacherById(int teacherId) {
	return teacherRepository.findById(teacherId);
}

@Override
public Teacher findTeacherByCode(String code) {
	
	return teacherRepository.findTeacherByCode(code);
}


@Override
@Transactional
public int saveTeacherToken(String token,String email) {
	
	return teacherRepository.saveTeacherToken(token,email);
}	

@Override
public Teacher findTeacherByEmail(String email) {
	return teacherRepository.findTeacherByEmail(email);
}



@Override
public void login(Teacher teacher) {

		  
			final UserDetails userDetails = userDetailsService.loadUserByUsername(teacher.getTeacherEmail());
			final String token = jwtTokenUtil.generateToken(userDetails.getUsername());
			System.out.println("Token/////////// :"+token);
			teacher.setToken(token);
			saveTeacherToken(token,teacher.getTeacherEmail());
			
}



}
