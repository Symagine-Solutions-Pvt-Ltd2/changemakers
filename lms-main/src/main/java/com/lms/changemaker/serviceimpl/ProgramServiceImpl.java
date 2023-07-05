package com.lms.changemaker.serviceimpl;

import java.util.*;

import javax.validation.Valid;

import com.lms.changemaker.entity.*;
import com.lms.changemaker.entity.Module;
import com.lms.changemaker.service.S3BucketStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lms.changemaker.repository.ProgramRepository;
import com.lms.changemaker.file.FileStorageServiceImpl;
import com.lms.changemaker.service.ProgramService;


@Service
public class ProgramServiceImpl implements ProgramService {

	

@Autowired
private ProgramRepository programRepository;


@Autowired
private ProgramAssigmentServiceImpl programAssigmentServiceImpl;

@Autowired
private FileStorageServiceImpl fileStorageServiceImpl;

@Autowired
private ModuleServiceImpl moduleServiceImpl;

@Autowired
private SchoolServiceImpl schoolServiceImpl;

@Autowired
private UserServiceImpl userServiceImpl;
	
@Autowired
private ProgramProgressServiceImpl programProgressServiceImpl;

/*@Autowired
private GoogleTranslateServiceImpl googleTranslateService;*/

@Autowired
private S3BucketStorageService s3BucketStorageService;

@Autowired
private CertificateDetailServiceImpl certificateDetailService;

@Autowired
private ModuleServiceImpl moduleServiceimpl;

@Autowired
private ProgramStandardDetailServiceImpl programStandardDetailServiceImpl;

@Override
@Transactional
public Program addProgram(Program program) {
	return programRepository.save(program);
}

@Override
public List<Program> findAllPrograms() {
	
	List<Program> programs=programRepository.findAll();
	
	
	programs.forEach(pr->{
	/*	  pr.setProgramName(googleTranslateService.translate(pr.getProgramName(),locale.getLanguage()));
		  pr.setDescription(googleTranslateService.translate(pr.getDescription(),locale.getLanguage()));*/
		pr.setImagePath(pr.getImagePath());
		pr.setCountModules(moduleServiceImpl.findModuleByProgId(pr.getProgId()).size());

	});
	
	return programs;
}

@Override
public Optional<Program> findProgramById(int programId) {
	return programRepository.findById(programId);
}


@Override
@Transactional
public String addnewProgram(MultipartFile multipartProgramFile, @Valid Program addProgramForm) {
	System.out.println(addProgramForm.toString());
	
	try {
		if(multipartProgramFile.isEmpty())
			return "Could not add program. Image not selected.";
		
		String moduleImagePath= s3BucketStorageService.uploadFormImage(multipartProgramFile);
		addProgramForm.setImagePath(moduleImagePath);
		Program newProgramForm=addProgram(addProgramForm);
		    userServiceImpl.findAllSubAdmins(2).forEach(u->{
		    	programAssigmentServiceImpl.saveProgramAssigment(new ProgramAssigment(0,newProgramForm.getProgId(),u.getUserId(),0));
			});
		addProgramForm.getStandardList().forEach(standard -> {
			programStandardDetailServiceImpl.addProgramStandardDetail(new ProgramStandardDetail(newProgramForm.getProgId(),standard.getStandardId()));
		});
		
		return "Program added successfully.";
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return "Error Occured. Could not add Program.";
	

}

	@Override
	public List<ProgramAssignedDetail> getAssignedProgramDetails(int userId, int schoolId) {
		return programRepository.getAssignedProgramDetails(userId,schoolId);
	}
	

	@Override
	public List<Program> getAssignedProgramDetails2(int userId, int schoolId) {
		return programRepository.getAssignedProgramDetails2(userId,schoolId);
	}

	@Override
	public List<ProgramAssignedDetail> getAssignedProgramDetailsByUserID(int userId) {
		return programRepository.getAssignedProgramDetailsByUserID(userId);
	}

	@Override
	public List<ProgramAssignedDetail> getAssignedProgramDetailsByStudentId(int studentId, int userId, int schoolId) {
		return programRepository.getAssignedProgramDetailsByStudentId(studentId,userId,schoolId);
	}

    @Override
    public List<ProgramAssignedDetail> getProgramAssigntoStudent(int studentId, int userId, int studentSchoolId) {

		List<ProgramAssignedDetail> assignedProgramDetailsByStudentId = getAssignedProgramDetailsByStudentId(studentId, userId, studentSchoolId);

		assignedProgramDetailsByStudentId.forEach(pr->
				{

					List<ProgramProgress> programProgresses=programProgressServiceImpl.getProgramProgressByStuProg(pr.getProgId(),studentId,100);
					if(programProgresses.size()>0){
						ProgramProgress programProgress = programProgresses.stream().skip(programProgresses.size() - 1).findFirst().get();


						List<Module> moduleList= moduleServiceimpl.findModuleByProgId(pr.getProgId());
						System.out.println(moduleList.size());
						System.out.println(programProgresses.size());
						if(moduleList.size()==programProgresses.size()) {
							pr.setProgress(programProgress.getProgress());
							if(certificateDetailService.getCertificateDetailByStuProg(pr.getProgId(),programProgress.getStudentId())!=null)
								pr.setCertifyUrl(certificateDetailService.getCertificateDetailByStuProg(pr.getProgId(),programProgress.getStudentId()).getCertificateUrl());
							else
								pr.setCertifyUrl("");
						}

					} 


			
				}
	
		);

		for(int i=0;i<assignedProgramDetailsByStudentId.size();i++) {
			ProgramAssignedDetail m=assignedProgramDetailsByStudentId.get(i);
			if(m.getProgress()<100)
			{
				m.setIsOngoing(1);
				break;
			}
		}

		return assignedProgramDetailsByStudentId;
	}
	
	
	@Override
	@Transactional
    public String editProgram(MultipartFile multipartProgramFile, @Valid Program addProgramForm) {

	String  moduleImagePath="";
	try {
		


		Program program= findProgramById(addProgramForm.getProgId()).get();
		if(!multipartProgramFile.isEmpty())  {
			moduleImagePath= s3BucketStorageService.uploadFormImage(multipartProgramFile);
			program.setImagePath(moduleImagePath);

		}  else {
			program.setImagePath(program.getImagePath());
		}


		program.setProgramName(addProgramForm.getProgramName());
		program.setDescription(addProgramForm.getDescription());

		Program newProgramForm=addProgram(program);
		
		/*
		 * addProgramForm.getUsers().stream().forEach(us->{ ProgramAssigment
		 * programAssigment=new
		 * ProgramAssigment(0,newProgramForm.getProgId(),us.getUserId(),0);
		 * ProgramAssigment
		 * newprogramAssigment=programAssigmentServiceImpl.saveProgramAssigment(
		 * programAssigment); int ar[]=new int[1];
		 * ar[0]=newprogramAssigment.getAssignId(); List<School>
		 * schools=schoolServiceImpl.findSchoolByUserId(us.getUserId());
		 * if(schools.size()>0) { schools.stream().forEach(sc ->{
		 * programAssigmentServiceImpl.updateProgramAssignToSchool(ar[0],sc.getSchoolId(
		 * ));
		 * studentServiceImpl.findStudentBySchoolId(sc.getSchoolId()).stream().forEach(
		 * st ->{ programAssigmentServiceImpl.updateProgramAssignToStudent(ar[0],st.
		 * getStudentId()); }); }); }
		 * 
		 * 
		 * });
		 */
		return "Program Updated successfully.";
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return "Error Occured. Could not update Program.";
	

}


}
