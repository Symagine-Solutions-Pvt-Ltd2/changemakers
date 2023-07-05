package com.lms.changemaker.service;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface FileStorageService {

	String storeModuleImage(MultipartFile file);

	String storeDeepDiveFile(MultipartFile file);
	
	
	Resource loadModuleImage(String fileName);
	Resource loadDeepDiveImage(String fileName);

	String storeProgramImage(MultipartFile file);
	

	Resource loadProgramImage(String fileName);
	 
}
