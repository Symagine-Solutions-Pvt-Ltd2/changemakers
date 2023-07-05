package com.lms.changemaker.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lms.changemaker.repository.StudentRepository;
import com.lms.changemaker.entity.Student;

@Service
public class JwtStudentDetailsService implements UserDetailsService {

	@Autowired
	StudentRepository studentRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Student student=studentRepository.findStudentByEmail(email);
		 System.out.println(student.toString());
		if (student==null) 
			 throw new UsernameNotFoundException("User not found with email: " + email);
		
		return new org.springframework.security.core.userdetails.User(student.getStudentEmail(), student.getStudentPassword(),
				new ArrayList<>());
		
	}
}
