package com.lms.changemaker.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "max_student_limit_program")
@Data
@NoArgsConstructor
public class MaxStudentLimitProgram implements Serializable {

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "idmax_student_limit_program")
	private int idmaxStudentLimitProgram;
	@Column(name = "limit")
	private int limit;

	@Column(name = "prog_id")
	private int progId;

	
}
