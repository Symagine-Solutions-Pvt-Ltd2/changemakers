package com.lms.changemaker.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name="school")
@Data
@NoArgsConstructor
public class School implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	@Column(name="school_id")
	private int schoolId;

	@Column(name="school_branch")
	private String schoolBranch;

	@Column(name="school_name")
	private String schoolName;

	@Column(name="school_address")
	private String schoolAddress;

	@Column(name="school_code")
	private String schoolCode;          
	
	@Column(name="user_id")
	private int userId;
	
	
	
	@Column(name="contact_name")
	private String contactName;

	@Column(name="contact_email")
	private String contactEmail;
	
	@Column(name="contact_phone")
	private BigInteger contactPhone;
	
	
	@Transient
	private double studentsProgress;

	@Transient
	private List<ProgramAssignedDetail> programAssignedDetails;

	private String token;
	private String password;
	
	
	
	@Column(name="stu_reg_count")
	private int studentRegisterCount;
	
	@Column(name="certificate_issued")
	private int certificateIssued;
	
	




	public School(int schoolId, String schoolName) {
		this.schoolId = schoolId;
		this.schoolName = schoolName;
	}
}