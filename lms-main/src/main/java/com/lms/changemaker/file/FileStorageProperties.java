package com.lms.changemaker.file;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@ConfigurationProperties(prefix = "modulefile")
@Data
public class FileStorageProperties {
	private String imageModule;
	private String fileDeepDive;
	public FileStorageProperties(String imageModule, String fileDeepDive) {
		super();
		this.imageModule = imageModule;
		this.fileDeepDive = fileDeepDive;
	}
	public FileStorageProperties() {
		super();
	}
	
	
}
