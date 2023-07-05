package com.lms.changemaker.entity;

import java.io.Serializable;
import javax.persistence.*;



@Entity
@Table(name="module_training_details")
public class TrainingDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	@Column(name="id")
	private int trainingId;

	@Column(name="status")
	private int status;

	@Column(name="module_id")
	private int moduleId;

	@Column(name="student_id")
	private int studentId;

	@Column(name="deep_dive")
	private String deepDive;
	
	
	@Transient
	private Score score;

	


	public TrainingDetail() {
	}

	public TrainingDetail(int status, int moduleId, int studentId, String deepDive) {
		super();
		this.status = status;
		this.moduleId = moduleId;
		this.studentId = studentId;
		this.deepDive = deepDive;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public int getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(int trainingId) {
		this.trainingId = trainingId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getDeepDive() {
		return deepDive;
	}

	public void setDeepDive(String deepDive) {
		this.deepDive = deepDive;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "TrainingDetail [trainingId=" + trainingId + ", status=" + status + ", moduleId=" + moduleId
				+ ", studentId=" + studentId + ", deepDive=" + deepDive + "]";
	}

	}