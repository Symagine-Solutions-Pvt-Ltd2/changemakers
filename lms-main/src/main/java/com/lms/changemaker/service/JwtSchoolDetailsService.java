package com.lms.changemaker.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lms.changemaker.repository.SchoolRepository;
import com.lms.changemaker.entity.School;

@Service
public class JwtSchoolDetailsService implements UserDetailsService {

	@Autowired
	SchoolRepository schoolRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		School school=schoolRepository.findSchoolByEmail(email);
		return new org.springframework.security.core.userdetails.User(school.getContactEmail(), school.getPassword(),
				new ArrayList<>());
		
	}
}
