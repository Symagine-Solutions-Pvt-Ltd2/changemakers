package com.lms.changemaker.entity;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class InsertSubadminAccessDetails implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int moduleId;

	private int userId;

	public InsertSubadminAccessDetails(int moduleId, int userId) {
		super();
		this.moduleId = moduleId;
		this.userId = userId;
	}
	
	
	
}
