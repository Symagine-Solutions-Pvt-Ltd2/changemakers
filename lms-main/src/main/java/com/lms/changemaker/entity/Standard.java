package com.lms.changemaker.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "standard")
@Data
@NoArgsConstructor
public class Standard implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	@Column(name="standard_id")
	private int standardId;

	@Column(name="standard_name")
	private String standardName;

	
}