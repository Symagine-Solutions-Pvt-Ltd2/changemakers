package com.lms.changemaker.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "module")
@Data
@NoArgsConstructor
public class Module implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	@Column(name="module_id")
	private int moduleId;

	@Column(name="description")
	private String description;

	@Column(name="module_name")
	private String moduleName;
	
	@Column(name="image")
	private String image;
	
	@Column(name="deep_dive")
	private String deepDive;
	
	
	@Column(name="prog_id")
	private int progId;
	
	@Transient
	private User usersList;
	
	@Transient
	private Video videosList;
	
	@Transient
	private Game gamesList;
	
	@Transient
	private  Video videosList2;
	
	@Transient
	private List<Quiz> quizsList;
	
	@Transient
	private Program selectedProgram;
	
	@Transient
	private double progress;
	
	@Transient
	private int isOngoing=0;
	
	
	@Transient
	private ModuleTraining moduleTraining;
}