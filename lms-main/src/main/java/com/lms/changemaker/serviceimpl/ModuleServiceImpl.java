package com.lms.changemaker.serviceimpl;

import java.util.*;
import javax.validation.Valid;

import com.lms.changemaker.entity.*;
import com.lms.changemaker.entity.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.lms.changemaker.repository.ModuleRepository;
import com.lms.changemaker.file.FileStorageServiceImpl;
import com.lms.changemaker.service.ModuleService;


@Service
public class ModuleServiceImpl implements ModuleService {

	

@Autowired
private ModuleRepository moduleRepository;

@Autowired
private SpringTemplateEngine thymeleafTemplateEngine;

@Autowired
private FileStorageServiceImpl fileStorageService;

@Autowired
private TaskServiceImpl taskServiceImpl;

@Autowired
private GameTaskServiceImpl gameTaskServiceImpl;

/*@Autowired
private GoogleTranslateServiceImpl googleTranslateService;*/

@Autowired
private VideoTaskServiceImpl videoTaskServiceImpl;

@Autowired
private QuizTaskServiceImpl quizTaskServiceImpl;

@Autowired
private ProgramProgressServiceImpl programProgressServiceImpl;

@Autowired
private S3BucketStorageServiceImpl s3BucketStorageService;

@Override
public List<Module> findAllModules() {
	return moduleRepository.findAll();
}


@Override
@Transactional
public Module addModule(Module module) {
	return moduleRepository.save(module);
}



@Override
public Optional<Module> findModuleById(int moduleId) {
	return moduleRepository.findById(moduleId);
}



@Override
@Transactional
public String addnewModule(MultipartFile multipartModuleFile, MultipartFile multipartDeepDiveFile,@Valid Module addModuleForm) {
		try {
			 
			// String moduleImagePath= fileStorageService.storeModuleImage(multipartModuleFile);
            // String deepDiveImagePath= fileStorageService.storeDeepDiveFile(multipartDeepDiveFile);

		String moduleimage=	s3BucketStorageService.uploadFormImage(multipartModuleFile);
		String moduleDeepDive=	s3BucketStorageService.uploadFormImage(multipartDeepDiveFile);

//		 String deepDiveImagePathUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/upload/module_files/")
//						.path(deepDiveImagePath).build().toUriString();
             addModuleForm.setImage(moduleimage);
			 addModuleForm.setDeepDive(moduleDeepDive);
			 addModuleForm.setProgId(addModuleForm.getSelectedProgram().getProgId());
			Module module= addModule(addModuleForm);
			Task gTask = null,vTask2=null;
			Task vTask=taskServiceImpl.addTask(new Task("video",module.getModuleId()));
			
			if(addModuleForm.getGamesList()!=null)
			 gTask=taskServiceImpl.addTask(new Task("game",module.getModuleId()));

			if(addModuleForm.getVideosList2()!=null)
			 vTask2=taskServiceImpl.addTask(new Task("video",module.getModuleId()));

			Task qTask=taskServiceImpl.addTask(new Task("quiz",module.getModuleId()));

			if(addModuleForm.getVideosList()!=null)
			videoTaskServiceImpl.addVideoTask(new VideoTask(addModuleForm.getVideosList().getVideoId(),vTask.getTaskId(),addModuleForm.getVideosList()));
			else
				return "Could not add module. Please select a video task";

			if(addModuleForm.getGamesList()!=null)
			gameTaskServiceImpl.addGameTask(new GameTask(addModuleForm.getGamesList().getGameId(),gTask.getTaskId(),addModuleForm.getGamesList()));
			if(addModuleForm.getVideosList2()!=null)
			videoTaskServiceImpl.addVideoTask(new VideoTask(addModuleForm.getVideosList2().getVideoId(),vTask2.getTaskId(),addModuleForm.getVideosList2()));

			if(addModuleForm.getQuizsList()!=null&&addModuleForm.getQuizsList().size()>0) {

				addModuleForm.getQuizsList().forEach(q->{
					quizTaskServiceImpl.addQuizTask(new QuizTask(q.getQuizId(),qTask.getTaskId(),q));

				});

			}
			else
			return "Could not add module. Please select a quiz task";
			
			return "Module has been added to "+addModuleForm.getSelectedProgram().getProgramName()+" program";
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	

	 return "Error Occured.Could not add module.";
}


@Override
public List<Module> findModuleByProgId(int progId) {

	return moduleRepository.findModuleByProgId(progId);
}



@Override
public String getModuleListByProg(int progId, Locale locale) {
	List<Module> listModules= moduleRepository.findModuleByProgId(progId);
	listModules.forEach(m->{

	/*	m.setModuleName(googleTranslateService.translate(m.getModuleName(),locale));
		m.setDescription(googleTranslateService.translate(m.getDescription(),locale));*/
		m.setImage(m.getImage());
		m.setDeepDive(m.getDeepDive());
	    ModuleTraining moduleTraining=taskServiceImpl.getTaskstraining(m.getModuleId());

	    
	    moduleTraining.getQuiz().forEach(q->{
	    	//q.setQuestion(googleTranslateService.translate(q.getQuestion(),locale));
	    	//q.setAns(googleTranslateService.translate(q.getAns(),locale));

	    	q.quizOptions.forEach(qQ->{
				             //qQ.setQuizChoice(googleTranslateService.translate(qQ.getQuizChoice(),locale));
	    		if(qQ.getQuizOptionId()==q.getAnswer()) {
	    			q.setAns(qQ.getQuizChoice());
	    		}
	    	});
	    });
	    m.setModuleTraining(moduleTraining);
				
	});
	
	
	/*
	 * MvcUriComponentsBuilder.fromMethodName(AdminController.class, "serveFile",
	 * path.getFileName().toString()).build().toUri().toString())
	 * .collect(Collectors.toList()));
	 */
	
	System.out.println(listModules.toString());
	
	ModuleListingData listingData=new ModuleListingData();
	listingData.setModules(listModules);
	Map<String, Object> templateModel = new HashMap<String, Object>();
	templateModel.put("moduleList",listingData);
	
	 Context thymeleafContext = new Context();
     thymeleafContext.setVariables(templateModel);
     
     String htmlBody = thymeleafTemplateEngine.process("admin/module_listing.html", thymeleafContext);

	return htmlBody;
}

	@Override
	public List<ProgramAssigment> getModuleAssigntoStudentByProg(int prog_id) {

	
	
	   return null;
	}


	@Override
public  List<Module> getAllModuleByProgId(int progId,Student student){
	
	List<Module> modules=findModuleByProgId(progId);
	
	modules.forEach(m ->{
		
		m.setDeepDive(m.getDeepDive());
	List<ProgramProgress> programProgresses=programProgressServiceImpl.getProgramProgressByStudentIdModuleId(student.getStudentId(),m.getModuleId());

	if(programProgresses.size()>0)
		m.setProgress(programProgresses.stream().skip(programProgresses.size() - 1).findFirst().get().getProgress());
		
	});
	for(int i=0;i<modules.size();i++) {
		Module m=modules.get(i);
		if(m.getProgress()<100)
		   {
		  m.setIsOngoing(1);
		   break;
	     }
	}
	System.out.println(modules.toString());

	return modules;
	
}





}
