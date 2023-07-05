package com.lms.changemaker.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;
	
	private String email;
	private int type;
	private String password;
	private String name;

	@Column(name="organization_name")
	private String organizationName;
	
	private double phone;
	private String username;

	private String token;

	
	@Transient
	private List<Program> programs=new ArrayList();
	
	
	@Transient
	private List<ProgramAssigment> programAssigments=new ArrayList();
	
	@Transient
	private int schoolsRegistered;
	
	@Transient
	private int studentsRegistered;
	
	
	@Transient
	private int certificatesIssued;

	@Transient
	private String language;

	@Transient
	private String languageCode;
}
