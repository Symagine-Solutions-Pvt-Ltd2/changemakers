package com.lms.changemaker.entity;

import java.util.List;

import javax.persistence.Entity;



public class ResponseData {

	boolean status;
	String message;
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}	
	
}
