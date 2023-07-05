package com.lms.changemaker.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="program_progress")
@Data
@NoArgsConstructor
public class ProgramProgress {
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	@Column(name="progress_id")
	private int progressId ;
	
	@Column(name="progress")
	private double progress;
	
	@Column(name="task_id")
	private int taskId;

	@Column(name="student_id")
	private int studentId;
	
	@Column(name="start_date")
    private String startDate;
	
	@Column(name="end_date")
    private String endDate;
	
	@Column(name="module_id")
	private int moduleId;
	
	@Column(name="prog_id")
	private int progId;

	public ProgramProgress( int taskId,double progress, int studentId,  int moduleId, int progId,String startDate) {
		super();
		this.progress = progress;
		this.taskId = taskId;
		this.studentId = studentId;
		this.startDate = startDate;
		this.moduleId = moduleId;
		this.progId = progId;
	}

}
