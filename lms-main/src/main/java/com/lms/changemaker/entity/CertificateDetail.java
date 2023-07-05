package com.lms.changemaker.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "certificate_details")
@Data
@NoArgsConstructor
public class CertificateDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="certify_id")
	private int certifyId ;

	@Column(name="student_id")
	private int studentId;
	
	@Column(name="school_id")
	private int schoolId;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="prog_id")
	private int progId;

	@Column(name="certificate_url")
	private String certificateUrl;

	public CertificateDetail(int studentId, int schoolId, int userId, int progId, String certificateUrl) {
		this.studentId = studentId;
		this.schoolId = schoolId;
		this.userId = userId;
		this.progId = progId;
		this.certificateUrl = certificateUrl;
	}
}