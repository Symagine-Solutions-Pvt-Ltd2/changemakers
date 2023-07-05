package com.lms.changemaker.service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.validation.Valid;

import com.lms.changemaker.entity.ProgramAssigment;
import org.springframework.web.multipart.MultipartFile;

import com.lms.changemaker.entity.Module;
import com.lms.changemaker.entity.Student;

public interface ModuleService {

	
	Module addModule(Module module);
		
    List<Module> findAllModules();
   
    Optional<Module> findModuleById(int moduleId);

    
    List<Module> findModuleByProgId(int progId);
    
	String addnewModule(MultipartFile multipartModuleFile, MultipartFile multipartDeepDiveFile,
			@Valid Module addModuleForm);

	List<Module> getAllModuleByProgId(int progId, Student student);


	String getModuleListByProg(int progId, Locale locale);

    List<ProgramAssigment> getModuleAssigntoStudentByProg(int prog_id);
}
