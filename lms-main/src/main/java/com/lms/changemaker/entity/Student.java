package com.lms.changemaker.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@DynamicUpdate
@Table(name = "student")
@Data
@NoArgsConstructor
public class Student implements Serializable {

private static final long serialVersionUID = 1L;



	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	@Column(name = "student_id")
	private int studentId;

	

	@Column(name="first_name")
	private String studentFirstName;

	@Column(name="last_name")
	private String studentLastName;
	
  

	@Column(name = "email")
	private String studentEmail;
   

	@Column(name = "password")
	private String studentPassword;
	

	@Transient
    private String passwordConfirm;


	@Transient
	private School studentSchool;

	@Transient
	private int progCount;
	
	@Column(name = "school_id")
	private int studentSchoolId;
  

	@Column(name = "standard_id")
	private int standardId;
  

	@Column(name = "parent_name")
	private String studentParentName;
	

	@Column(name = "address")
	private String studentAddress;
  

	@Column(name = "parent_number")
	private String studentParentNumber;
	

	@Transient
	private String schoolCode;
	

	@Transient
	private String language;
  
    @Transient
	private int certificateCount;
    
	
	@Column(name ="section_id")
	private int sectionId;
	
	@Column(name="token")
    private String studentToken;
	
	@OneToOne(
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
 	  @JoinColumn(name = "standard_id",insertable = false,updatable = false,nullable = true)
	private Standard selectedStandard=new Standard();
	
	
	
	@Transient
	private List<ProgramAssignedDetail> assigments;
}	
	