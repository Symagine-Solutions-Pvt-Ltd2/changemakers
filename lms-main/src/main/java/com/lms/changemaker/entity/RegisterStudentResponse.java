package com.lms.changemaker.entity;

public class RegisterStudentResponse {

	
	String message;
	int status;
	Student student;
	public String getMessage() {
		return message;
	}
	public int getStatus() {
		return status;
	}
	public Student getStudent() {
		return student;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	
	
}
