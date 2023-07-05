package com.lms.changemaker.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name="video")
@Data
@NoArgsConstructor
public class Video implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	@Column(name="video_id")
	private int videoId;

	@Column(name="video_url_english")
	private String videoUrlEnglish;

	@Column(name="video_url_bengali")
	private String videoUrlBengali;


	@Column(name="video_title")
	private String videoTitle;

}