package com.lms.changemaker.entity;

import java.io.Serializable;
import javax.persistence.*;



@Entity
@Table(name="student_module_detail")
public class StudentModuleDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	private int id;

	@Column(name="completion_status")
	private int completionStatus;

	@Column(name="module_id")
	private int moduleId;

	@Column(name="student_id")
	private int studentId;

	public StudentModuleDetail() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCompletionStatus() {
		return this.completionStatus;
	}

	public void setCompletionStatus(int completionStatus) {
		this.completionStatus = completionStatus;
	}

	public int getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public int getStudentId() {
		return this.studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

}