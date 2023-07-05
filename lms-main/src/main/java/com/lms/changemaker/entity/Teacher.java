package com.lms.changemaker.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


@Entity
@Table(name="teacher")
public class Teacher implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	@Column(name="teacher_id")
	private int teacherId;

	@Column(name="email")
	private String teacherEmail;

	@Column(name="is_active")
	private int isActive;
	
	@Column(name="name")
	private String teacherName;

	@Column(name="password")
	private String teacherPassword;

	
	@Transient
    private String passwordConfirm;
	
	@Transient
	private School teacherSchool;
	
	@Column(name="phone")
	private String teacherPhone;


	@Column(name="school_id")
	private int schoolId;

	
	@Column(name="subject")
	private String teacherSubject;

	@Column(name="teacher_code")
	private String teacherCode;

	@Column(name="token")
	private String token;

	
	
	public Teacher() {
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherEmail() {
		return teacherEmail;
	}

	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherPassword() {
		return teacherPassword;
	}

	public void setTeacherPassword(String teacherPassword) {
		this.teacherPassword = teacherPassword;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public School getTeacherSchool() {
		return teacherSchool;
	}

	public void setTeacherSchool(School teacherSchool) {
		this.teacherSchool = teacherSchool;
	}

	public String getTeacherPhone() {
		return teacherPhone;
	}

	public void setTeacherPhone(String teacherPhone) {
		this.teacherPhone = teacherPhone;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public String getTeacherSubject() {
		return teacherSubject;
	}

	public void setTeacherSubject(String teacherSubject) {
		this.teacherSubject = teacherSubject;
	}

	public String getTeacherCode() {
		return teacherCode;
	}

	public void setTeacherCode(String teacherCode) {
		this.teacherCode = teacherCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Teacher [teacherId=" + teacherId + ", teacherEmail=" + teacherEmail + ", isActive=" + isActive
				+ ", teacherName=" + teacherName + ", teacherPassword=" + teacherPassword + ", passwordConfirm="
				+ passwordConfirm + ", teacherSchool=" + teacherSchool + ", teacherPhone=" + teacherPhone
				+ ", schoolId=" + schoolId + ", teacherSubject=" + teacherSubject + ", teacherCode=" + teacherCode
				+ "]";
	}

	
}