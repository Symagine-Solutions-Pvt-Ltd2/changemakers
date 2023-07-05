package com.lms.changemaker.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "program_standard_detail")
@Data
@NoArgsConstructor
public class ProgramStandardDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="id_program_standard_detail")
	private int idProgramStandardDetail;

	@Column(name="prog_id")
	private int progId;

	@Column(name="standard_id")
	private int standardId;


	@OneToOne(
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	@JoinColumn(name = "standard_id",insertable = false,updatable = false)
	private Standard standard = new Standard();

	public ProgramStandardDetail(int progId, int standardId) {
		this.progId = progId;
		this.standardId = standardId;
	}


}