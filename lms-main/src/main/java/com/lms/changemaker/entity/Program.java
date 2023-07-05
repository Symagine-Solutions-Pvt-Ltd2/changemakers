package com.lms.changemaker.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "program")
@Data
public class Program {

	
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	@Column(name="prog_id")
	private int progId ;

	@Column(name="program_name")
	private String programName;         
	
	@Column(name="image_path")
	private String imagePath;

	

	@Transient
	private int countModules;

	private String description;
	
	@Transient
	private List<User> users;

	@Transient
	private float progress;

	@OneToMany(
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	@JoinColumn(name = "prog_id")
	public List<ProgramStandardDetail> programStandardDetails = new ArrayList<>();

	@Transient
	private List<Standard> standardList;
	

	public Program() {
		super();
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getProgId() {
		return progId;
	}

	public void setProgId(int progId) {
		this.progId = progId;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getCountModules() {
		return countModules;
	}

	public void setCountModules(int countModules) {
		this.countModules = countModules;
	}
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public float getProgress() {
		return progress;
	}

	public void setProgress(float progress) {
		this.progress = progress;
	}



	
	
	
}
