package com.lms.changemaker.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lms.changemaker.repository.TeacherRepository;
import com.lms.changemaker.entity.Teacher;

@Service
public class JwtTeacherDetailsService implements UserDetailsService {

	@Autowired
	TeacherRepository teacherRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Teacher teacher=teacherRepository.findTeacherByEmail(email);
		 
		if (teacher==null) 
			 throw new UsernameNotFoundException("User not found with email: " + email);
		
		return new org.springframework.security.core.userdetails.User(teacher.getTeacherEmail(), teacher.getTeacherPassword(),
				new ArrayList<>());
		
	}
}
