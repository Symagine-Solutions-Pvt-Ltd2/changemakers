package com.lms.changemaker.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="video_task")
@Data
@NoArgsConstructor
public class VideoTask {
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	@Column(name="video_task_id")
	private int videoTaskId ;

	@Column(name="video_id")
	private int videoId;
	
	@Column(name="task_id")
	private int taskId;

	
	@OneToOne(
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
 	  @JoinColumn(name = "video_id",insertable = false,updatable = false)
	  private Video video = new Video();
	
	
	


	public VideoTask(int videoId, int taskId, Video video) {
		super();
		this.videoId = videoId;
		this.taskId = taskId;
		this.video = video;
	}
}
