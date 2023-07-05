package com.lms.changemaker.entity;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "program_assignment")
@Data
@NoArgsConstructor
public class ProgramAssigment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "assign_id")
	private int assignId;
	
	

	@Column(name = "student_id")
	private int studentId;
	
	@Column(name = "prog_id")
	private int progId;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "school_id")
	private int schoolId;

	@Column(name = "start_date")
	private String startDate;

	@Column(name = "end_date")
	private String endDate;

	@Transient
	int isOngoing=0;
	
	@Transient
	double progress=0;

	@Transient
	int remainProgram=0;
	
	@Transient
	private String color;

	@Transient
	private Program program;


	@Transient
	private Module module;

	@Transient
	private String certifyUrl;

	
	
	public ProgramAssigment(int studentId, int progId, int userId, int schoolId) {
		super();
		this.studentId = studentId;
		this.progId = progId;
		this.userId = userId;
		this.schoolId = schoolId;
	
	}

	public ProgramAssigment(int studentId, int progId, int userId, int schoolId,String startDate) {
		super();
		this.studentId = studentId;
		this.progId = progId;
		this.userId = userId;
		this.schoolId = schoolId;
		this.startDate = startDate;

	}
}
