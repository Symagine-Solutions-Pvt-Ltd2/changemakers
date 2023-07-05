package com.lms.changemaker.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;


@Table(name = "task")
@Entity
@Data
@NoArgsConstructor
public class Task implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	@Column(name="task_id")
	private int taskId;

	@Column(name="type")
	private String taskType;
	
	@Column(name="module_id")
	private int moduleId; 
	

	public Task(String taskType, int moduleId) {
		super();
		this.taskType = taskType;
		this.moduleId = moduleId;
	}
}